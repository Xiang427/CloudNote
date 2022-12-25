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
public class UserAddController {
    @Resource
    private UserService userService;

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<User> add(String name,String password ,String nick){
        System.out.println(name+"----"+password+"----"+nick);
        NoteResult<User> noteResult = userService.addUser(name,password,nick);
        return noteResult;
    }
}
