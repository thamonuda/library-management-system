package controller;

import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;

import dao.BookDao;
import dto.BookDto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.BookService;

public class BookController implements Initializable {

    private BookService bookService = new BookService();
        private IntegerProperty bookId = new SimpleIntegerProperty();
    private StringProperty bookIdText = new SimpleStringProperty();

    @FXML
    private TextField txtCategoryCode;
  
    @FXML
    private Label lblLastBookId;
    @FXML
    void btnId(ActionEvent event) throws ClassNotFoundException, SQLException {
       // initialize();
        
        String lastId = bookService.getLastBookId();
        lblLastBookId.setText("Last Member ID: " + lastId);
        System.out.println("Last Member ID: " + lastId);
    }

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookCount;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private ComboBox<String> categoryChoiceBox;

  //  private String[] abc = { "Music", "Science", "History", "Art", "Religion", "Novel", "Technology" };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCategoriesFromDatabase();
    }

    @FXML
    void btnSaveBook(ActionEvent event) throws ClassNotFoundException, SQLException {
        String code = getSelectedCategoryCode();
        System.out.println(code);
         System.out.println("Button pressed: Save");
        String id = txtBookId.getText();
        String name = txtBookName.getText();
        String categoryId = getSelectedCategoryCode();
        int bookcount = Integer.parseInt(txtBookCount.getText());
        String author = txtAuthor.getText();
        
        System.out.println("ID : " + id);
        System.out.println("Name : " + name);
        System.out.println("DOB : " + categoryId);
        System.out.println("Address : " + author);
       // String lastId = memberService.getLastMemberId();
      //  lblLastMemberId.setText("Last Member ID: " + lastId);
      //  System.out.println("Last Member ID: " + lastId);

        BookDto bookDto = new BookDto(id,name,categoryId,bookcount,author);
        try {
            bookService.addBook(bookDto);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        clearForm();
        btnId(event);
    }

    public String getSelectedCategoryCode() {
        String[] categoriesId = null;
        try {
             categoriesId = BookDao.getCategoriesId();
          
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
       }
   

        String[] categories = null;
        try {
             categories = BookDao.getCategories();
           
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectedItem = categoryChoiceBox.getSelectionModel().getSelectedItem();
        String code = "C000";
    for (int i = 0;i<categories.length;i++) {
        if (categories[i].equals(selectedItem)) {
            code = categoriesId[i];
            return code;
        } 
    }
        if (categories[2].equals(selectedItem)) {
            code = categoriesId[2];
            return code;
        } 
        
    
        return code;
    }
    private void loadCategoriesFromDatabase() {
        try {
            String[] categories = BookDao.getCategories();
            categoryChoiceBox.getItems().addAll(categories);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void clearForm(){
        txtBookId.setText("");
        txtBookName.setText("");
        txtBookCount.setText("");
        txtAuthor.setText("");

    }
    void initialize() {
        // Bind the TextField to the memberIdText property
        txtBookId.textProperty().bindBidirectional(bookIdText);

        // Bind the memberId property to memberIdText for conversion
        bookIdText.addListener((observable, oldValue, newValue) -> {
            try {
                bookId.set(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
               bookId.set(0);
            }
        });

        // Display the last member ID
        try {
            String lastId = bookService.getLastBookId();
            lblLastBookId.setText("Last Member ID: " + lastId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
