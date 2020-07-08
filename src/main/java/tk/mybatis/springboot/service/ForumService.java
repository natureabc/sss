package tk.mybatis.springboot.service;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.springboot.mapper.ForumLabelMapper;
import tk.mybatis.springboot.mapper.ForumMapper;
import tk.mybatis.springboot.mapper.LabelMapper;
import tk.mybatis.springboot.model.Forum;
import tk.mybatis.springboot.model.ForumLabel;
import tk.mybatis.springboot.model.Keyword;
import tk.mybatis.springboot.model.Satuation;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.model.vo.Label;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForumService {

    @Resource
    private ForumMapper forumMapper;

    @Resource
    private ForumLabelMapper forumLabelMapper;


    public List<ForumVo> getAllList(Integer labelId,Integer keywordId) {
        Map<String,Object> params=new HashMap<>();
        params.put("labelId",labelId);
        params.put("keywordId",keywordId);
       List<ForumVo> list=forumMapper.getAllList(params);
       if(CollectionUtils.isNotEmpty(list)){
           for(ForumVo vo:list){
               Example example = new Example(ForumLabel.class);
               Example.Criteria criteria = example.createCriteria();
               criteria.andEqualTo("forumId",vo.getId());
               List<ForumLabel> fllist=forumLabelMapper.selectByExample(example);
               vo.setLabelList(fllist);
           }
       }
       return  list;
    }

    public ForumVo getForumDetailById(Integer forumId) {

        ForumVo f=forumMapper.getById(forumId);
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
}
