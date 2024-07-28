package tm;


import java.time.LocalDate;

import javafx.scene.control.Button;

public class AllMemberTm {
    private String memberId;
    private String memberName;
    private LocalDate DOB;
    private String memberAddress;
    private Button btnDelete; 

    public AllMemberTm() {}

    public AllMemberTm(String memberId, String memberName, LocalDate DOB, String memberAddress, Button btnDelete) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.DOB = DOB;
        this.memberAddress = memberAddress;
        this.btnDelete = btnDelete; 
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "AllMemberTm [memberId=" + memberId + ", memberName=" + memberName + ", DOB=" + DOB + ", memberAddress=" + memberAddress + "]";
    }
}