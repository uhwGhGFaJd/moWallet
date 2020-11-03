package com.mowallet.bitcoinrpc;

import com.mowallet.mapper.JsonRpcDbMapper;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/22
 * Github       : https://github.com/uhwGhGFaJd
 */

@Component
public class BitcoinJsonRPC {

    private final JsonRpcDbMapper jsonRpcDbMapper;

    public BitcoinJsonRPC(JsonRpcDbMapper jsonRpcDbMapper) {
        this.jsonRpcDbMapper = jsonRpcDbMapper;
    }

    public JSONObject requestJsonRpc(String method, String user_name, boolean rootMode, Object... parameters) {
        JSONObject bitcoinRpc = new JSONObject();
        bitcoinRpc.put("jsonrpc", "1.0");
        bitcoinRpc.put("id", "1");
        bitcoinRpc.put("method", method);
        bitcoinRpc.put("params", parameters);

        if (parameters != null) {
            JSONArray paramArray = new JSONArray();
            for (Object ob : parameters) {
                paramArray.put(ob);
            }
            bitcoinRpc.put("params", paramArray);
        }

        String tracUrl;
        if (rootMode) {
            tracUrl = "http://127.0.0.1:18332/";
        } else {
            tracUrl = "http://127.0.0.1:18332/wallet/" + user_name;
        }
        String tracUsername = "bitcoin";
        String tracPassword = "J9JkYnPiXWqgRzg3vAA";

        StringBuilder jsonText = new StringBuilder();

        try {
            URL url = new URL(tracUrl);
            URLConnection conn;

            conn = url.openConnection();

            String userpass = tracUsername + ":" + tracPassword;
            String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
            conn.setRequestProperty("Authorization", basicAuth);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            System.out.println("jsonrpc request:\n" + bitcoinRpc.toString());

            wr.write(bitcoinRpc.toString());
            wr.flush();
            wr.close();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                jsonText.append(line);
            }
            rd.close();

            System.out.println("jsonrpc response:\n" + jsonText);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(jsonText.toString().trim());
    }
}



