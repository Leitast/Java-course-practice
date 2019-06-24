package MyServer;

import statictast.GetRequest;
import statictast.GetResponse;
import Servlet.*;
import jsp.MyJsp;
import filter.*;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import Frame.MyFrame;

public class server {
	public static String WEB_ROOT=System.getProperty("user.dir")+"\\"+"src"+"\\"+"WebContent";
    public static Map<String, String> ReadfilterMap = new HashMap<>();
    public static Map<String, String> ReadXmlMap = new HashMap<>();
    public static HttpServletRequest req;
    public static HttpServletResponse res;
    public static int curtestnum = 0;
    public static int port;
	//static ServerSocket serversocker=null;
	static Socket socket=null;
    
	//public static void main(String[] args) throws IOException {
    public static void InitWeb() throws IOException {
		//MyJsp jsp1=new MyJsp("show5.jsp");
		//System.out.println(WEB_ROOT);
        String xml = readXml("src/WebContent/WEB-INF/web.xml");
        GetXmlMap(xml);
		
		ServerSocket serversocker=null;
		Socket socket=null;
		InputStream is=null;
		OutputStream ops=null;
		
		try {
			serversocker=new ServerSocket(port);
			while(true) {
				socket=serversocker.accept();
                
				is=socket.getInputStream();
				ops=socket.getOutputStream();
				
				req=new HttpServletRequest(is);
				req.getrequest();
				
				String url=req.geturl();
				
				res=new HttpServletResponse(ops);
				
				if(url!=null) {
	                if (curtestnum == 1) {
	                    if (ReadfilterMap .containsKey("AccessFilter") || ReadfilterMap .containsKey(url)) {
	                    //curtestnum = 0;
	                    GetResponse.filterresponse((HttpServletRequest)req, (HttpServletResponse)res);
	                  }
	                }
                    if (ReadXmlMap.containsKey(url) || url.endsWith(".jsp")) {
					GetResponse.servletgetresponse((HttpServletRequest)req, (HttpServletResponse)res);
                    } else {
	                    curtestnum = 1;
                    	res.getresponse();
                   }
		         }
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(is!=null) {
				is.close();
				is=null;
			}
			if(ops!=null) {
				ops.close();
				ops=null;
			}
			if(socket!=null) {
				socket.close();
				socket=null;
			}
		}
	}
    
public static String readXml(String XmlPath) {
    StringBuffer XmlInf = new StringBuffer();
    String Inf = null;
    try {
        File file = new File(XmlPath);
          if (file.exists()) { 
            InputStreamReader readfile = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(readfile);
            while ((Inf = bufferedReader.readLine()) != null) {
            	XmlInf.append(Inf);
            }
            readfile.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return XmlInf.toString();
}

public static void GetXmlMap(String XmlInf) {
    int start = 0;
    int index;
    while ((index = XmlInf.indexOf("<servlet-name>", start)) != -1) {
    	start = index + 14;
        ReadXmlMap.put(
        		XmlInf.substring(XmlInf.indexOf("<url-pattern>", start) + 14, XmlInf.indexOf("</url-pattern>", start)),
                XmlInf.substring(XmlInf.indexOf("<servlet-class>", start) + 15, XmlInf.indexOf("</servlet-class>", start))
        );
        start = XmlInf.indexOf("</servlet-mapping>", start) + 18;
        }
    

    int filterstart = 0;
    while ((index = XmlInf.indexOf("<filter-name>", filterstart)) != -1) {
    	filterstart = index + 13;
    	ReadfilterMap .put(
        		XmlInf.substring(filterstart,XmlInf.indexOf("</filter-name>", filterstart)),
                XmlInf.substring(XmlInf.indexOf("<filter-class>", filterstart) + 14,XmlInf.indexOf("</filter-class>", filterstart))
        );
        filterstart = XmlInf.indexOf("<filter-name>", filterstart) + 13;
        }
    }
}