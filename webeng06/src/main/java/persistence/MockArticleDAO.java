package persistence;

import interfaces.ArticleDAO;
import transferobject.ArticleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MockArticleDAO implements ArticleDAO {

    private ArrayList<ArticleDTO> stock = new ArrayList<>();


    public ArrayList<ArticleDTO> getStock() {
        return stock;
    }

    public void addToStock(ArticleDTO _article) {
        this.stock.add(_article);
    }

    public void removeFromStock(ArticleDTO _article) {
        for (ArticleDTO article : this.getStock()) {
            if(article.getId() == _article.getId()) {
                this.getStock().remove(article);
                break;
            }
        }
    }

    public ArticleDTO getArticleById(int _id) {
        ArticleDTO foundArticle = null;
        for (ArticleDTO article : this.getStock()) {
            if(article.getId() == _id) {
                foundArticle = article;
            }
        }
        return foundArticle;
    }

    @Override
    public ArrayList<ArticleDTO> findAll() throws SQLException {
        return this.getStock();
    }

    @Override
    public ArticleDTO findById(int _id) throws SQLException {
        return getArticleById(_id);
    }

    @Override
    public void insertArticle(String _name, int _id, int _amount, float _price) {
        this.addToStock(new ArticleDTO(_name, _id,_amount,_price));
    }

    @Override
    public void updateArticle(String _name, int _id, int _amount, float _price) {

    }

    @Override
    public void deleteArticle(int _id) {
        this.removeFromStock(this.getArticleById(_id));
    }
}
