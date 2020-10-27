package com.mowallet.service.impl;

import com.mowallet.domain.GetAddressesByLabel;
import com.mowallet.domain.GetUserLast10Transactions;
import com.mowallet.jsonrpc.BitcoinJsonRPC;
import com.mowallet.service.BitcoinJsonRpcService;
import com.mowallet.utils.BitcoinUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
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

    private final BitcoinJsonRPC bitcoinJsonRPC;

    public BitcoinJsonRpcServiceImpl(BitcoinJsonRPC bitcoinJsonRPC) {
        this.bitcoinJsonRPC = bitcoinJsonRPC;
    }

    @Override
    public List<GetUserLast10Transactions> GetUserLast10Transactions(String user_name) {
        List<GetUserLast10Transactions> list = new ArrayList<>();
        JSONObject jsonObject = bitcoinJsonRPC.sendJsonRpc("listtransactions", "*", 10);
        JSONArray jsonArray = jsonObject.getJSONArray("result");

        for (Object tx : jsonArray) {
            JSONObject jsonParser = new JSONObject(tx.toString());
            GetUserLast10Transactions getUserLast10Transactions = GetUserLast10Transactions.builder()
                    .address(jsonParser.getString("address"))
                    .category(jsonParser.getString("category"))
                    .amount(jsonParser.getBigDecimal("amount").setScale(8, RoundingMode.DOWN))
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
    public List<GetAddressesByLabel> GetAddressesByLabel(String user_name) {
        List<GetAddressesByLabel> list = new ArrayList<>();
        JSONObject jsonObject = bitcoinJsonRPC.sendJsonRpc("getaddressesbylabel", user_name).getJSONObject("result");
        Iterator<String> x = jsonObject.keys();

        while (x.hasNext()) {
            GetAddressesByLabel getAddressesByLabel = GetAddressesByLabel.builder()
                    .address(x.next())
                    .build();
            list.add(getAddressesByLabel);
        }
        Collections.reverse(list);
        return list;
    }


}
