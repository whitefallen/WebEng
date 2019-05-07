package logic;

import persistence.ArticleDTO;
import persistence.MockArticleDAO;

import java.util.ArrayList;

public class ArticleManager {


    private MockArticleDAO stockDB;


    public ArticleManager(MockArticleDAO _stockDB) {
        this.setStockDB(_stockDB);
    }

    public void addArticle(String _name, int _id, int _amount, float _price) {
        this.stockDB.addToStock(new ArticleDTO(_name, _id, _amount, _price));
    }

    public void removeArticle(int _id) {
        ArticleDTO article = this.stockDB.getArticleById(_id);
        if(article != null) {
            this.stockDB.removeFromStock(article);
        }
    }

    public void setStockDB(MockArticleDAO stockDB) {
        this.stockDB = stockDB;
    }

    public ArrayList<ArticleDTO> getArticleList() {
        return this.stockDB.getStock();
    }

    public ArticleDTO getArticleById(int _id) {
       return this.stockDB.getArticleById(_id);
    }
}
