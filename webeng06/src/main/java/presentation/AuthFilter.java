package presentation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/frontend"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String authtoken = request.getParameter("authtoken");
        String confAuthToken = request.getServletContext().getInitParameter("AuthToken");
        String action = request.getParameter("action");

        if(action.equals("admin") && (authtoken == null || !authtoken.equals(confAuthToken))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
