package controller;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
     @FXML
    void btnAddBookOnAction(ActionEvent event) throws IOException{


    }

    @FXML
    void btnAddMembersOnAction(ActionEvent event) throws IOException{
        System.out.println("Add Members");
        URL resource = this.getClass().getResource("/view/AddMembers.fxml");
        Parent node = FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.show();
        stage.setTitle("Add Memeber Form");
    }

    @FXML
    void btnAllBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnAllMembersOnAction(ActionEvent event) {

    }

    @FXML
    void btnToBorrowBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnToReturnBookOnAction(ActionEvent event) {

    }
}
