package persistence;

import interfaces.ArticleDAO;

public class ArticleFactoryDAO {

    public static ArticleDAO getArticle(int _type) {
        ArticleDAO _article = null;
        switch(_type) {
            case 1:
                _article = new H2ArticleDAO();
                break;
            case 2:
                _article = new MockArticleDAO();
            default:
                break;
        }
        return _article;
    }

}
