package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
     @FXML
    void btnAddBookOnAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
        System.out.println("Add Book");
        URL resource = this.getClass().getResource("/view/AddBooks.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent node = loader.load();

        
              // Get the controller instance
              BookController bookController = loader.getController();
              bookController.btnId(event); // Call the btnId method

        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Add Book Form");
        stage.show();

    }

    @FXML
    void btnAddMembersOnAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
        System.out.println("Add Members");
        URL resource = this.getClass().getResource("/view/AddMembers.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent node = loader.load();
        
        // Get the controller instance
        MemberController memberController = loader.getController();
        memberController.btnId(event); // Call the btnId method
        
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Add Member Form");
        stage.show();
    }
    @FXML
    void btnAllBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnAllMembersOnAction(ActionEvent event) {

    }

    @FXML
    void btnToBorrowBookOnAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        System.out.println("Add Members");
        URL resource = this.getClass().getResource("/view/ToBorrow.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent node = loader.load();
        
        ToBorrowController toBorrowController = loader.getController();
        
     //   toBorrowController.btnId(event);
        
       Stage stage = new Stage();
       stage.setScene(new Scene(node));
        stage.setTitle("To Borrow Book");
        stage.show();
   

    }

    @FXML
    void btnToReturnBookOnAction(ActionEvent event) {

    }
}
