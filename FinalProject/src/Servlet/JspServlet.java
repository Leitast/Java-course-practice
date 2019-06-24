package Servlet;

import Servlet.*;
import statictast.GetRequest;
import statictast.GetResponse;
import MyServer.server;

public abstract class JspServlet extends HttpServlet {
	    GetRequest request;
	    public void _jspInit() {
	    	
	    }
	    public void _jspDestroy() {
	    	
	    }
	    
	    public abstract void _jspService(HttpServletRequest request, HttpServletResponse response);
	    
	    public final void init() {
	        super.init();
	        request = server.req;
	        _jspInit();
	    }
	    public final void destroy() {
	        super.destroy();
	        _jspDestroy();
	    }
	    public final void service(HttpServletRequest request, HttpServletResponse response) {
	        _jspService(request, response);
	    }
}