package dto;

public class CategorieDto {
    private String categId;
    private String categorie;
    public CategorieDto() {
    }
    public CategorieDto(String categId, String categorie) {
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
        return "CategorieDto [categId=" + categId + ", categorie=" + categorie + "]";
    }

    
}
