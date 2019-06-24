package statictast;

import MyServer.server;
import statictast.GetRequest;
import Servlet.*;
import jsp.MyJsp;
import filter.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GetResponse {
    private static OutputStream ops;
    private GetRequest request;
    public GetResponse() {

    }

    public GetResponse(OutputStream output) {
        this.ops= output;
    }
    

    public OutputStream getOutputStream() {
        return ops;
    }
    

    public void setrequest(GetRequest request) {
        this.request = request;
    }
    
	public  void getresponse() throws IOException {
		// TODO 自动生成的方法存根
		GetRequest req=new GetRequest();
		String url=req.geturl();
		byte[] bytes=new byte[2048];
		FileInputStream fis=null;
		File file=new File(server.WEB_ROOT,url);
		try {
			//File file=new File(server.WEB_ROOT,url);
			if(file.exists()) {
				if(file.isFile()) {
				ops.write("HTTP/1.1 200 OK\n".getBytes());
			    ops.write("Server:apache-Coyote/1.1\n".getBytes());
			    ops.write("Content-Type:text/html;charest=utf-8\n".getBytes());
			    ops.write("\n".getBytes());
			    fis=new FileInputStream(file);
			    int ch=fis.read(bytes);
			    while(ch!=-1) {
			    	ops.write(bytes, 0, ch);
			    	ch=fis.read(bytes);
			    	}
				}else {
					File[] filelist=file.listFiles();
                    ops.write("<html><body><ul>".getBytes());
                    assert filelist != null;
                    for (File value : filelist) {
                        if (!value.isFile() && value.getName().endsWith("-INF")) continue;
                        ops.write(("<li" + "><a href = \"" + req.geturl() + "/" + value.getName() + "\">" + value.getName() + "</a></li>").getBytes());
                    }
                    ops.write("</ul></body></html>".getBytes());
				}
			}else {
				ops.write("HTTP/1.1 404 not found\n".getBytes());
			    ops.write("Server:apache-Coyote/1.1\n".getBytes());
			    ops.write("Content-Type:text/html;charest=utf-8\n".getBytes());
			    ops.write("\n".getBytes());
			    String errowMessage="file not found";
			    ops.write(errowMessage.getBytes());
			    }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				fis.close();
				fis=null;
			}
		}
	}
	
	public static  void servletgetresponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.geturl().endsWith(".jsp")) {
            MyJsp jsp = new MyJsp(request.geturl().substring(0));
            try {
            	//System.out.println("My Happy Day");
                String headers = "HTTP/1.1 200\r\n" + "\r\n";
                response.getOutputStream().write(headers.getBytes());
                Class clazz = Class.forName("WebContent." + jsp.getjspfile());
                JspServlet servlet= (JspServlet) clazz.newInstance();
                servlet._jspService(request, response);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
        } else if (server.ReadXmlMap.containsKey(request.geturl())) {
            try {
                String headers = "HTTP/1.1 200\r\n" + "\r\n";
                response.getOutputStream().write(headers.getBytes());
                String value = server.ReadXmlMap.get(request.geturl());
                Class clazz = Class.forName(value);
                HttpServlet servlet = (HttpServlet) clazz.newInstance();
                servlet.service(request, response);
            } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void filterresponse(HttpServletRequest request, HttpServletResponse response) {
        String value;
        if (server.ReadfilterMap .containsKey("AccessFilter")) {
            value = server.ReadfilterMap .get("AccessFilter");
        } else {
            value = server.ReadfilterMap .get(request.geturl());
        }
        try {
            Class clazz = Class.forName(value);
            Filter filter = (Filter) clazz.newInstance();
            filter.doFilter(request, response, new FilterChain());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException | ServletException e) {
            e.printStackTrace();
        }
	}
}
