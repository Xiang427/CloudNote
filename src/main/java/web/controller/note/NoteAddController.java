package web.controller.note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.Note;
import web.service.NoteService;
import web.util.NoteResult;

import javax.annotation.Resource;

@Controller
@RequestMapping("/note")
public class NoteAddController {

    @Resource
    private NoteService noteService;

    @ResponseBody
    @RequestMapping("/addNote.do")
    public NoteResult<Note> addNote(String noteTitle,String userId,String bookId){
        NoteResult<Note> noteResult = noteService.add(noteTitle,userId,bookId);
        return noteResult;
    }
}
