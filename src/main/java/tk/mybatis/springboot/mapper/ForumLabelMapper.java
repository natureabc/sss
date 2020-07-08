package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.springboot.model.Forum;
import tk.mybatis.springboot.model.ForumLabel;
import tk.mybatis.springboot.model.vo.ForumVo;
import tk.mybatis.springboot.util.MyMapper;

import java.util.List;

@Mapper
public interface ForumLabelMapper extends MyMapper<ForumLabel> {
}
