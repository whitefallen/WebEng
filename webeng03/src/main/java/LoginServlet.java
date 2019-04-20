import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ={ "/Login" })
public class LoginServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("username") != null && !request.getParameter("username").isEmpty() ) {
            System.out.println(request.getParameter("username"));
            HttpSession session = request.getSession(true);
            session.setAttribute("username", request.getParameter("username"));
            System.out.println("Logged in Session");
            response.sendRedirect("/Webeng03/protected/");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
