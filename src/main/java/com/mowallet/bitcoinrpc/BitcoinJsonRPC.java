package com.mowallet.bitcoinrpc;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/22
 * Github       : https://github.com/uhwGhGFaJd
 */


public class BitcoinJsonRPC {

    public BitcoinJsonRPC(String rpcUser, String rpcPassword, String rpcHost, String rpcPort, String rpcWallet){
        BitcoinRpcConfig bitcoinRpcConfig = BitcoinRpcConfig.builder()
                .rpcHost(rpcUser)
                .rpcUsername(rpcPassword)
                .rpcPassword(rpcHost)
                .rpcPort(rpcPort)
                .rpcWallet(rpcWallet)
                .build();

        System.out.println(bitcoinRpcConfig.getBaseUrl());

        try {
            URL url = new URL(bitcoinRpcConfig.getBaseUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", bitcoinRpcConfig.getBasicAuth());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());

            JSONObject bitcoinRpc = new JSONObject();
            bitcoinRpc.put("jsonrpc", "1.0");
            bitcoinRpc.put("id", "1");
            bitcoinRpc.put("method", "getbalances");

            out.write(bitcoinRpc.toString());
            out.flush();
            out.close();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("asd");
            }

        }catch (Exception ex){

        }
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




