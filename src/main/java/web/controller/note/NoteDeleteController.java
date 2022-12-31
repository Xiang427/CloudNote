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
public class NoteDeleteController {
    @Resource(name = "noteService")
    NoteService noteService;

    @ResponseBody
    @RequestMapping("/deleteNote.do")
    public NoteResult delete(String noteId){
        NoteResult noteResult = noteService.deleteNote(noteId);
        return noteResult;
    }
}
