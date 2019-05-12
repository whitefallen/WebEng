package logic;

import transferobject.ArticleDTO;
import transferobject.ShoppingCartDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShoppingCartManager {


    private ShoppingCartDTO cartDTO = new ShoppingCartDTO();
    private ArticleManager articleManager = null;

    public void addArticletoCart(ArticleDTO _article) throws SQLException {
        this.articleManager.updateAddAmount(_article);
        cartDTO.addArticleToList(_article);
        this.updateAddSum(_article.getPrice());
    }

    public void removeArticleFronCart(ArticleDTO _article) throws SQLException {
        this.articleManager.updateRemoveAmount(_article);
        cartDTO.removeArticle(_article);
        this.updateRemoveSum(_article.getPrice());
    }


    public void setArticleManager(ArticleManager _manager) {
        this.articleManager = _manager;
    }

    public void checkoutCart() {
        this.cartDTO.checkoutCart();
        this.cartDTO.setSum(0f);
    }

    public ArrayList<ArticleDTO> getCurrentCart() {
        return cartDTO.getArticleList();
    }

    public void updateAddSum(float _price) {
        float currentSum = cartDTO.getSum();
        cartDTO.setSum(currentSum + _price);
    }

    public void updateRemoveSum(float _price) {
        float currentSum = cartDTO.getSum();
        cartDTO.setSum(currentSum - _price);
    }

    public float getSum() {
        return this.cartDTO.getSum();
    }
}
