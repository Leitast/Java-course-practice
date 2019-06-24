package WebContent;
import Servlet.*;

import java.io.*;

public class show5jsp extends JspServlet { 
public void _jspService(HttpServletRequest request, HttpServletResponse response) {
PrintWriter out = response.getWriter();
out.write("<!DOCTYPE html><html><head><title>Testing for Filter</title><body> <h1>Testing for Filter</h1> <p>The site have been visited ");
out.println(course.AccessFilter.nNum);
out.write(" times.<p></body> </html>");
out.flush();
out.close();
}
}
