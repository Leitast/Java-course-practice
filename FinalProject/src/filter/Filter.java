package filter;

import Servlet.*;
import java.io.*;

public interface Filter {

    public void destroy();
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
    public void init(FilterConfig fConfig) throws ServletException;
}
