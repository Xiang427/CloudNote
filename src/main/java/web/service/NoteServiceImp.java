package web.service;

import org.springframework.stereotype.Service;
import web.dao.NoteDAO;
import web.entity.Note;
import web.util.NoteResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("noteService")
public class NoteServiceImp implements NoteService{
    @Resource
    NoteDAO noteDAO;
    @Override
    public NoteResult<List<Map>> loadNotes(String bookId) {
        NoteResult<List<Map>> noteResult = new NoteResult<>();
        List<Map> notes=noteDAO.findNotesById(bookId);
        if (notes.isEmpty()){
            noteResult.setMsg("此笔记本无笔记");
        }else {
            noteResult.setMsg("查询成功");
        }
        noteResult.setStatus(0);
        noteResult.setData(notes);
        return noteResult;
    }

    @Override
    public NoteResult<Note> load(String noteId) {
        NoteResult<Note> noteResult=new NoteResult<>();
        Note note = noteDAO.findNoteById(noteId);
        if (note==null){
            noteResult.setMsg("笔记无内容");
        }else {
            noteResult.setMsg("查询笔记内容成功");
        }
        noteResult.setStatus(0);
        noteResult.setData(note);
        return noteResult;
    }
}
