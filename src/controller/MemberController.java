package controller;


import java.sql.SQLException;
import java.time.LocalDate;

import dto.MemberDto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.MemberService;

public class MemberController {

    private MemberService memberService = new MemberService();
    private IntegerProperty memberId = new SimpleIntegerProperty();
    private StringProperty memberIdText = new SimpleStringProperty();
    @FXML
    private TextField txtMemberAddress;

    @FXML
    private DatePicker txtMemberDOB;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtMemberName;

     @FXML
    private Label lblLastMemberId;
    @FXML

    void btnId(ActionEvent event) throws ClassNotFoundException, SQLException {
       // initialize();
        
        String lastId = memberService.getLastMemberId();
        lblLastMemberId.setText("Last Member ID: " + lastId);
        System.out.println("Last Member ID: " + lastId);
    }

   /*  @FXML
    void initialize() {
        // Bind the TextField to the memberIdText property
        txtMemberId.textProperty().bindBidirectional(memberIdText);

        // Bind the memberId property to memberIdText for conversion
        memberIdText.addListener((observable, oldValue, newValue) -> {
            try {
                memberId.set(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
                memberId.set(0);
            }
        });

        // Display the last member ID
        try {
            String lastId = memberService.getLastMemberId();
            lblLastMemberId.setText("Last Member ID: " + lastId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    void btnAddMember(ActionEvent event) throws ClassNotFoundException, SQLException {
        System.out.println("Button pressed: Save");
        String id = txtMemberId.getText();
        String name = txtMemberName.getText();
        LocalDate dob = txtMemberDOB.getValue();
        String address = txtMemberAddress.getText();
        
        System.out.println("ID : " + id);
        System.out.println("Name : " + name);
        System.out.println("DOB : " + dob);
        System.out.println("Address : " + address);
       // String lastId = memberService.getLastMemberId();
      //  lblLastMemberId.setText("Last Member ID: " + lastId);
      //  System.out.println("Last Member ID: " + lastId);

        MemberDto memberDto = new MemberDto(id, name, dob, address);
        memberService.addMember(memberDto);
        clearForm();
      // Update the last member ID
      btnId(event);

    }
    public void clearForm(){
        txtMemberId.setText("");
        txtMemberName.setText("");
        txtMemberDOB.setValue(null);
        txtMemberAddress.setText("");

    }

    void initialize() {
        // Bind the TextField to the memberIdText property
        txtMemberId.textProperty().bindBidirectional(memberIdText);

        // Bind the memberId property to memberIdText for conversion
        memberIdText.addListener((observable, oldValue, newValue) -> {
            try {
                memberId.set(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
                memberId.set(0);
            }
        });

        // Display the last member ID
        try {
            String lastId = memberService.getLastMemberId();
            lblLastMemberId.setText("Last Member ID: " + lastId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}