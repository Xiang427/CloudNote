package web.controller.note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.Note;
import web.service.NoteService;
import web.util.NoteResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class NoteLoadController {
    @Resource(name = "noteService")
    NoteService noteService;

    @RequestMapping("/loadnotes.do")
    @ResponseBody
    public NoteResult<List<Map>> lodeNotes(String bookId){
        System.out.println(bookId);
        NoteResult<List<Map>> noteResult=noteService.loadNotes(bookId);
        return noteResult;
    }

    @RequestMapping("/load.do")
    @ResponseBody
    public NoteResult<Note> load(String noteId){
        System.out.println(noteId);
        NoteResult<Note> noteResult = noteService.load(noteId);
        return noteResult;
    }
}
