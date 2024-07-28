package service;

import java.sql.SQLException;
import java.util.List;

import dao.CategorieDao;

import dto.CategorieDto;

import entity.CategorieEntity;

public class CategorieService {
    
    private CategorieDao categorieDao = new CategorieDao();

    public  void saveCategorie (CategorieDto categorieDto) throws ClassNotFoundException, SQLException {
        CategorieEntity categorieEntity = new CategorieEntity(
            categorieDto.getCategId(),
            categorieDto.getCategorie());

            categorieDao.saveCategorie(categorieEntity);
    }

    public List<CategorieDto> getAllCategories() throws ClassNotFoundException, SQLException {
               return categorieDao.getAllCategories();
    }

    public void deleteCategorie(String categId) throws ClassNotFoundException, SQLException {
        categorieDao.deleteCategorie(categId);
    }


}
