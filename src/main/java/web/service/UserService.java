package web.service;


import web.entity.User;
import web.util.NoteResult;


public interface UserService {
    public NoteResult<User> checkLogin(String name ,String password);
    public NoteResult addUser(String name, String nick, String password);
}
