package com.simple.ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {
	
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	private boolean shutdown = false;
	
	public static void main(String[] args){
		HttpServer1 server = new HttpServer1();
		server.await();
	}
	
	public void await() {
		ServerSocket serverSocket= null;
		try {
			serverSocket = new ServerSocket(8080,1,InetAddress.getByName("127.0.0.1"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		InputStream input = null;
		OutputStream output = null;
		Socket socket = null;
		while(!shutdown) {
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				// create Request object and parse
				Request request = new Request(input);
				request.parse();
				// create Response
				Response response = new Response(output);
				response.setRequest(request);
				// check if this is a request for a servert or a static resource
				// a request for a servlet begins with "/servlet"
				if(request.getUri().startsWith("/servlet/")) {
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request,response);
				}else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request,response);
				}
				socket.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}catch(Exception e) {}
		}
	}
	
}
