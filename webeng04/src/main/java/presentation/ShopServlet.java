package presentation;

import logic.ArticleManager;
import logic.ShoppingCartManager;
import persistence.ArticleDTO;
import persistence.MockArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        MockArticleDAO newStockDB = new MockArticleDAO();
        ArticleManager newAricleManager = new ArticleManager(newStockDB);
        ShoppingCartManager newShoppingCartManager = new ShoppingCartManager();


        newAricleManager.addArticle("ItemNr1", 1,10, 5.55f);
        newAricleManager.addArticle("ItemNr2", 2,10, 25.55f);
        newAricleManager.addArticle("ItemNr3", 3,10, 35.55f);
        newAricleManager.addArticle("ItemNr4", 4,10, 45.55f);
        newAricleManager.addArticle("ItemNr5", 5,10, 55.55f);
        newAricleManager.addArticle("ItemNr6", 6,10, 65.55f);

        newAricleManager.removeArticle(5);
        ArrayList<ArticleDTO> stockList = newAricleManager.getArticleList();

        this.printArticleList(request,response,stockList);

        newShoppingCartManager.addArticletoCart(newAricleManager.getArticleById(2));
        newShoppingCartManager.addArticletoCart(newAricleManager.getArticleById(2));
        newShoppingCartManager.addArticletoCart(newAricleManager.getArticleById(6));

        ArrayList<ArticleDTO> cartlist = newShoppingCartManager.getCurrentCart();
        response.getWriter().append("<h2>Warenkorb</h2>");
        for (ArticleDTO item : cartlist ) {
            response.getWriter().append("Name: "+item.getName()+" Preis: "+item.getPrice()+"<br>");
        }
        response.getWriter().append("Gesammt Summe: " + newShoppingCartManager.getSum());


        this.printArticleList(request,response,stockList);


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
}
