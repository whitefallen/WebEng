import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns ={ "/Navigation" })
public class Navigation extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Navigation() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<nav style=\"display:block-box;\">");
        response.getWriter().println("<ul>");
        response.getWriter().println("<li><a href=\"?action=home\">Startseite</a></li>");
        response.getWriter().println("<li><a href=\"?action=header\">Header</a></li>");
        response.getWriter().println("<li><a href=\"?action=cookies\">Cookies</a></li>");
        response.getWriter().println("<li><a href=\"?action=search\">Search</a></li>");
        response.getWriter().println("</ul>");
        response.getWriter().println("</nav>");
    }
}
