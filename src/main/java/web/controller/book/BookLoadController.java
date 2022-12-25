package web.controller.book;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.Book;
import web.service.BookService;
import web.util.NoteResult;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookLoadController {
    @Resource
    BookService bookService;
    @RequestMapping("/loadbooks.do")
    @ResponseBody
    public NoteResult<List<Book>> load(String userId){
        NoteResult<List<Book>> noteResult=new NoteResult<>();
        noteResult = bookService.loadBooks(userId);
        return noteResult;
    }
}
