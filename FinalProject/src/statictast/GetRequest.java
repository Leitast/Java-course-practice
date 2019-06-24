package statictast;

import java.io.*;
import java.net.*;
//import java.util.Enumeration;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import Frame.*;

import session.session;

public class GetRequest {

private Map<String, String> headerMap = new HashMap<>(); 
private static InputStream in;
protected static String reqstring;
private static String url;
private static String method;
private String header = "";
public GetRequest() {

}

public GetRequest(InputStream input) throws IOException {
	this.in=input;
}

public String getmethod() {
	return method;
}

public String geturl() {
	return url;
}

//ÐèÒªÐÞ¸Ä
public String getParameter(String parameter) {
    if (reqstring.contains(parameter + "=")) {
        int begin = reqstring.indexOf(parameter + "=");
        int end = reqstring.indexOf("&", begin);
        if (end == -1) {
            return reqstring.substring(begin + parameter.length() + 1);
        } else {
            return reqstring.substring(begin + parameter.length() + 1, end);
        }
    } else {
        return null;
    }
}

public void getrequest() throws IOException {
	StringBuffer readinf=new StringBuffer();
	byte[] buffer=new byte[2048];
	int len=in.read(buffer);
	for(int j=0;j<len;j++) {
		readinf.append((char)buffer[j]);
	}
	System.out.println(readinf);
	
	reqstring=readinf.toString();
	MyFrame.textArea2.append(reqstring);
	header = reqstring.substring(reqstring.indexOf("\r\n"));
	int index1,index2;
	index1=readinf.indexOf(" ");
	method=readinf.substring(0,index1);
	if(index1!=-1) {
		index2=readinf.indexOf(" ",index1+1);
		if(index2>index1) {
			url= readinf.substring(index1+2, index2);
		}
	}
	//System.out.println(url);
	}

public String getHeader(String h) {
	return headerMap.get(h);
}


public String getHeader() {
    return header;
}

public Enumeration getHeaderNames() {
    Enumeration<String> HeaderNames;
    Vector<String> header = new Vector<>();
    String key;
    String value;
    for (String s : getHeader().split("\r\n")) {
        if (s.contains(": ")) {
            key = s.substring(0, s.indexOf(": ") - 1);
            value = s.substring(s.indexOf(": ") + 1);
            headerMap.put(key, value);
            header.add(key);
        }
    }
    HeaderNames = header.elements();
    return HeaderNames;
    }
}
