package web.service;

import web.entity.Note;
import web.util.NoteResult;

import java.util.List;
import java.util.Map;

public interface NoteService {
    public NoteResult<List<Map>> loadNotes(String bookId);
    public NoteResult<Note> load(String noteId);
}
