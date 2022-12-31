package web.dao;

import web.entity.Note;

import java.util.List;
import java.util.Map;

public interface NoteDAO {
    public List<Map> findNotesById(String bookId);
    public Note findNoteById(String noteId);
    public void addNote(Note note);
    public int updateNote(Note note);
    public int moveNote(Note note);
    public int deleteNote(String noteId);
}
