package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.BookDto;
import dto.MemberDto;

public class AllMemberDao {

    public List<MemberDto> getAllMembers() throws ClassNotFoundException, SQLException {

        List<MemberDto> members = new ArrayList<>();
              System.out.println("all member");
          Connection connection = DBConnection.getInstance().getConnection();
           PreparedStatement statement = connection.prepareStatement("select * from member");
           ResultSet memberSet = statement.executeQuery();

           while (memberSet.next()) {
            members.add(new MemberDto(
                memberSet.getString("MembID"),
                memberSet.getString("MembName"),
                memberSet.getDate("DOB").toLocalDate(),
                memberSet.getString("MembAddress")
            ));
        }



        return members;
    }

    public void deleteMember(String id) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM member WHERE MembID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }
    
}
