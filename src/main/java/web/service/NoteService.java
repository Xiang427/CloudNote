package web.service;

import web.entity.Note;
import web.util.NoteResult;

import java.util.List;
import java.util.Map;

public interface NoteService {
    public NoteResult<List<Map>> loadNotes(String bookId);
    public NoteResult<Note> load(String noteId);
    public NoteResult<Note> add(String noteTitle,String userId,String bookId);
    public NoteResult<Note> updateNote(String noteId,String noteBody,String noteTitle);
    public NoteResult deleteNote(String noteId);
    public NoteResult moveNote(String noteId,String bookId);
}
