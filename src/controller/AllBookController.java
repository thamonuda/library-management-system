package controller;

import java.sql.SQLException;
import java.util.List;

import dto.BookDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.AllBookService;
import tm.AllBookTm;

public class AllBookController {

    private AllBookService allBookService = new AllBookService();

    @FXML
    private TableColumn<AllBookTm, String> colBookId;

    @FXML
    private TableColumn<AllBookTm, String> colBookName;

    @FXML
    private TableColumn<AllBookTm, String> colCategId;

    @FXML
    private TableColumn<AllBookTm, Integer> colBookCount;

    @FXML
    private TableColumn<AllBookTm, String> colAuthor;

    @FXML
    private TableColumn<AllBookTm, Button> colDelete;

    @FXML
    private TableView<AllBookTm> tblAllBook;

    public void initialize() {
        System.out.println("Initializing table");

        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategId.setCellValueFactory(new PropertyValueFactory<>("categId"));
        colBookCount.setCellValueFactory(new PropertyValueFactory<>("bookcount"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        try {
            loadAllBooks();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllBooks() throws ClassNotFoundException, SQLException {
        List<BookDto> books = allBookService.getAllBooks();
        ObservableList<AllBookTm> bookTmList = FXCollections.observableArrayList();

        for (BookDto book : books) {
            Button button = new Button("Delete");
            button.setOnAction(event -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this book?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                          
                            allBookService.deleteBook(book.getId()); 

                            Alert alerts = new Alert(Alert.AlertType.CONFIRMATION, "Book Deleted Success.");
                            alerts.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
                            alerts.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                            alerts.show();

                         System.out.println("delete"+book.getId());
                            loadAllBooks(); 
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            Alert alerts = new Alert(Alert.AlertType.CONFIRMATION, ""+e);
                            alerts.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
                            alerts.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                            alerts.show();
                        }
                    }
                });



            });

            AllBookTm bookTm = new AllBookTm(
                    book.getId(),
                    book.getName(),
                    book.getCategId(),
                    book.getBookcount(),
                    book.getAuthor(),
                    button);

            bookTmList.add(bookTm);
        }
        tblAllBook.setItems(bookTmList);
    }
}