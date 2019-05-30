package presentation;

import logic.ArticleManager;
import logic.ShoppingCartManager;
import persistence.ArticleFactoryDAO;
import transferobject.ArticleDTO;
import transferobject.ShoppingCartDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns ={ "/frontend" })
public class FrontendController extends HttpServlet {

    //region instance variables
    private ArticleManager newAricleManager = null;
    private ShoppingCartManager newShoppingCartManager = null;
    //endregion

    //region Constructor
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontendController() {
        super();
        this.initShop();
    }
    //endregion

    //region Servlet doGet / doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.handleArticleFormSubmit(request,response);
        this.handleCheckout(request,response);
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if(request.getSession(false) == null) {
            request.getSession(true);
        } else {
            // Rebind Cart Objekt to Session
            HttpSession session = request.getSession();
            this.newShoppingCartManager.setCartDTO((ShoppingCartDTO)session.getAttribute("shoppingCart"));
        }

        try {
            this.routerAction(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region HandleFormSubmits
    private void handleArticleFormSubmit(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("articleAction") != null) {
            String formAction = request.getParameter("articleAction");
            if(this.checkForValues(request,response,formAction)){
                switch(formAction) {
                    case "add":
                        this.addNewArticle(request, response);
                        break;
                    case "edit":
                        this.editArticle(request, response);
                        break;
                    case "delete":
                        this.deleteArticle(request, response);
                        break;
                    default:
                        break;
                }
                this.setResponseMessage(request,response,formAction);
            }
        }
    }

    private void handleCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("checkoutAction") != null) {
            this.newShoppingCartManager.checkoutCart();
            request.getRequestDispatcher("checkoutSuccess.jsp").forward(request, response);
        }

    }
    private void deleteArticle(HttpServletRequest request, HttpServletResponse response) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));

        try {
            this.newAricleManager.removeArticle(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editArticle(HttpServletRequest request, HttpServletResponse response) {
        String articleName = request.getParameter("articleName");
        float articlePrice = Float.parseFloat(request.getParameter("articlePrice"));
        int articleAmount = Integer.parseInt(request.getParameter("articleAmount"));
        int articleId = Integer.parseInt(request.getParameter("articleId"));

        try {
            this.newAricleManager.updateArticle(articleName,articleId,articleAmount,articlePrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewArticle(HttpServletRequest request, HttpServletResponse response) {

        String articleName = request.getParameter("articleName");
        float articlePrice = Float.parseFloat(request.getParameter("articlePrice"));
        int articleAmount = Integer.parseInt(request.getParameter("articleAmount"));

        try {
            this.newAricleManager.addArticle(articleName,this.pseudoAutoIncrement(),articleAmount,articlePrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //endregion

    //region Routing
    private void routerAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String action = request.getParameter("action");
        if(action != null) {
            switch(action) {
                case "admin":
                    this.adminAction(request, response);
                    break;
                case "articlelist":
                    this.articleListAction(request, response);
                    break;
                case "articledetails":
                    this.articleDetailsAction(request, response);
                    break;
                case "checkout":
                    this.checkoutAction(request, response);
                    break;
                default:
                    this.defaultAction(request,response);
                    break;
            }
        }
    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("shoppingcart")) {
            int amount = Integer.parseInt(request.getParameter("articleAmount"));
            int id = Integer.parseInt(request.getParameter("articleId"));
            for(int i = 0; i < amount; i++) {
                try {
                    ArticleDTO article = newAricleManager.getArticleById(id);
                    newShoppingCartManager.addArticletoCart(article);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            HttpSession session = request.getSession(false);
            if(session != null) {
                session.setAttribute("shoppingCart", newShoppingCartManager.getCartDTO());
            }
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void checkoutAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.setAttribute("shoppingCart", newShoppingCartManager.getCartDTO());
        }
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

    private void adminAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ArrayList<ArticleDTO> stockList = newAricleManager.getArticleList();
        request.setAttribute("stocklist", stockList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
    //endregion

    //region Helper
    private void initShop() {
        newAricleManager = new ArticleManager();
        newAricleManager.setStockDB(ArticleFactoryDAO.getArticle(1));
        newShoppingCartManager = new ShoppingCartManager();
        newShoppingCartManager.setArticleManager(newAricleManager);
    }

    private int pseudoAutoIncrement() {
        ArrayList<ArticleDTO> currentStock;
        int lastIndex = 0;
        try {
            currentStock = this.newAricleManager.getArticleList();
            ArticleDTO article = currentStock.get(currentStock.size()-1);
            lastIndex = article.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastIndex+1;
    }

    private boolean checkForValues(HttpServletRequest request, HttpServletResponse response, String formAction) {
        boolean isValidForm = false;
        if(
                request.getParameter("articleName") != null &&
                        request.getParameter("articlePrice") != null &&
                        request.getParameter("articleAmount") != null ||
                        request.getParameter("articleAction").equals("delete")
        ) {
            isValidForm = true;
        }
        return isValidForm;
    }

    private void setResponseMessage(HttpServletRequest request, HttpServletResponse response, String _action) {

        String repsonseMessage = null;
        switch (_action) {
            case "add":
                repsonseMessage = "Ein Neuer Artikel wurde hinzugefuegt.";
                break;
            case "edit":
                repsonseMessage = "Der Artikel wurde erfolgreich bearbeitet";
                break;
            case"delte":
                repsonseMessage = "Der Artikel wurde erfolgreich gelöscht";
                break;
        }
        request.setAttribute("actionMessage", repsonseMessage);
    }
    //endregion

}
