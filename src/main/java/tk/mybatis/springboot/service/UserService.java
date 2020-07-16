package tk.mybatis.springboot.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.UserMapper;
import tk.mybatis.springboot.model.User;
import tk.mybatis.springboot.util.Md5Utils;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public User getUserByAccount(String userName, String password) {

        User user=userMapper.getUserByAccount(userName);
        if(user!=null){
            String md5pwd=Md5Utils.md5Hex(password);
            if(StringUtils.isNotBlank(user.getPassword())){
                if(user.getPassword().equals(md5pwd)){
                    return user;
                }
            }
        }
        return null;
    }
}
