import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ={ "/Logout" })
public class LogoutServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("logout") != null) {
            if(request.getSession() != null) {
                HttpSession session = request.getSession();
                session.invalidate();
                System.out.println("Logged out of Session");
                response.sendRedirect("/Webeng03");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
