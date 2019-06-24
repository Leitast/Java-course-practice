package WebContent;
import Servlet.*;

import java.io.*;

public class test6jsp extends JspServlet { 
public void _jspService(HttpServletRequest request, HttpServletResponse response) {
PrintWriter out = response.getWriter();
out.write("<html> <body><b>Login to System</B>Current user is:");
out.println(request.getSession().getAttribute("username"));
out.write(" </br> </hr><form  action=\"Login\" method=\"post\">    <h4> User Name: </h4>        <input type=\"text\"  name=\"username\"  size=\"10\">   <h4> Password: </h4>   <input type=\"text\"  name=\"password\"  size=\"10\">        <p>    <input type=\"submit\" value=\"Login\" >    </p></body></form>");
out.write("</html>");
out.flush();
out.close();
}
}
