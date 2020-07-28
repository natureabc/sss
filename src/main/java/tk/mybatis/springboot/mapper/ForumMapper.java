package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.springboot.model.Forum;
import tk.mybatis.springboot.model.Keyword;
import tk.mybatis.springboot.model.Satuation;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.model.vo.Label;
import tk.mybatis.springboot.util.MyMapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ForumMapper extends MyMapper<Forum> {
    List<ForumVo> getAllList(@Param("params") Map<String,Object> params);

    ForumVo getById(@Param("forumId")Integer forumId);

    ForumVo getNextForum(@Param("forumId")Integer forumId);

    ForumVo getPreForum(@Param("forumId")Integer forumId);

    List<Satuation> getSatutionList();

    List<Keyword> getKeyWordList();

    List<Label> getLabelList();

    List<Label> getLabelListTitle();

    int addForum(ForumVo forum);
}
