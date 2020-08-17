package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.springboot.model.ForumLabel;
import tk.mybatis.springboot.model.vo.Label;
import tk.mybatis.springboot.util.MyMapper;

@Mapper
public interface LabelMapper extends MyMapper<Label> {
    int editLabel(Label label);
}
