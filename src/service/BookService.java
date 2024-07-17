package service;

import java.sql.SQLException;

import dao.BookDao;

import dto.BookDto;

import entity.BookEntity;

public class BookService {

    private BookDao bookDAO = new BookDao();

    public  void addBook(BookDto bookDto) throws ClassNotFoundException, SQLException {
        BookEntity bookEntity = new BookEntity(
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getCategId(),
                bookDto.getBookcount(),
                bookDto.getAuthor());

        bookDAO.saveBook(bookEntity);
    }

    public String getLastMemberId() throws SQLException, ClassNotFoundException {
        return bookDAO.getLastBookId();
    }
}
