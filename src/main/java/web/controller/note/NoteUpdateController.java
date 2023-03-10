package web.controller.note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.Note;
import web.service.NoteService;
import web.util.NoteResult;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/note")
public class NoteUpdateController {
    @Resource
    NoteService noteService;

    @RequestMapping("/updateNote.do")
    public NoteResult<Note> update(String noteId,String body,String title){
        NoteResult<Note> noteResult=noteService.updateNote(noteId,body,title);
        return noteResult;
    }
}
