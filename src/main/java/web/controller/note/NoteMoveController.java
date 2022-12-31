package web.controller.note;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.service.NoteService;
import web.util.NoteResult;

import javax.annotation.Resource;

@Controller
@RequestMapping("/note")
public class NoteMoveController {
    @Resource(name = "noteService")
    NoteService noteService;

    @ResponseBody
    @RequestMapping("/moveNote.do")
    public NoteResult moveNote(String noteId,String bookId){
        NoteResult noteResult = noteService.moveNote(noteId,bookId);
        return noteResult;
    }
}
