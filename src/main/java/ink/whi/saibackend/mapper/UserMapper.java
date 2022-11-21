package ink.whi.saibackend.mapper;

import ink.whi.saibackend.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from sai.user where username = #{username} and password = #{password}")
    UserInfo getUserByCond(String username, String password);

}
