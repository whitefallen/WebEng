package presentation;

import logic.ArticleManager;
import logic.ShoppingCartManager;
import persistence.ArticleFactoryDAO;
import persistence.H2ArticleDAO;
import transferobject.ArticleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(urlPatterns ={ "/" })
public class ShopServlet extends HttpServlet {



    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().append("<html>");
        response.getWriter().append("<body>");
        response.getWriter().append("<main>");

        ArticleManager newAricleManager = new ArticleManager();
        ArticleFactoryDAO articleFactoryDAO = new ArticleFactoryDAO();
        newAricleManager.setStockDB(articleFactoryDAO.getArticle(2));
        ShoppingCartManager newShoppingCartManager = new ShoppingCartManager();
        newShoppingCartManager.setArticleManager(newAricleManager);

        try {
            newAricleManager.addArticle("ItemNr1", 100,10, 5.55f);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        try {
            //newAricleManager.removeArticle(5);
            ArrayList<ArticleDTO> stockList = newAricleManager.getArticleList();

            this.printArticleList(request,response,stockList);

            newShoppingCartManager.addArticletoCart(newAricleManager.getArticleById(100));
            newShoppingCartManager.addArticletoCart(newAricleManager.getArticleById(100));

            ArrayList<ArticleDTO> cartlist = newShoppingCartManager.getCurrentCart();
            this.printShoppingCart(request,response,cartlist);
            response.getWriter().append("Gesammt Summe: " + newShoppingCartManager.getSum());
            this.printArticleList(request,response,stockList);
            newShoppingCartManager.checkoutCart();
            cartlist = newShoppingCartManager.getCurrentCart();
            this.printShoppingCart(request,response,cartlist);
            response.getWriter().append("Gesammt Summe: " + newShoppingCartManager.getSum());
        }
        catch (SQLException e) {

        }

        response.getWriter().append("</main>");
        response.getWriter().append("</body>");
        response.getWriter().append("</html>");

    }

    private void printArticleList(HttpServletRequest request, HttpServletResponse response, ArrayList<ArticleDTO> stockList) throws ServletException, IOException {
        response.getWriter().append("<div>");
        response.getWriter().append("<h2>Artikelliste</h2>");


        for (ArticleDTO item : stockList ) {
            response.getWriter().append("Name: "+item.getName()+" Anzahl: "+item.getAmount()+" Preis: "+item.getPrice()+"<br>");
        }

        response.getWriter().append("</div>");
    }

    private void printShoppingCart(HttpServletRequest request, HttpServletResponse response, ArrayList<ArticleDTO> cartlist) throws ServletException, IOException {
        response.getWriter().append("<h2>Warenkorb</h2>");
        for (ArticleDTO item : cartlist ) {
            response.getWriter().append("Name: "+item.getName()+" Preis: "+item.getPrice()+"<br>");
        }
    }
}
