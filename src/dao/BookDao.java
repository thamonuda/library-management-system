package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.BookEntity;


public class BookDao {

     public static String[] getCategories() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT Categories FROM categorie";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        List<String> categories = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            categories.add(resultSet.getString("Categories"));
        }
        return categories.toArray(new String[0]);

    }
    public static String[] getCategoriesId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT CategID FROM categorie";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        List<String> categoriesId = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            categoriesId.add(resultSet.getString("CategID"));
        }
        return categoriesId.toArray(new String[0]);

    }

        public void saveBook(BookEntity BookEntity) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO book (BookID, BookName, CategID, BookCount,Author) VALUES (?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, BookEntity.getId());
        preparedStatement.setString(2, BookEntity.getName());
        preparedStatement.setString(3,BookEntity.getCategId());
        preparedStatement.setInt(4, BookEntity.getBookcount());
        preparedStatement.setString(5, BookEntity.getAuthor());
        preparedStatement.executeUpdate();
        // connection.close();
    }

    public String getLastBookId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT MAX(Bookid) FROM book";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        String lastId = "f";
        if (resultSet.next()) {
            lastId = resultSet.getString(1);
        }
        // connection.close();
        return lastId;
    }
    
}
