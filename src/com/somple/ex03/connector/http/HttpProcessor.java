package com.somple.ex03.connector.http;

import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {

	private HttpConnector httpConnector;
	
	public HttpProcessor(HttpConnector httpConnector) {
		this.httpConnector = httpConnector;
	}
	
	public void process(Socket socket) {
		SocketInputStream input = null;
		OutputStream output = null;
		// create HttpRequest Object and parse
	}
}
