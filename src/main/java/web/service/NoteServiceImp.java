package web.service;

import org.springframework.stereotype.Service;
import web.dao.NoteDAO;
import web.entity.Note;
import web.util.NoteResult;
import web.util.NoteUtil;

import javax.annotation.Resource;
import java.util.Date;
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
    public NoteResult<Note> add(String noteTitle, String userId, String bookId) {
        NoteResult<Note> noteResult = new NoteResult<>();
        if (noteTitle==null || noteTitle.equals("")){
            noteResult.setMsg("创建失败，笔记无标题");
            noteResult.setStatus(1);
            return noteResult;
        }
        Note note = new Note();
        String id = NoteUtil.createId();
        note.setCn_note_id(id);
        note.setCn_note_title(noteTitle);
        note.setCn_user_id(userId);
        note.setCn_notebook_id(bookId);
        note.setCn_note_create_time(new Date().getTime());
        noteDAO.addNote(note);
        noteResult.setData(note);
        noteResult.setStatus(0);
        noteResult.setMsg("添加笔记成功！！");
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

    @Override
    public NoteResult<Note> updateNote(String noteId, String noteBody, String noteTitle) {
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_title(noteTitle);
        note.setCn_note_body(noteBody);
        Long time = System.currentTimeMillis();
        note.setCn_note_last_modify_time(time);
        int rows = noteDAO.updateNote(note);
        NoteResult result = new NoteResult();
        if(rows == 1){    //成功
            result.setStatus(0);
            result.setMsg("保存笔记成功");
            return result;
        }
        result.setStatus(1);
        result.setMsg("保存笔记失败");
        return result;
    }

    @Override
    public NoteResult deleteNote(String noteId) {
        NoteResult result = new NoteResult();
        int rows = noteDAO.deleteNote(noteId);
        if(rows == 1){    //成功
            result.setStatus(0);
            result.setMsg("笔记删除成功");
            return result;
        }
        result.setStatus(1);
        result.setMsg("笔记删除失败");
        return result;
    }

    @Override
    public NoteResult moveNote(String noteId, String bookId) {
        NoteResult noteResult = new NoteResult();
        Note note =new Note();
        note.setCn_note_id(noteId);
        note.setCn_notebook_id(bookId);
        int rows = noteDAO.moveNote(note);
        if (rows==1){
            noteResult.setStatus(0);
            noteResult.setMsg("移动笔记成功");
            return noteResult;
        }
        noteResult.setStatus(1);
        noteResult.setMsg("移动笔记失败");
        return noteResult;
    }
}
