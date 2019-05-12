package interfaces;

import transferobject.ArticleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ArticleDAO {
    ArrayList<ArticleDTO> findAll() throws SQLException;
    ArticleDTO findById(int _id) throws SQLException;
    void insertArticle(String _name, int _id, int _amount, float _price) throws SQLException;
    void updateArticle(String _name, int _id, int _amount, float _price) throws SQLException;
    void deleteArticle(int _id) throws SQLException;
}
