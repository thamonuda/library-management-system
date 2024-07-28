package entity;

public class CategorieEntity {
    private String categId;
    private String categorie;
    public CategorieEntity() {
    }
    public CategorieEntity(String categId, String categorie) {
        this.categId = categId;
        this.categorie = categorie;
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
    @Override
    public String toString() {
        return "CategorieEntity [categId=" + categId + ", categorie=" + categorie + "]";
    }

    
}
