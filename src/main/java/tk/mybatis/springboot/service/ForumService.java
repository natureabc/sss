package tk.mybatis.springboot.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.springboot.mapper.*;
import tk.mybatis.springboot.model.*;
import tk.mybatis.springboot.model.vo.BannerVo;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.model.vo.Label;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Service
public class ForumService {

    @Resource
    private ForumMapper forumMapper;

    @Resource
    private ForumLabelMapper forumLabelMapper;

    @Resource
    private LabelMapper labelMapper;

    @Resource
    private KeywordMapper keywordMapper;

    @Resource
    private BannerMapper bannerMapper;


    @Value("${sys.img.readPath}")
    private String imgPath;

    @Value("${sys.img.uploadPath}")
    private String uploadPath;

    public List<ForumVo> getAllList(Integer labelId, Integer keywordId){
        Map<String,Object> params=new HashMap<>();
        params.put("labelId",labelId);
        params.put("keywordId",keywordId);
        List<ForumVo> list=forumMapper.getAllListNoPage(params);
        if(CollectionUtils.isNotEmpty(list)){
            for(ForumVo vo:list){
                Example example = new Example(ForumLabel.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("forumId",vo.getId());
                List<ForumLabel> fllist=forumLabelMapper.selectByExample(example);
                vo.setLabelList(fllist);
                if(StringUtil.isNotEmpty(vo.getMainPic())){
                    // String path=FileConverBase64.getbase64Url(imgPath+vo.getMainPic());
                    String path=imgPath+vo.getMainPic();
                    vo.setMainPic(path);
                }
            }
        }
        return list;
    }

    public PageBean getAllList(Integer labelId, Integer keywordId, PageBean pageBean) {
        Map<String,Object> params=new HashMap<>();
        params.put("labelId",labelId);
        params.put("keywordId",keywordId);
        params.put("pageBean",pageBean);
        int count=forumMapper.getAllListCount(params);
       List<ForumVo> list=forumMapper.getAllList(params);
       if(CollectionUtils.isNotEmpty(list)){
           for(ForumVo vo:list){
               Example example = new Example(ForumLabel.class);
               Example.Criteria criteria = example.createCriteria();
               criteria.andEqualTo("forumId",vo.getId());
               List<ForumLabel> fllist=forumLabelMapper.selectByExample(example);
               vo.setLabelList(fllist);
               if(StringUtil.isNotEmpty(vo.getMainPic())){
                  // String path=FileConverBase64.getbase64Url(imgPath+vo.getMainPic());
                   String path=imgPath+vo.getMainPic();
                   vo.setMainPic(path);
               }
           }
       }
       return  new PageBean(pageBean.getPage(), pageBean.getRows(), count, list);
    }

    public ForumVo getForumDetailById(Integer forumId) {

        ForumVo f=forumMapper.getById(forumId);
        if(StringUtils.isNotEmpty(f.getMainPic())){
            f.setMainPic(imgPath+f.getMainPic());
        }
        Example example = new Example(ForumLabel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("forumId",f.getId());
        List<ForumLabel> fllist=forumLabelMapper.selectByExample(example);
        f.setLabelList(fllist);

        ForumVo next=forumMapper.getNextForum(forumId);
        if(next!=null){
            f.setNextTitle(next.getTitle());
            f.setNextId(next.getId());
        }
        ForumVo pre=forumMapper.getPreForum(forumId);
        if(pre!=null){
            f.setPreTitle(pre.getTitle());
            f.setPreId(pre.getId());
        }

        return f;
    }

    public List<Satuation> getSatuationList() {

        List<Satuation> list=  forumMapper.getSatutionList();
        return list;
    }

    public List<Keyword> getKeyWordList() {

        List<Keyword> list=forumMapper.getKeyWordList();
        return list;
    }

    public List<Label> getLabelList() {
        List<Label> list=forumMapper.getLabelList();
        return list;
    }

    public List<Label> getToolTitle() {
        List<Label> list=forumMapper.getLabelListTitle();
        return list;
    }

    @Transactional
    public int addForum(ForumVo forum) {
        try {
            if (!forum.getImgFile().isEmpty()) {
                String fileName = System.currentTimeMillis()+".jpg";
                forum.getImgFile().transferTo(new File(uploadPath + fileName));
                forum.setMainPic(fileName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(forum.getKeywordId()!=null){
            Keyword keyword=keywordMapper.selectByPrimaryKey(forum.getKeywordId());
            if(keyword!=null){
                forum.setKeyWord(keyword.getKeyword());
            }
        }
        int count=forumMapper.addForum(forum);
        if(forum.getLabelArray()!=null&&forum.getLabelArray().length>0){
            for(int i=0;i<forum.getLabelArray().length;i++){
                ForumLabel fl=new ForumLabel();
                fl.setForumId(forum.getId());
                fl.setLabelId(Integer.parseInt(forum.getLabelArray()[i]));
                Label label=labelMapper.selectByPrimaryKey(Integer.parseInt(forum.getLabelArray()[i]));
                if(label!=null){
                    fl.setLabelName(label.getLabelName());
                }
                forumLabelMapper.insert(fl);
            }
        }
        return count;

    }

    public int delForum(Integer id) {

        return forumMapper.delForum(id);

    }

    public int editForum(ForumVo forum) {
        if(forum.getId()==null){
            return -1;
        }
        try {
            if (!forum.getImgFile().isEmpty()) {
                String fileName = System.currentTimeMillis()+".jpg";
                forum.getImgFile().transferTo(new File(uploadPath + fileName));
                forum.setMainPic(fileName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(forum.getKeywordId()!=null){
            Integer keywordId=forum.getKeywordId();
            Keyword keyword=keywordMapper.selectByPrimaryKey(keywordId);
            if(keyword!=null){
                forum.setKeyWord(keyword.getKeyword());
            }
        }
        int count=forumMapper.editForm(forum);
        if(forum.getLabelArray()!=null&&forum.getLabelArray().length>0){
            //全删全增
            forumLabelMapper.deleteByForumId(forum.getId());
            for(int i=0;i<forum.getLabelArray().length;i++){
                ForumLabel fl=new ForumLabel();
                fl.setForumId(forum.getId());
                fl.setLabelId(Integer.parseInt(forum.getLabelArray()[i]));
                Label label=labelMapper.selectByPrimaryKey(Integer.parseInt(forum.getLabelArray()[i]));
                if(label!=null){
                    fl.setLabelName(label.getLabelName());
                }
                forumLabelMapper.insert(fl);
            }
        }
        return count;
    }

    public List<Banner> getBannerList() {

        List<Banner>  list=bannerMapper.selectAll();
        if(CollectionUtils.isNotEmpty(list)){
            for(Banner b:list){
                if(StringUtils.isNotBlank(b.getBannerImg())){
                    String path=imgPath+b.getBannerImg();
                    b.setBannerImg(path);
                }
            }
        }
        return list;
    }

    public int addBanner(BannerVo bannerVo) {
        try {
            Banner b = new Banner();
            if (!bannerVo.getImgFile().isEmpty()) {
                String fileName = System.currentTimeMillis() + ".jpg";
                bannerVo.getImgFile().transferTo(new File(uploadPath + fileName));
                b.setBannerImg(fileName);
            }
            b.setCreateTime(new Date());
            b.setSort(bannerVo.getSort());
            return bannerMapper.insert(b);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int changeBanner(BannerVo bannerVo) {
        try {
            Banner b = new Banner();
            if (!bannerVo.getImgFile().isEmpty()) {
                String fileName = System.currentTimeMillis() + ".jpg";
                bannerVo.getImgFile().transferTo(new File(uploadPath + fileName));
                b.setBannerImg(fileName);
            }
            b.setSort(bannerVo.getSort());
            b.setId(bannerVo.getId());
            return bannerMapper.editBanner(b);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }


    }

    public int delBanner(Integer id) {

        int count=bannerMapper.delBanner(id);
        return count;
    }

    public int addLabel(Label label) {

        int count=labelMapper.insert(label);
        return count;
    }

    public int delLabel(Integer id) {
        int count=labelMapper.deleteByPrimaryKey(id);
        return count;
    }

    public int editLabel(Label label) {

        int count=labelMapper.editLabel(label);
        if(count>0){
            ForumLabel forumLabel=new ForumLabel();
            forumLabel.setLabelName(label.getLabelName());
            forumLabel.setLabelId(label.getId());
            forumLabelMapper.updateLabelName(forumLabel);
        }
        return count;
    }
}
