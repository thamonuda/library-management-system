package entity;

import java.sql.Date;

public class ToBorrowEntity {
    
     public static ToBorrowEntity toBorrowEntity;
    private String borrowId;
     private String bookId;
     private String memberId;
     private Date sqlIssuedDate;
     private Date sqlDueDate;
     private String categoryId;
     private String bookName;
     
    public ToBorrowEntity() {
    }

    

    public ToBorrowEntity(String borrowId, String bookId, String memberId, Date sqlIssuedDate, Date sqlDueDate,
            String categoryId) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.sqlIssuedDate = sqlIssuedDate;
        this.sqlDueDate = sqlDueDate;
        this.categoryId = categoryId;
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

    public Date getSqlIssuedDate() {
        return sqlIssuedDate;
    }

    public void setSqlIssuedDate(Date sqlIssuedDate) {
        this.sqlIssuedDate = sqlIssuedDate;
    }

    public Date getSqlDueDate() {
        return sqlDueDate;
    }

    public void setSqlDueDate(Date sqlDueDate) {
        this.sqlDueDate = sqlDueDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookName(){
        return bookName;
    }
    public void setBookName(String bookName){
        this.bookName=bookName;
    }

    @Override
    public String toString() {
        return "ToBorrowDto [borrowId=" + borrowId + ", bookId=" + bookId + ", memberId=" + memberId
                + ", sqlIssuedDate=" + sqlIssuedDate + ", sqlDueDate=" + sqlDueDate + ", categoryId=" + categoryId
                + "]";
    }

     
    


}
