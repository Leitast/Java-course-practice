package Servlet;

import statictast.GetResponse;
import MyServer.server;
import jsp.MyJsp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpServletResponse extends GetResponse{
	public static String s;
	
    public HttpServletResponse(OutputStream output) {
        super(output);
    }

	public void setContentType(String string) {
		s=string;
	}

	public PrintWriter getWriter() {
		return new PrintWriter(getOutputStream());
	}

	public void sendRedirect(String string) throws IOException {
		// TODO 自动生成的方法存根
        MyJsp jsp = new MyJsp(string);
        try {
            Class clazz = Class.forName("WebContent." + jsp.getjspfile());
            JspServlet servlet = (JspServlet) clazz.newInstance();
            servlet._jspService((HttpServletRequest) server.req, (HttpServletResponse) server.res);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
	}
}
