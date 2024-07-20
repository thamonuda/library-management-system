package dao;

import db.DBConnection;
import dto.BookDto;
import dto.MemberDto;
import dto.ToBorrowDto;
import entity.ToBorrowEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ToBorrowDao {

    public MemberDto getMember(String membID) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM member WHERE MembID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, membID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            Date sqlDate = resultSet.getDate("DOB");
            LocalDate dob = sqlDate != null ? sqlDate.toLocalDate() : null;

            return new MemberDto(
                    resultSet.getString("MembID"),
                    resultSet.getString("MembName"),
                    dob,
                    resultSet.getString("MembAddress"));
        }
        return null;
    }

    public void saveBorrow(ToBorrowEntity toBorrowEntity) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO borrowdetail (BorrowID, BookID, MembID, CategID,IssuedDate,DueDate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, toBorrowEntity.getBorrowId());
        preparedStatement.setString(2, toBorrowEntity.getBookId());
        preparedStatement.setString(3, toBorrowEntity.getMemberId());
        preparedStatement.setString(4, toBorrowEntity.getCategoryId());
        preparedStatement.setDate(5, toBorrowEntity.getSqlIssuedDate());
        preparedStatement.setDate(6, toBorrowEntity.getSqlDueDate());
        preparedStatement.executeUpdate();
        // connection.close();
    }

    public BookDto getBook(String bookId) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM book WHERE BookID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bookId);
        ResultSet resultSet = preparedStatement.executeQuery();
     
               BookDto book = null;
               if (resultSet.next()) {
                String id = resultSet.getString("BookID");
                String name = resultSet.getString("BookName");
                String categId = resultSet.getString("CategID");
                int bookcount = resultSet.getInt("BookCount");
                String author = resultSet.getString("Author");
                book = new BookDto(id, name, categId, bookcount, author);
            }

        //resultSet.close();
      //  preparedStatement.close();
        return book;

    }

    public String getLastBorrowId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT MAX(Borrowid) FROM borrowdetail";
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