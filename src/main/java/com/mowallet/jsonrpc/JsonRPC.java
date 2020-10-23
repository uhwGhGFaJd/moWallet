package com.mowallet.jsonrpc;

import com.mowallet.mapper.JsonRpcDbMapper;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
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
public class JsonRPC {

    private final JsonRpcDbMapper jsonRpcDbMapper;

    public JsonRPC(JsonRpcDbMapper jsonRpcDbMapper) {
        this.jsonRpcDbMapper = jsonRpcDbMapper;
    }

    public static void main(String[] args) throws Exception {
        JSONObject resp = sendJsonRpc("getbalance", false);
    }

    public static JSONObject sendJsonRpc(String method, boolean debugMode, String[]... parameters) throws IOException, JSONException {
        JSONObject electrumRpc = new JSONObject();
        electrumRpc.put("jsonrpc", "2.0");
        electrumRpc.put("id", "curltext");
        electrumRpc.put("method", method);
        electrumRpc.put("params", parameters);


        String tracUrl = "http://127.0.0.1:7772";
        String tracUsername = "rpcuser";
        String tracPassword = "rpcpassword";


        URL url = new URL(tracUrl);
        URLConnection conn = url.openConnection();
        String userpass = tracUsername + ":" + tracPassword;
        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        conn.setRequestProperty("Authorization", basicAuth);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

        System.out.println("jsonrpc request:\n" + electrumRpc.toString());

        wr.write(electrumRpc.toString());
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
