<html>
 <meta http-equiv=Content-Type content="text/html;charset=utf-8">
<body bgcolor="white"> 
<h1>The Echo JSP - Testing for Jsp tasks</h1> 
<%   java.util.Enumeration eh = request.getHeaderNames(); 
     while (eh.hasMoreElements()) { 
         String h = (String) eh.nextElement(); 
         out.print("<br> header: " + h ); 
         out.println(" value: " + request.getHeader(h)); 
     } 
%> 
</body> 
</html> 