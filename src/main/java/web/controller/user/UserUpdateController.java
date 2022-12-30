package web.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.User;
import web.service.UserService;
import web.util.NoteResult;

import javax.annotation.Resource;

@RequestMapping("/user")
@Controller
public class UserUpdateController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("updatePwd.do")
    public NoteResult<User> updatePwd(String newPwd,String userId){
        NoteResult<User> noteResult= userService.updatePwd(newPwd,userId);
        return noteResult;
    }
}
