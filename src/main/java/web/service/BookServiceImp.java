package web.service;

import org.springframework.stereotype.Service;
import web.dao.BookDAO;
import web.entity.Book;
import web.util.NoteResult;

import javax.annotation.Resource;
import java.util.List;

@Service("bookService")
public class BookServiceImp implements BookService{
    @Resource
    BookDAO bookDAO;
    @Override
    public NoteResult<List<Book>> loadBooks(String uid) {
        NoteResult<List<Book>> noteResult = new NoteResult<>();
        List<Book> books=bookDAO.findBookByUID(uid);
        noteResult.setStatus(0);
        noteResult.setMsg("查询笔记本成功！");
        noteResult.setData(books);
        return noteResult;
    }
}
