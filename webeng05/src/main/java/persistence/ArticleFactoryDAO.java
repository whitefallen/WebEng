package persistence;

public class ArticleFactoryDAO {

    public static ArticleDAO getArticle(int _type) {
        ArticleDAO _article = null;
        switch(_type) {
            case 1:
                _article = new H2ArticleDAO();
                break;
            case 2:
            default:
                _article = new MockArticleDAO();
                break;
        }
        return _article;
    }

}
