package controller;

import java.sql.SQLException;
import java.util.List;


import dto.CategorieDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.CategorieService;

import tm.CategorieTm;
import javafx.scene.control.TableView;

public class CategorieController {

    CategorieService categorieService = new CategorieService();

    @FXML
    private TableColumn<CategorieTm, String> colCategorieId;

    @FXML
    private TableColumn<CategorieTm, String> colCategorieName;

    @FXML
    private TableColumn<CategorieTm, Button> colDelete;

    @FXML
    private TableView<CategorieTm> tblCategorie;

    @FXML
    private TextField txtCategName;

    @FXML
    private TextField txtCategId;

    public void initialize() {
        colCategorieId.setCellValueFactory(new PropertyValueFactory<>("categId"));
        colCategorieName.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        try {
            loadAllCategories();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCategories() throws ClassNotFoundException, SQLException {
        List<CategorieDto> categories = categorieService.getAllCategories();
        ObservableList<CategorieTm> categorieTmList = FXCollections.observableArrayList();

        for (CategorieDto categorie : categories) {
            Button button = new Button("Delete");
            button.getStyleClass().add("delete-button");

            button.setOnAction(event -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure you want to delete " + categorie.getCategorie() + " categorie?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {

                            categorieService.deleteCategorie(categorie.getCategId());

                            Alert alerts = new Alert(Alert.AlertType.CONFIRMATION, "Categorie Deleted Success.");
                            alerts.getDialogPane().getStylesheets()
                                    .add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS
                                                                                                        // file
                            alerts.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                            alerts.show();

                            loadAllCategories();
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();

                            Alert alerts = new Alert(Alert.AlertType.CONFIRMATION, "" + e);
                            alerts.getDialogPane().getStylesheets()
                                    .add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS
                                                                                                        // file
                            alerts.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                            alerts.show();
                        }
                    }
                });
            });

            CategorieTm categorieTm = new CategorieTm(
                    categorie.getCategId(),
                    categorie.getCategorie(),
                    button);

            categorieTmList.add(categorieTm);
        }
        tblCategorie.setItems(categorieTmList);
    }

    @FXML
    void btnSave(ActionEvent event) {
        System.out.println("button");
        String categId = txtCategId.getText();
        String categorie = txtCategName.getText();
        try {
            CategorieDto categorieDto = new CategorieDto();
            categorieDto.setCategId(categId);
            categorieDto.setCategorie(categorie);
            categorieService.saveCategorie(categorieDto);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Categorie Save Success.");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load
                                                                                                                      // the
                                                                                                                      // CSS
                                                                                                                      // file
            alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
            alert.show();

        } catch (Exception e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "" + e);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load
                                                                                                                      // the
                                                                                                                      // CSS
                                                                                                                      // file
            alert.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
            alert.show();
        }

        clearForm();
    }

    public void clearForm() {
        txtCategId.setText(null);
        txtCategName.setText(null);
    }
}
