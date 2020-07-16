package tk.mybatis.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.service.ForumService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Resource
    private ForumService forumService;

    @RequestMapping("getForumList")
    public Object getForumList(){

        List<ForumVo> list= forumService.getAllList(null,null);
        return list;
    }

}
