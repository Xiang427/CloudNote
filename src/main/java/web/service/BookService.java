package web.service;

import web.entity.Book;
import web.util.NoteResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookService {
    public NoteResult<List<Book>> loadBooks(String uid);
    public NoteResult<Book> addBook(String bookName,String userId);
}
