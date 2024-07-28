package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import service.AllMemberService;

import tm.AllMemberTm;

public class AllMemberController {

    private AllMemberService allMemberService = new AllMemberService();

    @FXML
    private TableColumn<AllMemberTm, String> colMemberId;

    @FXML
    private TableColumn<AllMemberTm, String> colMemberName;

    @FXML
    private TableColumn<AllMemberTm, Date> colDOB;

    @FXML
    private TableColumn<AllMemberTm, String> colMemberAddress;

    @FXML
    private TableColumn<AllMemberTm, Button> colDelete;

    @FXML
    private TableView<AllMemberTm> tblAllMember;

    public void initialize() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colMemberAddress.setCellValueFactory(new PropertyValueFactory<>("memberAddress"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        try {
            loadAllMembers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllMembers() throws ClassNotFoundException, SQLException {
        List<MemberDto> members = allMemberService.getAllMembers(); // Assuming this method exists in AllBookService
        ObservableList<AllMemberTm> memberTmList = FXCollections.observableArrayList();

        for (MemberDto member : members) {
            Button button = new Button("Delete");
            button.getStyleClass().add("delete-button");
            button.setOnAction(event -> {
                // Show confirmation dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete "+ member.getName()  +" member?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            // Delete logic here

                            allMemberService.deleteMember(member.getId());
                            System.out.println(member.getId());
                            Alert alerts = new Alert(Alert.AlertType.CONFIRMATION, "Member Deleted Success.");
                            alerts.getDialogPane().getStylesheets()
                                    .add(getClass().getResource("/styles/alert.css").toExternalForm()); // Load the CSS
                                                                                                        // file
                            alerts.getDialogPane().getStyleClass().add("custom-alert"); // Apply the custom CSS class
                            alerts.show();

                            System.out.println("delete" + member.getId());
                            loadAllMembers(); // Refresh the table
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

            AllMemberTm memberTm = new AllMemberTm(
                    member.getId(),
                    member.getName(),
                    member.getDob(),
                    member.getAddress(),
                    button);

            memberTmList.add(memberTm);
        }
        tblAllMember.setItems(memberTmList);

    }
}
