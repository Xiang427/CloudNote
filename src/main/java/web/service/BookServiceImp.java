package web.service;

import org.springframework.stereotype.Service;
import web.dao.BookDAO;
import web.entity.Book;
import web.util.NoteResult;
import web.util.NoteUtil;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("bookService")
public class BookServiceImp implements BookService{
    @Resource
    BookDAO bookDAO;

    @Override
    public NoteResult<Book> addBook(String bookName,String userId) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String id = NoteUtil.createId();
        NoteResult<Book> noteResult = new NoteResult<>();
        if (bookName==null || bookName.equals("")){
            noteResult.setStatus(1);
            noteResult.setMsg("笔记本创建失败！笔记本名称为空！！");
            return noteResult;
        }
        Book book = new Book();
        book.setCn_notebook_id(id);
        book.setCn_user_id(userId);
        book.setCn_notebook_name(bookName);
        book.setCn_notebook_createtime(timestamp);
        bookDAO.addBook(book);
        noteResult.setData(book);
        noteResult.setStatus(0);
        noteResult.setMsg("创建笔记本成功");
        return noteResult;
    }

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
