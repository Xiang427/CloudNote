package web.dao;

import web.entity.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> findBookByUID(String uid);
}
