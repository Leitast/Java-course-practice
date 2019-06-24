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
		// TODO �Զ����ɵķ������
		requestDispatcher=new RequestDispatcher(string);
		return requestDispatcher;
	}

	public void setAttribute(String string, String pet) {
		// TODO �Զ����ɵķ������
		animalMap.put(string, pet);
	}

	public session getSession() {
		// TODO �Զ����ɵķ������
		return new session();
	}

	public Object getAttribute(String string) {
		// TODO �Զ����ɵķ������
		return animalMap.get(string);
	}
}
