package tk.mybatis.springboot.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.Banner;
import tk.mybatis.springboot.model.vo.BannerVo;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.model.vo.Label;
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

    @RequestMapping("toEditPage")
    public ModelAndView toEditPage(String forumId){
        ModelAndView mv=new ModelAndView();
        mv.addObject("forumId",forumId);
        mv.setViewName("forumEditPage");
        return mv;

    }

    @RequestMapping("editForum")
    public Object editForum(ForumVo forum){
        int count=forumService.editForum(forum);
        return count;
    }

    @RequestMapping("delForum")
    public Object delForum(Integer id){
        int count=forumService.delForum(id);
        return count;
    }

    @RequestMapping("toBannerPage")
    public ModelAndView toBannerPage(){

        return new ModelAndView("bannerPage");

    }

    @RequestMapping("getBannerList")
    public Object getBannerList(){

        List<Banner> list=forumService.getBannerList();
        return list;
    }

    @RequestMapping("changeBanner")
    public Object changeBanner(BannerVo bannerVo){
        int count=forumService.changeBanner(bannerVo);
        return count;

    }

    @RequestMapping("toBannerAdd")
    public ModelAndView toBannerAdd(){
        return new ModelAndView("bannerAdd");
    }

    @RequestMapping("addBanner")
    public Object addBanner(BannerVo bannerVo){

        int count=forumService.addBanner(bannerVo);
        return count;
    }

    @RequestMapping("delBanner")
    public Object delBanner(Integer id){
        int count=forumService.delBanner(id);
        return count;
    }

    @RequestMapping("toLabelPage")
    public ModelAndView toLabelPage(){
        return new ModelAndView("labelPage");
    }

    @RequestMapping("getLabelList")
    public Object getLabelList(){
        return forumService.getLabelList();
    }

    @RequestMapping("toLabelAdd")
    public ModelAndView toLabelAdd(){
        return new ModelAndView("labelAdd");
    }

    @RequestMapping("addLabel")
    public Object addLabel(Label label){

        int count=forumService.addLabel(label);
        return count;
    }

    @RequestMapping("editLabel")
    public Object editLabel(Label label){

        int count=forumService.editLabel(label);
        return count;
    }

    @RequestMapping("delLabel")
    public Object delLabel(Integer id){
        int count=forumService.delLabel(id);
        return count;
    }

}
