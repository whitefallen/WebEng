package logic;

import persistence.ArticleDAO;
import transferobject.ArticleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleManager {


    private ArticleDAO stockDB;


    public void addArticle(String _name, int _id, int _amount, float _price) throws SQLException {
        this.stockDB.insertArticle(_name,_id,_amount,_price);
    }

    public void removeArticle(int _id) throws SQLException {
        this.stockDB.deleteArticle(_id);
    }

    public ArrayList<ArticleDTO> getArticleList() throws SQLException {
        return this.stockDB.findAll();
    }

    public ArticleDTO getArticleById(int _id) throws SQLException {
        return this.stockDB.findById(_id);
    }

    public void updateAddAmount(ArticleDTO article) throws SQLException {
        this.stockDB.updateArticle(article.getName(),article.getId(),article.getAmount()-1,article.getPrice());
    }

    public void updateRemoveAmount(ArticleDTO article) throws SQLException {
        this.stockDB.updateArticle(article.getName(),article.getId(),article.getAmount()+1,article.getPrice());
    }

    public void setStockDB(ArticleDAO stockDB) {
        this.stockDB = stockDB;
    }
}
