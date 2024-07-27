package service;

import java.sql.SQLException;
import java.util.List;

import dao.AllBookDao;
import dto.BookDto;

public class AllBookService {



    private AllBookDao allBookDao = new AllBookDao();
        public List<BookDto> getAllBooks() throws SQLException, ClassNotFoundException {

        return allBookDao.getAllBooks();
    }
        public void deleteBook(String id) throws ClassNotFoundException, SQLException {
              allBookDao.deleteBook(id);
        }
}
