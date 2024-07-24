package entity;

import java.sql.Date;

public class ToReturnEntity {
    private String borrowId;
    private String bookId;
    private String memberId;
    private Date returnDate;
    private String memberName;
    private String bookName;
    private Date issueDate;
    private Date dueDate;

    public ToReturnEntity() {
    }

    public ToReturnEntity(String borrowId, String bookId, String memberId, Date returnDate) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.returnDate = returnDate;
    }

    public ToReturnEntity(String memberId2, String memberName2, String bookId2, String bookName2, Date issueDate2,
            Date dueDate2) {
                this.memberName = memberName2;
                this.bookId = bookId2;
                this.memberId = memberId2;
                this.bookName = bookName2;
                this.issueDate = issueDate2;
                this.dueDate = dueDate2;


    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "ToReturnDao [borrowId=" + borrowId + ", bookId=" + bookId + ", memberId=" + memberId + ", returnDate="
                + returnDate + "]";
    }

}
