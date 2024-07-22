package service;

import java.sql.SQLException;

import dao.ToBorrowDao;
import dto.BookDto;
import dto.MemberDto;
import dto.ToBorrowDto;

import entity.ToBorrowEntity;

public class ToBorrowService {

    private ToBorrowDao toBorrowDao = new ToBorrowDao();
    // private ToBorrowDto toBorrowDto = new ToBorrowDto();

    public MemberDto getMember(String membID) throws SQLException, ClassNotFoundException {
        System.out.println(membID);
        return toBorrowDao.getMember(membID);
    }

    public void saveBorrow(ToBorrowDto toBorrowDto) throws ClassNotFoundException, SQLException {
        ToBorrowEntity toBorrowEntity = new ToBorrowEntity(
                toBorrowDto.getBorrowId(),
                toBorrowDto.getBookId(),
                toBorrowDto.getMemberId(),
                toBorrowDto.getSqlIssuedDate(),
                toBorrowDto.getSqlDueDate(),
                toBorrowDto.getCategoryId());

        toBorrowDao.saveBorrow(toBorrowEntity);
    }

    // public ToBorrowDto getBookId(ToBorrowDto toBorrowDto2){

    // return toBorrowDao.getBookName(toBorrowDto2);
    // }

    public BookDto getBook(String bookID) throws SQLException, ClassNotFoundException {

        return toBorrowDao.getBook(bookID);
    }

    public String getLastBorrowId() throws SQLException, ClassNotFoundException {
        return toBorrowDao.getLastBorrowId();
    }

}
