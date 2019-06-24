package Servlet;

import jsp.MyJsp;

import java.io.IOException;

import Servlet.JspServlet;
public class RequestDispatcher {
	String name;
	public RequestDispatcher(String s) {
		name=s;
	}
	public void forward(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO 自动生成的方法存根
        MyJsp jsp = new MyJsp(name);
        try {
//            String headers = "HTTP/1.1 200\r\n" + "\r\n";
//            response.getOutputStream().write(headers.getBytes());
            Class clazz = Class.forName("WebContent." + jsp.getjspfile());
            JspServlet servlet = (JspServlet) clazz.newInstance();
            servlet._jspService(req, resp);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
