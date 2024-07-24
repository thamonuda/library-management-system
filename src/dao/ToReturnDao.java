package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import dto.BookDto;
import dto.ToReturnDto;
import entity.ToBorrowEntity;
import entity.ToReturnEntity;

public class ToReturnDao {

    public ToReturnDto getBook(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT \r\n" + //
                "    bd.MembID,\r\n" + //
                "    m.MembName,\r\n" + //
                "    bd.BookID,\r\n" + //
                "    b.BookName,\r\n" + //
                "    bd.IssuedDate,\r\n" + //
                "    bd.DueDate\r\n" + //
                "FROM \r\n" + //
                "    BorrowDetail bd\r\n" + //
                "JOIN \r\n" + //
                "    Member m ON bd.MembID = m.MembID\r\n" + //
                "JOIN \r\n" + //
                "    Book b ON bd.BookID = b.BookID\r\n" + //
                "WHERE \r\n" + //
                "    bd.BorrowID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        ToReturnDto book = null;
        if (resultSet.next()) {
            String memberId = resultSet.getString("MembID");
            String memberName = resultSet.getString("MembName");
            String bookId = resultSet.getString("BookID");
            String bookName = resultSet.getString("BookName");
            Date issueDate = resultSet.getDate("IssuedDate");
            Date dueDate = resultSet.getDate("DueDate");
            book = new ToReturnDto(memberId, memberName, bookId, bookName, issueDate,dueDate);
        }


        return book;
    }


    public void saveReturn(ToReturnEntity toReturnEntity) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO ReturnedDetail (BorrowID, BookID, MembID, ReturnedDate) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setString(1, toReturnEntity.getBorrowId());
        preparedStatement.setString(2, toReturnEntity.getBookId());
        preparedStatement.setString(3, toReturnEntity.getMemberId());
        preparedStatement.setDate(4, toReturnEntity.getReturnDate());
        preparedStatement.executeUpdate();
    }


    public int getLateDays(String borrowId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT LateDays FROM returneddetail WHERE BorrowID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, borrowId);
        ResultSet resultSet = preparedStatement.executeQuery();
    
        int lateDays = 0;
        if (resultSet.next()) {
            lateDays = resultSet.getInt("LateDays");
        }
    
       // resultSet.close();
        //preparedStatement.close();
    
        return lateDays;
    }
}
