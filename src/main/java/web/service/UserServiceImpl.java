package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.entity.User;
import web.util.NoteException;
import web.util.NoteResult;
import web.util.NoteUtil;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    UserDAO userDAO;
    @Override
    public NoteResult<User> checkLogin(String name, String password) {
        User user = userDAO.findByName(name);
        NoteResult<User> noteResult = new NoteResult<>();
        String md5Password = null;
        try {
            md5Password = NoteUtil.md5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (user==null){
            noteResult.setStatus(1);
            noteResult.setMsg("账号不存在");
        }else if (!user.getCn_user_password().equals(md5Password)){
            noteResult.setStatus(2);
            noteResult.setMsg("密码错误");
        }else {
            noteResult.setStatus(0);
            noteResult.setMsg("登录成功");
            noteResult.setData(user);
        }
        return noteResult;
    }

    @Override
    public NoteResult addUser(String name, String password, String nick) {
        NoteResult<User> noteResult = new NoteResult<>();
        String md5Password = null;
        try {
            User has_user  = userDAO.findByName(name);
            if (has_user!=null){
                noteResult.setStatus(1);
                noteResult.setMsg("用户名被占用");
                return noteResult;
            }
            User user = new User();
            md5Password = NoteUtil.md5(password);
            String id = NoteUtil.createId();
            System.out.println(id);
            user.setCn_user_id(id);
            user.setCn_user_name(name);
            user.setCn_user_nick(nick);
            user.setCn_user_password(md5Password);
            userDAO.save(user);
            noteResult.setStatus(0);
            noteResult.setMsg("注册成功");
            noteResult.setData(user);
            System.out.println(noteResult.toString());
            return noteResult;
        } catch (NoSuchAlgorithmException e) {
            throw new NoteException("用户注册异常",e);
        }
    }
}
