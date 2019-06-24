package WebContent;
import Servlet.*;

import java.io.*;

public class test4jsp extends JspServlet { 
public void _jspService(HttpServletRequest request, HttpServletResponse response) {
PrintWriter out = response.getWriter();
out.println("<body>");
out.write("<html> <meta http-equiv=Content-Type content=\"text/html;charset=utf-8\"><body bgcolor=\"white\"> <h1>The Echo JSP - Testing for Jsp tasks</h1> ");
   java.util.Enumeration eh = request.getHeaderNames();      while (eh.hasMoreElements()) {          String h = (String) eh.nextElement();          out.print("<br> header: " + h );          out.println(" value: " + request.getHeader(h));      } 
out.write(" </body> </html> ");
out.flush();
out.close();
}
}
