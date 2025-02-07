package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.BookDao;
import dto.BookDto;
import dto.MemberDto;
import dto.ToBorrowDto;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import service.ToBorrowService;

public class ToBorrowController implements Initializable {

  //  private IntegerProperty borrowId = new SimpleIntegerProperty();
   // private StringProperty borrowIdText = new SimpleStringProperty();

    private ToBorrowService toBorrowService = new ToBorrowService();

    @FXML
    private Button btnBorrowBook;

    @FXML
    private Label lblCId;

    @FXML
    private Label iblShowName;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblShowBookName;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblBorrowId;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblId;

    @FXML
    private Label lblIssuedDate;

    @FXML
    private Label lblIssuedDateShow;

    @FXML
    private Label lblLastBorrowId;

    @FXML
    private Label lblMemberId;

    @FXML
    private Label lblMemberIdShow;

    @FXML
    private Label lblName;

    @FXML
    private Label lblShowDOB;

    @FXML
    private Label lblShowId;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBorrowId;

    @FXML
    private DatePicker txtDueDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label iblgetcategory;

    @FXML
    private TextField txtShowId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize categories
        loadCategoriesFromDatabase();

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Format the date if needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        // Set the text of the label
        lblIssuedDateShow.setText(formattedDate);

        // Initialize the clock
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblTime.setText(LocalTime.now().format(timeFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();

        // Bind properties
        /*
         * txtBorrowId.textProperty().bindBidirectional(borrowIdText);
         * 
         * // Bind the borrowId property to borrowIdText for conversion
         * borrowIdText.addListener((observable, oldValue, newValue) -> {
         * try {
         * borrowId.set(Integer.parseInt(newValue));
         * } catch (NumberFormatException e) {
         * borrowId.set(0);
         * }
         * });
         */

        // Display the last borrow ID
        try {
            String lastId = toBorrowService.getLastBorrowId();
            lblLastBorrowId.setText("Last Borrow ID: " + lastId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveBook(ActionEvent event) throws ClassNotFoundException, SQLException {
        System.out.println("save");
        String borrowId = txtBorrowId.getText();
        String bookId = txtBookId.getText();
        String memberId = lblMemberIdShow.getText();
        LocalDate issuedDate = LocalDate.now();
        LocalDate dueDate = txtDueDate.getValue();
        String categoryId = lblCId.getText();

        Date sqlIssuedDate = Date.valueOf(issuedDate);
        Date sqlDueDate = Date.valueOf(dueDate);

        System.out.println(borrowId);
        System.out.println(bookId);
        System.out.println(memberId);
        System.out.println(categoryId);
        System.out.println(sqlIssuedDate);
        System.out.println(sqlDueDate);

        ToBorrowDto toBorrowDto = new ToBorrowDto(borrowId, bookId, memberId, sqlIssuedDate, sqlDueDate, categoryId);
        try {
            toBorrowService.saveBorrow(toBorrowDto);
                                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Book Save Success");
           alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
           alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
           alert.show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ""+e);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
            alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
            alert.show();
        }
        clearForm();
        btnId(event);
    }

    @FXML
    void btnSearchId(ActionEvent event) throws ClassNotFoundException, SQLException {

        try {

            String id = txtShowId.getText();
            System.out.println(id);
            MemberDto member = toBorrowService.getMember(id);
            System.out.println(id);

            if (member != null) {
                iblShowName.setText(member.getName());
                lblShowId.setText(member.getId());
                lblShowDOB.setText(member.getDob().toString());
                lblMemberIdShow.setText(member.getId());
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No Member This ID.Search Again");
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
                alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                alert.show();
                lblShowId.setText("Not found!");
                iblShowName.setText(" ");
                lblShowDOB.setText(" ");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ""+e);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
            alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
            alert.show();
            lblShowId.setText("Wrong details.");
            iblShowName.setText(" ");
            lblShowDOB.setText(" ");
            System.out.println(e);
        }

        txtShowId.setText("");
    }

    private String[] loadCategoriesFromDatabase() {
        try {
            String[] categories = BookDao.getCategories();
            return categories;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    void btnSearchBookId(ActionEvent event) {

        try {
            String id = txtBookId.getText();
            BookDto book = toBorrowService.getBook(id);

            if (book != null) {
                lblShowBookName.setText(book.getName());
                lblCId.setText(book.getCategId());
                iblgetcategory.setText(returnCategoryName(book.getCategId()));
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No Book.Search Again");
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
                alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                alert.show();
                lblShowBookName.setText("No Book");
                iblgetcategory.setText("");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ""+e);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS file
            alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
            alert.show();
            lblShowBookName.setText("Wrong details.");
            System.out.println(e);
        }
    }

    public void clearForm() {
        txtBorrowId.setText("");
        txtBookId.setText("");
        lblMemberIdShow.setText("Search Member ID");
        lblShowBookName.setText("Search Book ID");
        txtDueDate.setValue(null);
        lblShowId.setText("Search Book ID");
        iblShowName.setText("");
        lblShowDOB.setText("");
        iblgetcategory.setText("Search Book ID");
        lblCId.setText("");
    }

    public String returnCategoryName(String id) throws ClassNotFoundException, SQLException {

        String[] categoriesId = BookDao.getCategoriesId();
        String[] categories = loadCategoriesFromDatabase();

        for (int i = 0; i < categories.length; i++) {
            if (categoriesId[i].equals(id)) {
                return categories[i];
            }
        }
        return null;
    }

    void btnId(ActionEvent event) throws ClassNotFoundException, SQLException {
        String lastId = toBorrowService.getLastBorrowId();
        lblLastBorrowId.setText("Last Borrow ID: " + lastId);
    }
}