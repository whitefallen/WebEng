import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ={ "/protected/Stats" })
public class StatServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        response.setContentType("text/html");
        response.getWriter().append("<main>");
            response.getWriter().append("<h1>Statistics</h1>");
            response.getWriter().append("<div>");
                response.getWriter().append("<div>");
                    response.getWriter().append("Total Active Session: "+ request.getServletContext().getAttribute("activeSessions"));
                response.getWriter().append("</div>");
                response.getWriter().append("<div>");
                    response.getWriter().append("Current User:"+ session.getAttribute("username"));
                response.getWriter().append("</div>");
                response.getWriter().append("<div>");
                    response.getWriter().append("<a href=\""+request.getHeader("referer")+"\">Back</a>");
                response.getWriter().append("</div>");
            response.getWriter().append("</div>");
        response.getWriter().append("</main>");

    }
}
