import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Servlet implementation class WebEng02
 */
@WebServlet(urlPatterns ={ "/Webeng02" })
public class Webeng02 extends HttpServlet {

    RequestDispatcher dispatcher;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Webeng02() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("<h1>2. Praktikum WebEng </h1>");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Navigation");
        dispatcher.include(request,response);
        String actionParameter = request.getParameter("action");

        switch(actionParameter) {
            case "header":
                response.getWriter().append("<h2>Wilkommen zu Header</h2>");
                this.printHeader(request,response);
                break;
            case "cookies":
                response.getWriter().append("<h2>Wilkommen zu Cookies</h2>");
                this.printCookies(request, response);
                break;
            case "search":
                response.getWriter().append("<h2>Wilkommen zu Search</h2>");
                response.sendRedirect("http://www.google.de");
                break;
            case "home":
            default:
                response.getWriter().append("<h2>Willkommen zu Home</h2>");
                response.getWriter().append("<div>Wilkommen zum WebEng02 Servlet von Thomas Kammann(765002) </div>");
                break;
        }

    }
    private void printHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Enumeration headerNames = request.getHeaderNames();
        response.getWriter().append("<table>");
        response.getWriter().append("<tr><th>Header-Key</th><th>Header-Wert</th><th>Beschreibung</th></tr>");
        while (headerNames.hasMoreElements()) {
            response.getWriter().append("<tr>");
            String key = (String)headerNames.nextElement();
            response.getWriter().append("<td>" + key + "</td>");
            response.getWriter().append("<td>" + request.getHeader(key) + "</td>");
            if(key.equals("Accept-Language")) {
                response.getWriter().append("<td>Sagt in welcher Sprache der Server die Seite darstellen sool, falls moeglich</td>");
            } else if(key.equals("User-Agent")) {
                response.getWriter().append("<td>Gibt Informationen darueber ob die Seite ueber ein Mobiles Geraet oder ueber ein Browser und welcher Browser geoeffnet wurde</td>");
            } else {
                response.getWriter().append("<td></td>");
            }

            response.getWriter().append("</tr>");
        }
        response.getWriter().append("</table>");
    }

    private void printCookies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                response.getWriter().append(cookie.getName() + "<br>");
                response.getWriter().append(cookie.getMaxAge() + "<br>");
                response.getWriter().append(cookie.getValue() + "<br>");
            }
        } else {
            Cookie neuerCookie = new Cookie("Das_ist_ein_neuer_Cookie2", "Hallo WebEng02");
            neuerCookie.setMaxAge(60*60*24*365);
            response.addCookie(neuerCookie);
        }

    }
}

