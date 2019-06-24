package Servlet;

import statictast.GetRequest;
import session.session;

import java.io.*;
import java.util.*;
import java.net.*;

public class HttpServletRequest extends GetRequest{

    private RequestDispatcher requestDispatcher;
    private Map<String, String> animalMap = new HashMap<>(); 
    public HttpServletRequest(InputStream inputStream) throws IOException {
        super(inputStream);
    }

	public RequestDispatcher getRequestDispatcher(String string) {
		// TODO 自动生成的方法存根
		requestDispatcher=new RequestDispatcher(string);
		return requestDispatcher;
	}

	public void setAttribute(String string, String pet) {
		// TODO 自动生成的方法存根
		animalMap.put(string, pet);
	}

	public session getSession() {
		// TODO 自动生成的方法存根
		return new session();
	}

	public Object getAttribute(String string) {
		// TODO 自动生成的方法存根
		return animalMap.get(string);
	}
}
