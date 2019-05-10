package persistence;

import java.util.ArrayList;

public class MockArticleDAO {

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
}
