package persistence;


import java.util.ArrayList;

public class ShoppingCartDTO {

    private ArrayList<ArticleDTO> articleList = new ArrayList<>();

    private float sum = 0;


    public ArrayList<ArticleDTO> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<ArticleDTO> articleList) {
        this.articleList = articleList;
    }

    public void addArticleToList(ArticleDTO article) {
        this.articleList.add(article);
    }

    public void removeArticle(ArticleDTO article) {
        for(ArticleDTO _article : this.getArticleList()) {
            if(_article.getId() == article.getId()) {
                this.getArticleList().remove(_article);
                break;
            }
        }
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void checkoutCart() {
        this.setArticleList(new ArrayList<>());
    }
}
