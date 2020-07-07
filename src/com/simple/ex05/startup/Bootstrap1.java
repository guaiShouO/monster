package com.simple.ex05.startup;

import org.apache.catalina.Loader;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

import com.simple.ex05.core.SimpleLoader;
import com.simple.ex05.core.SimpleWrapper;
import com.simple.ex05.valves.ClientIPLoggerValve;
import com.simple.ex05.valves.HeaderLoggerValve;

public class Bootstrap1 {

	public static void main(String[] rags) {
		HttpConnector connector = new HttpConnector();
		
		Wrapper wrapper = new SimpleWrapper();
//		wrapper.setServletClass("SimpleServlet");
		wrapper.setServletClass("ModernServlet");
		Loader loader = new SimpleLoader();
		Valve IPValve = new ClientIPLoggerValve();
		Valve HeadValve = new HeaderLoggerValve();
		
		wrapper.setLoader(loader);
		((Pipeline) wrapper).addValve(IPValve);
		((Pipeline) wrapper).addValve(HeadValve);
		
		connector.setContainer(wrapper);
		
		try {
			connector.initialize();
			connector.start();
			// make the application wait until we press a key
			System.in.read();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
