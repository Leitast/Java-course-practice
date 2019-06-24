package Servlet;

import java.io.IOException;

//import Servlet.HttpServletRequest;
//import Servlet.HttpServletResponse;
import MyServer.server;
//import course.ServletException;

public class HttpServlet {
	public HttpServlet() {
		
	}
	
	public void init() {

	}

	public void destroy() {
	    	
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws Servlet.ServletException, IOException {
	    if (req.getmethod().equals("GET")) {
	        doGet(req, res);
	    } else if (req.getmethod().equals("POST")) {
	         doPost(req, res);
	    }
	}
	    
	    public void doGet(HttpServletRequest req, HttpServletResponse res) {
	    	System.out.println("The Method is GET");
	    }

	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    	System.out.println("The Method is POST");
	    }
	}