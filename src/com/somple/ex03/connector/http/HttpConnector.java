package com.somple.ex03.connector.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable{
	boolean stopped;
	private String scheme = "http";
	
	public String getScheme() {
		return scheme;
	}

	@Override
	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(8080);
		}catch(IOException e){
			e.printStackTrace();
		}
		while(!stopped) {
			// Accpet the next incoming connection from the server socket
			Socket socket = null;
			try {
				socket = server.accept();
			}catch(IOException e) {
				e.printStackTrace();
			}
			// Hand this socket off to an httpProcessor
		}
		
	}
	
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

}
