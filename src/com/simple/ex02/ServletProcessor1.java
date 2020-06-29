package com.simple.ex02;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletProcessor1 {

	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/")+1);
		URLClassLoader loader = null;
		try {
			// create a URLClassloader
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			// the forming of repository is taken from the createClassloader method in ClassLoaderFactory
 			String repository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
 			System.out.println("====> repository:"+repository);
 			// the code for forming the URL is taken the addRepository method in StandardClassLoader
 			urls[0] = new URL(null,repository, streamHandler);
 			loader = new URLClassLoader(urls);
		}catch(IOException e) {
			e.printStackTrace();
		}
		Class clazz = null;
		try {
			clazz = loader.loadClass(servletName);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		Servlet servlet = null;
		try {
			servlet = (Servlet) clazz.newInstance();
			servlet.service((ServletRequest) request, (ServletResponse) response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
