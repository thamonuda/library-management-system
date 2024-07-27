package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.BookDto;

public class AllBookDao {


     public List<BookDto> getAllBooks() throws SQLException, ClassNotFoundException {
        List<BookDto> books = new ArrayList<>();
              System.out.println("all book");
          Connection connection = DBConnection.getInstance().getConnection();
           PreparedStatement statement = connection.prepareStatement("select * from book");
           ResultSet bookSet = statement.executeQuery();
           
           while (bookSet.next()) {
            books.add(new BookDto(
                bookSet.getString("bookID"),
                bookSet.getString("bookName"),
                bookSet.getString("categId"),
                bookSet.getInt("bookCount"),
                bookSet.getString("author")
            ));
        }

        return books;
    }

    public void deleteBook(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM book WHERE BookID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }
}
