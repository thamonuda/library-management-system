package service;

import java.sql.SQLException;

import dao.ToReturnDao;
import dto.ToReturnDto;

import entity.ToReturnEntity;

public class ToReturnService {

    ToReturnDao toReturnDao = new ToReturnDao();
    
    public ToReturnDto getBook(String bookID) throws SQLException, ClassNotFoundException {

        return toReturnDao.getBook(bookID);
    }

    public void saveReturn(ToReturnDto toReturnDto) throws ClassNotFoundException, SQLException{
          
        ToReturnEntity toReturnEntity = new ToReturnEntity(
            toReturnDto.getBorrowId(),
            toReturnDto.getBookId(),
            toReturnDto.getMemberId(),
            toReturnDto.getReturnDate());
        
         toReturnDao.saveReturn(toReturnEntity);
    }

    public int getLateDays(String borrowId) throws ClassNotFoundException, SQLException {
       return toReturnDao.getLateDays(borrowId);
    }

}
