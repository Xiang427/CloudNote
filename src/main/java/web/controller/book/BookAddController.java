package web.controller.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.Book;
import web.service.BookService;
import web.util.NoteResult;

import javax.annotation.Resource;

@Controller
@RequestMapping("/book")
public class BookAddController {
    @Resource
    BookService bookService;
    @ResponseBody
    @RequestMapping("/addBook.do")
    public NoteResult<Book> addBook(String bookName, String userId){
        NoteResult<Book> noteResult=bookService.addBook(bookName,userId);
        return noteResult;
    }
}
