package tm;

import javafx.scene.control.Button;

public class CategorieTm {
    private String categId;
    private String categorie;
   private Button btnDelete;
public CategorieTm() {
}
public CategorieTm(String categId, String categorie, Button btnDelete) {
    this.categId = categId;
    this.categorie = categorie;
    this.btnDelete = btnDelete;
}
public String getCategId() {
    return categId;
}
public void setCategId(String categId) {
    this.categId = categId;
}
public String getCategorie() {
    return categorie;
}
public void setCategorie(String categorie) {
    this.categorie = categorie;
}
public Button getBtnDelete() {
    return btnDelete;
}
public void setBtnDelete(Button btnDelete) {
    this.btnDelete = btnDelete;
}
@Override
public String toString() {
    return "CategorieTm [categId=" + categId + ", categorie=" + categorie + ", btnDelete=" + btnDelete + "]";
}

   
    

}
