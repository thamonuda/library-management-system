package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dto.BookDto;
import dto.FinesDto;
import dto.ToReturnDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ToReturnService;

public class ToReturnController {

    ToReturnService toReturnService = new ToReturnService();
    FinesController finesController = new FinesController();

    @FXML
    private Label iblShowName;

    @FXML
    private Label iblShowName1;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBorrowId;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblIssuedDate;

    @FXML
    private Label lblMemberId;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblReturnedDate;

    @FXML
    private Label lblShowBookId;

    @FXML
    private Label lblShowBookName;

    @FXML
    private Label lblShowBorrowId;

    @FXML
    private Label lblShowDOB;

    @FXML
    private Label lblShowMemberId;

    @FXML
    private Label lblShowDueDate;

    @FXML
    private Label lblShowId;

    @FXML
    private Label lblShowIssuedDate;

    @FXML
    private Label lblShowReturnedDate;

    @FXML
    private TextField txtGetBorrowId;

    @FXML
    void btnReturnBook(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        System.out.println("sdfsdg");
        String name = "Search Borrow ID";

        if (lblShowBookId.getText().equals(name)) {
            System.out.println("if");
        } else {
            System.out.println("else");

            String borrowId = lblShowId.getText();
            String bookId = lblShowBookId.getText();
            String memberId = lblShowMemberId.getText();

            String returnDateStr = ((Label) lblShowReturnedDate).getText();
        
            LocalDate returnDate = LocalDate.parse(returnDateStr);
    
            Date sqlReturnDate = Date.valueOf(returnDate);

            System.out.println(borrowId + bookId + memberId + sqlReturnDate);

           ToReturnDto toReturnDto = new ToReturnDto(borrowId, bookId, memberId, sqlReturnDate);

           try {
            toReturnService.saveReturn(toReturnDto);
            getLateDays(borrowId);
           } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ""+e);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
            alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
            alert.show();
           }

           
             
         
            
            
            
        
            lblShowId.setText("");
            iblShowName1.setText("");
            lblShowMemberId.setText("");
            lblShowBookId.setText("Search Borrow ID");
            lblShowBookName.setText("Search Borrow ID");
            lblShowIssuedDate.setText("Search Borrow ID".toString());
            lblShowDueDate.setText("Search Borrow ID".toString());
            lblShowReturnedDate.setText("Search Borrow ID");


        }
    }

    private void getLateDays(String borrowId) throws ClassNotFoundException, SQLException, IOException{
       
        int lateDays = toReturnService.getLateDays(borrowId);
         FinesDto finesDto = new FinesDto();
         finesDto.setLateDays(lateDays);
       

         if(lateDays>0){
         
            System.out.println("Fines");
        URL resources = this.getClass().getResource("/view/Fines.fxml");
        FXMLLoader loaders = new FXMLLoader(resources);
        Parent nodes = loaders.load();
        Stage stages = new Stage();
        
        stages.setScene(new Scene(nodes));
        stages.setTitle("Fines");
        stages.show();



         }else{
                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No Late Days.Return Success");
           alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
           alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
           alert.show();
         }
         
        
    }

    @FXML
    void btnSearchId(ActionEvent event) {

        try {
            String id = txtGetBorrowId.getText();
            ToReturnDto book = toReturnService.getBook(id);

            if (book != null) {
                iblShowName1.setText(book.getMemberName());
                lblShowId.setText(id);
                lblShowMemberId.setText(book.getMemberId());
                lblShowBookId.setText(book.getBookId());
                lblShowBookName.setText(book.getBookName());
                lblShowIssuedDate.setText(book.getIssueDate().toString());
                lblShowDueDate.setText(book.getDueDate().toString());

                LocalDate currentDate = LocalDate.now();
                // Format the date if needed
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = currentDate.format(formatter);
                // Set the text of the label
                lblShowReturnedDate.setText(formattedDate);

                

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wrong Borrow ID.Search Again");
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
                alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                alert.show();
                lblShowId.setText("No Borrow ID");
                iblShowName1.setText("No Borrow ID");
                lblShowMemberId.setText("   *");
                lblShowBookId.setText("Search Borrow ID");
                lblShowBookName.setText("Search Borrow ID");
                lblShowIssuedDate.setText("Search Borrow ID".toString());
                lblShowDueDate.setText("Search Borrow ID".toString());
                lblShowReturnedDate.setText("Search Borrow ID");
            }
        } catch (Exception e) {
            lblShowId.setText("Wrong details.");
            iblShowName1.setText("Wrong details.");
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ""+e);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
            alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
            alert.show();
        }
        txtGetBorrowId.setText("");

    }
}
