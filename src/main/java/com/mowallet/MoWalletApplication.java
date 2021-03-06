package com.mowallet;

import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.event.EventListener;

@ServletComponentScan // @WebListener 스캔을 가능하도록
@SpringBootApplication
public class MoWalletApplication {

	private final BitcoinJsonRpcService bitcoinJsonRpcService;

	public MoWalletApplication(BitcoinJsonRpcService bitcoinJsonRpcService) {
		this.bitcoinJsonRpcService = bitcoinJsonRpcService;
	}

	public static void main(String[] args) {
        SpringApplication.run(MoWalletApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        bitcoinJsonRpcService.walletInit();
    }


}
