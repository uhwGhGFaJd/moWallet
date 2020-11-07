package com.mowallet.exception;


import org.json.JSONObject;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/29
 * Github       : https://github.com/uhwGhGFaJd
 */
public class CryptoCurrencyRpcExceptionHandler {

    public void checkException(JSONObject jsonObject) throws CryptoCurrencyRpcException {
        if (jsonObject.get("error") != null) {
            JSONObject errorJson = jsonObject.getJSONObject("error");
            String message = errorJson.getString("message");

            int code = errorJson.getInt("code");
            switch (code) {
                case -6:
                    throw new InsufficientFundsException(message);
                default:
                    throw new CryptoCurrencyRpcException(message);
            }
        }
    }
}
