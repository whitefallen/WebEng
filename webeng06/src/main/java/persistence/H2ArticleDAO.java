package persistence;

import interfaces.ArticleDAO;
import transferobject.ArticleDTO;
import transferobject.SqlConfiguration;

import java.sql.*;
import java.util.ArrayList;

public class H2ArticleDAO implements ArticleDAO {

    private Connection con = SqlConfiguration.sqlCon;


    @Override
    public ArrayList<ArticleDTO> findAll() throws SQLException {
        ArrayList<ArticleDTO> stock = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ARTIKEL");
        while (rs.next()) {
            ArticleDTO newArtikel = new ArticleDTO(rs.getString("name"),rs.getInt("id"),rs.getInt("amount"),rs.getFloat("price"));
            stock.add(newArtikel);
        }
        return stock;
    }

    @Override
    public ArticleDTO findById(int _id) throws SQLException {
        String selectSQL = "SELECT * FROM ARTIKEL WHERE ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
        preparedStatement.setInt(1, _id);
        ResultSet rs = preparedStatement.executeQuery();
        ArticleDTO artikel = null;
        while (rs.next()) {
            artikel = new ArticleDTO(rs.getString("name"),rs.getInt("id"),rs.getInt("amount"),rs.getFloat("price"));
        }
        return artikel;
    }

    @Override
    public void insertArticle(String _name, int _id, int _amount, float _price) throws SQLException {
        String selectSQL = "INSERT INTO ARTIKEL VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
        preparedStatement.setString(2, _name);
        preparedStatement.setInt(1, _id);
        preparedStatement.setInt(4, _amount);
        preparedStatement.setFloat(3, _price);
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateArticle(String _name, int _id, int _amount, float _price) throws SQLException {
        String selectSQL = "UPDATE ARTIKEL SET NAME=?,PRICE=?,AMOUNT=? WHERE ID=?";
        PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
        preparedStatement.setString(1, _name);
        preparedStatement.setInt(3, _amount);
        preparedStatement.setFloat(2, _price);
        preparedStatement.setInt(4, _id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteArticle(int _id) throws SQLException {
        String selectSQL = "DELETE FROM ARTIKEL WHERE id=?";
        PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
        preparedStatement.setInt(1, _id);
        preparedStatement.executeUpdate();
    }

}
