package dev.reeve.psr;

import dev.reeve.psr.proxy.ProxyType;
import lombok.Data;

@Data
public class Config {
	private String host = "localhost";
	private String virtualHost = "/";
	private int port = 5672;
	private String username = "guest";
	private String password = "guest";
	private ProxyType proxyType = ProxyType.BUNGEECORD;
}
