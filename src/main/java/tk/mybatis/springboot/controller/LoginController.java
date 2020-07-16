
package tk.mybatis.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.User;
import tk.mybatis.springboot.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("toLogin")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }

    @RequestMapping("doLogin")
    public ModelAndView doLogin(HttpServletRequest request,String userName, String password){

        User user=userService.getUserByAccount(userName,password);
        if(user!=null){
            request.getSession().setAttribute("user",user);
            return new ModelAndView("managerIndex");
        }else{
            request.getSession().setAttribute("error","用户名或密码错误");
            return new ModelAndView("login");
        }
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return new ModelAndView("login");

    }


}

