package com.simple.ex05.valves;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

/**
 * 
 * this a valve , print to client IP
 *
 */
public class ClientIPLoggerValve implements Valve, Contained {

	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
		// pass this request on to the next valve in our pipeline
		context.invokeNext(request, response);
		System.out.println("Client IP logger valve");
		ServletRequest servletRequest = request.getRequest();
		System.out.println(servletRequest.getRemoteAddr());
		System.out.println("===============================");
		
	}

}
