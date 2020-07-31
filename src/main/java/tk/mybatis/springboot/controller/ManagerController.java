package tk.mybatis.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.service.ForumService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Resource
    private ForumService forumService;

    @RequestMapping("index")
    public ModelAndView toIndex(){

        return new ModelAndView("managerIndex");

    }

    @RequestMapping("getForumList")
    public Object getForumList(){

        List<ForumVo> list= forumService.getAllList(null,null);
        return list;
    }

    @RequestMapping("toAddPage")
    public ModelAndView toAddPage(){
        return new ModelAndView("forumAddPage");
    }


    @RequestMapping("addForum")
    public Object addForum(ForumVo forum){
        int count=forumService.addForum(forum);
        return count;
    }

    @RequestMapping("delForum")
    public Object delForum(Integer id){
        int count=forumService.delForum(id);
        return count;
    }

}
