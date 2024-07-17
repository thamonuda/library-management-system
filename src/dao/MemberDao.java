package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import db.DBConnection;
import entity.MemberEntity;

public class MemberDao {

    public void saveMember(MemberEntity memberEntity) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO member (MembID, MembName, DOB, MembAddress) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, memberEntity.getId());
        preparedStatement.setString(2, memberEntity.getName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(memberEntity.getDob()));
        preparedStatement.setString(4, memberEntity.getAddress());
        preparedStatement.executeUpdate();
        // connection.close();
    }

    public String getLastMemberId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT MAX(Membid) FROM member";
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