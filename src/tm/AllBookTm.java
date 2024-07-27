package tm;

import javafx.scene.control.Button;

public class AllBookTm {
    private String id;
    private String Name;
    private String categId;
    private int bookcount;
    private String author;
    private Button btnDelete;
    public AllBookTm() {
    }
    public AllBookTm(String id, String name, String categId, int bookcount, String author, Button btnDelete) {
        this.id = id;
        Name = name;
        this.categId = categId;
        this.bookcount = bookcount;
        this.author = author;
        this.btnDelete = btnDelete;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getCategId() {
        return categId;
    }
    public void setCategId(String categId) {
        this.categId = categId;
    }
    public int getBookcount() {
        return bookcount;
    }
    public void setBookcount(int bookcount) {
        this.bookcount = bookcount;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Button getBtnDelete() {
        return btnDelete;
    }
    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
    @Override
    public String toString() {
        return "AllBookTm [id=" + id + ", Name=" + Name + ", categId=" + categId + ", bookcount=" + bookcount
                + ", author=" + author + ", btnDelete=" + btnDelete + "]";
    }

    
}
