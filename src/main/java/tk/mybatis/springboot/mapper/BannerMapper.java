package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.springboot.model.Banner;
import tk.mybatis.springboot.util.MyMapper;

@Mapper
public interface BannerMapper extends MyMapper<Banner> {


    int editBanner(Banner b);

    int delBanner(@Param("id") Integer id);
}
