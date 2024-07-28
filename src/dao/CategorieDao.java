package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

import dto.CategorieDto;
import entity.CategorieEntity;


public class CategorieDao {
    

    public void saveCategorie(CategorieEntity categorieEntity) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO categorie (CategID,Categories) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, categorieEntity.getCategId());
        preparedStatement.setString(2, categorieEntity.getCategorie());

        preparedStatement.executeUpdate();
        // connection.close();
    }

    public List<CategorieDto> getAllCategories() throws ClassNotFoundException, SQLException {
          List<CategorieDto> categories = new ArrayList<>();
              System.out.println("all categories");
          Connection connection = DBConnection.getInstance().getConnection();
           PreparedStatement statement = connection.prepareStatement("select * from categorie");
           ResultSet categorieSet = statement.executeQuery();

           while (categorieSet.next()) {
            categories.add(new CategorieDto(
                categorieSet.getString("CategID"),
                categorieSet.getString("Categories")
            ));
        }

        return categories;
    }

    public void deleteCategorie(String categId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM categorie WHERE CategID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, categId);
        preparedStatement.executeUpdate();
    }

}
