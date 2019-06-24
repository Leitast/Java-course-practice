package jsp;

import Servlet.*;
import MyServer.*;

import java.*;
import java.io.*;
import java.util.Date;


public class MyJsp {
	public static String jspfile;
	public static StringBuilder myjsp;
    int flag;
    
    public MyJsp(String jspfile) throws IOException {
        this.jspfile = jspfile;
        flag=0;
        getjspinf();
    }
    
    public String getjspfile() {
    	return jspfile;
    }
    
    public void Script(String s) {
        if (flag == 1) myjsp.append("\");\n");
        myjsp.append("out.println(");
        myjsp.append(s);
        myjsp.append(");\n");
    }
    
    public void Html(String s) {
        if (flag == 0) {
            myjsp.append("out.write(\"");
            myjsp.append(s);
            flag = 1;
            //System.out.println(s);
        } else if (flag == 1) {
            myjsp.append(s);
        }
    }
    
    public void Java(String s) {
        if (flag == 1) myjsp.append("\");\n");
        myjsp.append(s).append("\n");

    }
    
    public void getjavafile() {
        FileWriter javafile = null;
        try {
        	javafile = new FileWriter("src/WebContent/" + jspfile + ".java");
        	javafile.write(myjsp.toString());
        	javafile.flush();
        	javafile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    
	public void getjspinf() throws IOException {		
        String jspinf = server.readXml(server.WEB_ROOT + "/" + jspfile);
        myjsp=new StringBuilder();
        jspfile = jspfile.substring(0, jspfile.indexOf(".")) + "jsp";
        File file = new File("src/WebContent/" + jspfile + ".java");
        System.out.println(file.getName());
        if(file.exists()&&file.isFile()) {
        	file.delete();
        }
        file.createNewFile();
        
        myjsp.append("package WebContent;\n");
        myjsp.append("import Servlet.*;\n\n");
        myjsp.append("import java.io.*;\n\n");
        myjsp.append("public class ");
        myjsp.append(jspfile);
        myjsp.append(" extends JspServlet { \n");
        myjsp.append("public void _jspService(HttpServletRequest request, HttpServletResponse response) {\n");
        myjsp.append("PrintWriter out = response.getWriter();\n");

        //删除test1和test5的无效头文件
        if (jspinf.startsWith("<%@"))
        	jspinf = jspinf.substring(jspinf.indexOf("%>") + 2);
        
        //响应html页面，编写html和body字段
        if (!jspinf.contains("<html>")) {
             myjsp.append("out.println(\"");
             myjsp.append("<html>");
             myjsp.append("\");\n");
            }
       if (!jspinf.contains("<body>")) {
             myjsp.append("out.println(\"")
              .append("<body>")
              .append("\");\n");
            }
       
      // System.out.println(myjsp);
        
        for(int i=0;i<jspinf.length();i++) {
           if (i + 3 < jspinf.length() && jspinf.substring(i, i + 3).equals("<%=")) {
        	  flag=1;
        	  Script(jspinf.substring(i + 3, jspinf.indexOf("%>", i)));
              int index=jspinf.indexOf("%>", i);
              i = index + 1;
              flag=0;
           }else if(i + 2 < jspinf.length() && jspinf.substring(i, i + 2).equals("<%")) {
        	   flag=1;
        	   Java(jspinf.substring(i + 2, jspinf.indexOf("%>", i)));
               int index=jspinf.indexOf("%>", i);
               i = index + 1;
               flag = 0;
           } else {
              if (jspinf.substring(i, i + 1).equals("\"")) {Html("\\\"");}
              else Html(jspinf.substring(i, i + 1));
          }
        }
 
        //System.out.println(myjsp);
          //打印尾部文件
          myjsp.append("\");\n");
          if (!jspinf.contains("</body>")) {
              myjsp.append("out.write(\"");
              myjsp.append("</body>");
              myjsp.append("\");\n");
          }
          if (!jspinf.contains("</html>")) {
              myjsp.append("out.write(\"");
              myjsp.append("</html>");
              myjsp.append("\");\n");
          }
          myjsp.append("out.flush();\n");
          myjsp.append("out.close();\n");
          myjsp.append("}\n");
          myjsp.append("}\n");
          
          getjavafile();
	}
}
