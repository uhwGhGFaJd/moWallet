package com.mowallet;

import com.mowallet.bitcoinrpc.BitcoinRpcConfig;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;

@RunWith(SpringRunner.class)
class MoWalletApplicationTests {

	@Test
	public void hello() throws Exception {
		BitcoinRpcConfig bitcoinRpcConfig = BitcoinRpcConfig.builder()
				.rpcHost("127.0.0.1")
				.rpcUsername("Nitin")
				.rpcPassword("magicmaker07")
				.rpcPort("18332")
				.rpcWallet("admin")
				.build();

		//http://127.0.0.1:18332/wallet/admin

		System.out.println(bitcoinRpcConfig.getBaseUrl());

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
		//bitcoinRpc.put("params", parameters);

		out.write(bitcoinRpc.toString());
		out.flush();
		out.close();

		if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			System.out.println("asd");
		}
	}


}
