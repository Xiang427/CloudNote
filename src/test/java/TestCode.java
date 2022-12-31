import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import web.dao.BookDAO;
import web.dao.NoteDAO;
import web.dao.UserDAO;
import web.entity.Book;
import web.entity.Note;
import web.entity.User;
import web.service.BookService;
import web.service.NoteService;
import web.service.UserService;
import web.util.NoteResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestCode {

    //测试userDao接口
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
        UserDAO userDAO=context.getBean("userDAO",UserDAO.class);
        User user=userDAO.findByName("zhouj");
        if (user!=null) {
            System.out.println(user.toString());
        }else {
            System.out.println("用户不存在");
        }
    }

    //测试userService
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-*.xml");
        UserService userService=context.getBean("userService",UserService.class);
        NoteResult<User> noteResult= userService.checkLogin("zhouj","123456");
        System.out.println(noteResult.toString());
    }

    //测试bookService
    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-*.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        NoteResult<List<Book>> noteResult=bookService.loadBooks("clx");
        System.out.println(noteResult.toString());
    }

    @Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
        NoteDAO noteDAO=context.getBean("noteDAO", NoteDAO.class);
        List<Map> notes=noteDAO.findNotesById("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
        System.out.println(notes.toString());
    }

    @Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-*.xml");
        NoteService noteService = context.getBean("noteService", NoteService.class);
        NoteResult<List<Map>> noteResult=noteService.loadNotes("516f6f4f-eaa3-4c76-84ff-530b92c7f64");
        System.out.println(noteResult.toString());
    }

    @Test
    public void test6(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
        NoteDAO noteDAO=context.getBean("noteDAO", NoteDAO.class);
        Note note = new Note();
        note.setCn_note_id("71cac8ed68834ce083916cb690811126");
        note.setCn_note_title("666");
        note.setCn_note_body("5654");
        note.setCn_note_last_modify_time(new Date().getTime());
        int i = noteDAO.updateNote(note);
        System.out.println(i);
    }

    @Test
    public void test7(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-*.xml");
        NoteService noteService = context.getBean("noteService", NoteService.class);
        NoteResult<Note> noteResult=noteService.load("fed920a0-573c-46c8-ae4e-368397846efd");
        System.out.println(noteResult.toString());
    }
    @Test
    public void test8(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
        BookDAO bookDAO=context.getBean("bookDAO", BookDAO.class);
        Book book = new Book();
        book.setCn_notebook_id("666");
        book.setCn_notebook_name("666");
        book.setCn_user_id("03590914-a934-4da9-ba4d-b41799f917d1");
        book.setCn_notebook_createtime(new Timestamp(new Date().getTime()));
        bookDAO.addBook(book);
    }
    @Test
    public void test9(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-*.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.addBook("666","03590914-a934-4da9-ba4d-b41799f917d1");
    }
    @Test
    public void test10(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-*.xml");
        NoteService noteService = context.getBean("noteService", NoteService.class);
        NoteResult<Note> noteResult=noteService.updateNote("71cac8ed68834ce083916cb690811126","666","666");
        System.out.println(noteResult.toString());
    }
}
