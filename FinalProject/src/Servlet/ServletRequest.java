package Servlet;

import statictast.*;
import Servlet.*;
import java.io.*;

public class ServletRequest extends HttpServletRequest{
    public ServletRequest(InputStream inputStream) throws IOException {
        super(inputStream);
    }
}
