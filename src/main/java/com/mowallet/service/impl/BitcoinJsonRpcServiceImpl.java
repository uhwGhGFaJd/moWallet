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
    public List<GetUserLast10Transactions> getUserLast10Transactions(String user_name) {
        List<GetUserLast10Transactions> list = new ArrayList<>();
        JSONObject jsonObject = bitcoinJsonRPC.sendJsonRpc("listtransactions", user_name, 10);
        JSONArray jsonArray = jsonObject.getJSONArray("result");

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
        JSONObject jsonObject = bitcoinJsonRPC.sendJsonRpc("getaddressesbylabel", user_name).getJSONObject("result");
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
    public BigDecimal getReceivedByLabel(String user_name) {
        return bitcoinJsonRPC.sendJsonRpc("getreceivedbylabel", user_name).getBigDecimal("result").setScale(8, RoundingMode.HALF_EVEN);
    }

    @Override
    @Transactional
    public void getNewAddress(getNewAddressPost getNewAddressPost, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("member");
        getNewAddressPost.setUser_name(user.getUser_name());
        getNewAddressPost.setUser_id(user.getUser_id());
        getNewAddressPost.setAddress(bitcoinJsonRPC.sendJsonRpc("getnewaddress", getNewAddressPost.getUser_name()).getString("result"));
        jsonRpcDbMapper.getNewAddressAndInsertDb(getNewAddressPost);
    }

    @Override
    public void withdrawBitcoinAndInsertDb(WithdrawPost withdrawPost, Principal principal, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("member");
        withdrawPost.setUser_id(user.getUser_id());
        // get set service fees
        withdrawPost.setService_fees(jsonRpcDbMapper.getServiceFees());
        // get set user bitcoin balance
        withdrawPost.setUser_balance(bitcoinJsonRPC.sendJsonRpc("getreceivedbylabel", principal.getName()).getBigDecimal("result").setScale(8, RoundingMode.HALF_EVEN));

        //fee_amount is 0.10220000 fee is 0.00102200
        BigDecimal serviceFeeAmount =
                (withdrawPost.getUser_balance().multiply(BigDecimal.valueOf(withdrawPost.getService_fees())).setScale(8, RoundingMode.HALF_EVEN))
                        .divide(HUNDRED_PERCENT, 8, RoundingMode.HALF_EVEN);
        //System.out.println("fee_amount is " + fee_amount + " fee is " + fee_amount.divide(HUNDRED_PERCENT, 8, RoundingMode.HALF_EVEN));
        System.out.println(serviceFeeAmount);

        BigDecimal amountToBeSentWithoutFees = withdrawPost.getUser_balance().subtract(serviceFeeAmount).setScale(8, RoundingMode.HALF_EVEN);
        System.out.println(amountToBeSentWithoutFees);

        System.out.println(withdrawPost.toString());

    }


}
