package tk.mybatis.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.Forum;
import tk.mybatis.springboot.model.Satuation;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.service.ForumService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("forum")
public class ForumController {


    @Resource
    private ForumService forumService;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("forumIndex");

    }

    @RequestMapping("getAllList")
    public Object getAllList(Integer labelId,Integer keywordId){

        return forumService.getAllList(labelId,keywordId);
    }

    @RequestMapping("toDetail")
    public ModelAndView toDetail(Integer id,Integer nextId,Integer preId){
        ModelAndView mv=new ModelAndView("forumDetail");
        mv.addObject("forumId",id);
        mv.addObject("nextId",nextId);
        mv.addObject("preId",preId);
        return mv;
    }

    @RequestMapping("getForumDetailById")
    public Object getForumDetailById(Integer forumId){

        ForumVo f=forumService.getForumDetailById(forumId);
        return f;
    }

    @RequestMapping("getSatuationList")
    public Object getSatuationList(){
        List<Satuation> list=forumService.getSatuationList();
        return list;
    }


    @RequestMapping("getSatutionDetail")
    public ModelAndView getSatutionDetail(){
        return new ModelAndView("satuationlist");
    }

    @RequestMapping("getKeyWordList")
    public Object getKeyWordList(){

        return forumService.getKeyWordList();
    }

    @RequestMapping("getLabelList")
    public Object getLabelList(){
        return forumService.getLabelList();
    }


}
