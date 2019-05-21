package presentation;

import logic.ArticleManager;
import logic.ShoppingCartManager;
import persistence.ArticleFactoryDAO;
import transferobject.ArticleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns ={ "/frontend" })
public class FrontendController extends HttpServlet {

    ArticleManager newAricleManager = null;
    ArticleFactoryDAO articleFactoryDAO = null;
    ShoppingCartManager newShoppingCartManager = null;
    boolean isInit = false;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontendController() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!this.isInit) {
            this.initShop();
        }

        response.setContentType("text/html;charset=UTF-8");
        try {
            this.routerAction(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void routerAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String action = request.getParameter("action");
        if(action != null) {
            switch(action) {
                case "admin":
                    System.out.println("admin");
                    this.adminAction(request, response);
                    break;
                case "articlelist":
                    this.articleListAction(request, response);
                    System.out.println("list");
                    break;
                case "articledetails":
                    this.articleDetailsAction(request, response);
                    System.out.println("details");
                    break;
                case "checkout":
                    this.checkoutAction(request, response);
                    System.out.println("checkout");
                    break;
                default:
                    this.defaultAction(request,response);
                    System.out.println("default");
                    break;
            }
        }
    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void checkoutAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    private void articleDetailsAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(request.getParameter("id") != null) {
            int articleId = Integer.parseInt(request.getParameter("id"));
            ArticleDTO article = newAricleManager.getArticleById(articleId);
            request.setAttribute("article", article);
        }
        request.getRequestDispatcher("articledetail.jsp").forward(request, response);
    }

    private void articleListAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ArrayList<ArticleDTO> stockList = newAricleManager.getArticleList();
        request.setAttribute("stocklist", stockList);
        request.getRequestDispatcher("articlelist.jsp").forward(request, response);
    }

    private void adminAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void initShop() {
        newAricleManager = new ArticleManager();
        articleFactoryDAO = new ArticleFactoryDAO();
        newAricleManager.setStockDB(articleFactoryDAO.getArticle(1));
        newShoppingCartManager = new ShoppingCartManager();
        newShoppingCartManager.setArticleManager(newAricleManager);
    }

}
