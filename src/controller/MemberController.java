package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import dto.MemberDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.MemberService;

public class MemberController {

    private MemberService memberService = new MemberService();
    
    @FXML
    private TextField txtMemberAddress;

    @FXML
    private DatePicker txtMemberDOB;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtMemberName;

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

        MemberDto memberDto = new MemberDto(id, name, dob, address);
        memberService.addMember(memberDto);
        clearForm();

    }
    public void clearForm(){
        txtMemberId.setText("");
        txtMemberName.setText("");
        txtMemberDOB.setValue(null);
        txtMemberAddress.setText("");

    }
}