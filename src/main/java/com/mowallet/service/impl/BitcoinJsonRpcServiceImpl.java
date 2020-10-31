package com.mowallet.service.impl;

import com.mowallet.domain.*;
import com.mowallet.jsonrpc.BitcoinJsonRPC;
import com.mowallet.mapper.JsonRpcDbMapper;
import com.mowallet.service.BitcoinJsonRpcService;
import com.mowallet.utils.BitcoinUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class BitcoinJsonRpcServiceImpl implements BitcoinJsonRpcService {

    private static final BigDecimal HUNDRED_PERCENT = BigDecimal.valueOf(100);

    private final BitcoinJsonRPC bitcoinJsonRPC;
    private final JsonRpcDbMapper jsonRpcDbMapper;

    public BitcoinJsonRpcServiceImpl(BitcoinJsonRPC bitcoinJsonRPC, JsonRpcDbMapper jsonRpcDbMapper) {
        this.bitcoinJsonRPC = bitcoinJsonRPC;
        this.jsonRpcDbMapper = jsonRpcDbMapper;
    }

    @Override
    public void walletInit() {
        JSONArray jsonArray = bitcoinJsonRPC.requestJsonRpc("listwallets", null, true).getJSONArray("result");
        if (jsonArray.isEmpty()) {
            return;
        }
        for (Object ob : jsonArray) {
            bitcoinJsonRPC.requestJsonRpc("unloadwallet", null, true, ob.toString());
        }

    }

    @Override
    public void loadUserWallet(String user_name) {
        bitcoinJsonRPC.requestJsonRpc("loadwallet", user_name, false, user_name);
    }

    @Override
    public void unLoadWallet(String user_name) {
        bitcoinJsonRPC.requestJsonRpc("unloadwallet", user_name, false);
    }

    @Override
    public List<GetUserLast10Transactions> getUserLast10Transactions(String user_name) {
        List<GetUserLast10Transactions> list = new ArrayList<>();
        JSONArray jsonArray = bitcoinJsonRPC.requestJsonRpc("listtransactions", user_name, false, "*", 10).getJSONArray("result");

        for (Object tx : jsonArray) {
            JSONObject jsonParser = new JSONObject(tx.toString());
            GetUserLast10Transactions getUserLast10Transactions = GetUserLast10Transactions.builder()
                    .address(jsonParser.getString("address"))
                    .category(jsonParser.getString("category"))
                    .amount(jsonParser.getBigDecimal("amount").setScale(8, RoundingMode.HALF_EVEN))
                    .vout(jsonParser.getInt("vout"))
                    .fee(BitcoinUtil.optBigDecimal(jsonParser, "fee"))
                    .confirmations(jsonParser.getInt("confirmations"))
                    .blockhash(BitcoinUtil.optString(jsonParser, "blockhash"))
                    .blockheight(BitcoinUtil.optInt(jsonParser, "blockheight"))
                    .blockindex(BitcoinUtil.optInt(jsonParser, "blockindex"))
                    .blocktime(BitcoinUtil.getTimestampToDate(BitcoinUtil.optInt(jsonParser, "blocktime")))
                    .txid(jsonParser.getString("txid"))
                    .time(BitcoinUtil.getTimestampToDate(BitcoinUtil.optInt(jsonParser, "time")))
                    .timereceived(BitcoinUtil.getTimestampToDate(BitcoinUtil.optInt(jsonParser, "timereceived"))).build();
            list.add(getUserLast10Transactions);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<GetAddressesByLabel> getAddressesByLabel(String user_name) {
        List<GetAddressesByLabel> list = new ArrayList<>();
        JSONObject jsonObject = bitcoinJsonRPC.requestJsonRpc("getaddressesbylabel", user_name, false).getJSONObject("result");
        Iterator<String> iterator = jsonObject.keys();

        while (iterator.hasNext()) {
            GetAddressesByLabel getAddressesByLabel = GetAddressesByLabel.builder()
                    .address(iterator.next())
                    .build();
            list.add(getAddressesByLabel);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public UserBalance getBalances(String user_name) {
        UserBalance userBalance = new UserBalance();
        JSONObject jsonObject = bitcoinJsonRPC.requestJsonRpc("getbalances", user_name, false).getJSONObject("result").getJSONObject("mine");
        userBalance.setTrusted(jsonObject.getBigDecimal("trusted").setScale(8, RoundingMode.HALF_EVEN));
        userBalance.setUntrusted_pending(jsonObject.getBigDecimal("untrusted_pending").setScale(8, RoundingMode.HALF_EVEN));
        userBalance.setImmature(jsonObject.getBigDecimal("immature").setScale(8, RoundingMode.HALF_EVEN));
        return userBalance;
    }

    @Override
    @Transactional
    public void getNewAddress(getNewAddressPost getNewAddressPost, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("member");
        getNewAddressPost.setUser_name(user.getUser_name());
        getNewAddressPost.setUser_id(user.getUser_id());
        getNewAddressPost.setAddress(bitcoinJsonRPC.requestJsonRpc("getnewaddress", getNewAddressPost.getUser_name(), false).getString("result"));
        jsonRpcDbMapper.getNewAddressAndInsertDb(getNewAddressPost);
    }

    @Override
    @Transactional
    public void withdrawBitcoinAndInsertDb(WithdrawPost withdrawPost, Principal principal, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("member");
        withdrawPost.setUser_id(user.getUser_id());
        // get set service fees
        withdrawPost.setService_fees(jsonRpcDbMapper.getServiceFees());
        // get set user bitcoin balance
        withdrawPost.setUser_balance(bitcoinJsonRPC.requestJsonRpc("getreceivedbylabel", principal.getName(), false).getBigDecimal("result").setScale(8, RoundingMode.HALF_EVEN));

        //fee_amount is 0.10220000 fee is 0.00102200
        BigDecimal serviceFeeAmount =
                (withdrawPost.getUser_balance().multiply(BigDecimal.valueOf(withdrawPost.getService_fees())).setScale(8, RoundingMode.HALF_EVEN))
                        .divide(HUNDRED_PERCENT, 8, RoundingMode.HALF_EVEN);
        System.out.println(serviceFeeAmount);

        // 0.00919800
        BigDecimal amountToBeSentWithoutFees = withdrawPost.getUser_balance().subtract(serviceFeeAmount).setScale(8, RoundingMode.HALF_EVEN);
        System.out.println(amountToBeSentWithoutFees);

        bitcoinJsonRPC.requestJsonRpc("sendtoaddress", withdrawPost.getWithdraw_to(), false);


    }

    @Override
    public TransactionsDetail getTransactionsDetail(String address, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("member");
        JSONObject jsonObject = bitcoinJsonRPC.requestJsonRpc("getaddressinfo", user.getUser_name(), false, address).getJSONObject("result");

        return TransactionsDetail.builder()
                .address(jsonObject.getString("address"))
                .scriptPubKey(jsonObject.getString("scriptPubKey"))
                .ismine(jsonObject.getBoolean("ismine"))
                .solvable(jsonObject.getBoolean("solvable"))
                .desc(jsonObject.getString("desc"))
                .iswatchonly(jsonObject.getBoolean("iswatchonly"))
                .isscript(jsonObject.getBoolean("isscript"))
                .iswitness(jsonObject.getBoolean("iswitness"))
                .witness_version(jsonObject.getInt("witness_version"))
                .witness_program(jsonObject.getString("witness_program"))
                .pubkey(jsonObject.getString("pubkey"))
                .ischange(jsonObject.getBoolean("ischange"))
                .timestamp(BitcoinUtil.getTimestampToDate(BitcoinUtil.optInt(jsonObject, "timestamp")))
                .build();
    }
}
