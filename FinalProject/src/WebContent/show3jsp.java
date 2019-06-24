package WebContent;
import Servlet.*;

import java.io.*;

public class show3jsp extends JspServlet { 
public void _jspService(HttpServletRequest request, HttpServletResponse response) {
PrintWriter out = response.getWriter();
out.write("<!DOCTYPE html><html><head><title>Testing for Servlet-MVC</title><body> <h1>Recommended Pet - Testing for Web-MVC</h1> <p>You want a ");
out.println(request.getParameter("legs"));
out.write("-legged pet weighing ");
out.println(request.getParameter("weight"));
out.write("lbs.</p><p> We recommend getting <b>");
out.println(request.getAttribute("pet"));
out.write("</b></p></body> </html>");
out.flush();
out.close();
}
}
