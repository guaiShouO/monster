package com.simple.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.simple.ex01.Request;
import com.simple.ex01.Response;

public class HttpServerSocket {

	/**
	 * WEB_ROOT is the directory where our HTML and other files reside
	 * for this package, WEB_ROOT is the "webroot" directory under the
	 * working directory
	 * the working directory is the location in the file system
	 * from where the java command was invoked
	 * 
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator +"webroot";
	
	// shutdown command
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	private boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServerSocket server = new HttpServerSocket();
		server.await();
	}
	
	public void await() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8080,1,InetAddress.getByName("127.0.0.1"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		// loop waiting for a request
		while(!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				// create request object and parse
				Request request = new Request(input); 
				request.parse();
				//create response object
				Response resp = new Response(output); 
				resp.setRequest(request);
				resp.sendStaticResource();
				
				// close the socket
				socket.close();
				// check if the previous URI is shutdown command
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}catch(IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	
	
	
	
	
	
	
	
}
