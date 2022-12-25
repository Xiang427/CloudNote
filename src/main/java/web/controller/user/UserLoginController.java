package web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.User;
import web.service.UserService;
import web.util.NoteResult;

import javax.annotation.Resource;
@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Resource
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public NoteResult<User> login(String name,String password){
        System.out.println(name+"----"+password);
        NoteResult<User> noteResult=userService.checkLogin(name,password);
        return noteResult;
    }
}
