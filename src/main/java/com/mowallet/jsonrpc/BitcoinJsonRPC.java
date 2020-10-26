package com.mowallet.jsonrpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mowallet.mapper.JsonRpcDbMapper;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

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


    public static void main(String[] args) throws IOException {
        sendJsonRpc("listtransactions");
    }


    public static JSONObject sendJsonRpc(String method, String[]... parameters) throws IOException {
        JSONObject bitcoinRpc = new JSONObject();
        bitcoinRpc.put("jsonrpc", "1.0");
        bitcoinRpc.put("id", "1");
        bitcoinRpc.put("method", method);
        bitcoinRpc.put("params", parameters);

        String tracUrl = "http://127.0.0.1:18332";
        String tracUsername = "bitcoin";
        String tracPassword = "J9JkYnPiXWqgRzg3vAA";

        URL url = new URL(tracUrl);
        URLConnection conn = url.openConnection();
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
        StringBuilder jsonText = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            jsonText.append(line);
        }
        rd.close();

        System.out.println("jsonrpc response:\n" + jsonText);


        return new JSONObject(jsonText.toString());
    }
}




