package web.dao;

import web.entity.Note;

import java.util.List;
import java.util.Map;

public interface NoteDAO {
    public List<Map> findNotesById(String bookId);
    public Note findNoteById(String noteId);
}
