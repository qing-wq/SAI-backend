package ink.whi.saibackend.service;

import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.exception.BusinessException;
import ink.whi.saibackend.mapper.UserMapper;
import ink.whi.saibackend.pojo.UserInfo;
import ink.whi.saibackend.utils.TaleUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public UserInfo login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw BusinessException.withErrorCode(WebConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }
        String pwd = TaleUtil.MD5encoder(password + username);
        UserInfo userInfo = userMapper.getUserByCond(username, pwd);
        if (userInfo == null) {
            throw BusinessException.withErrorCode(WebConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }
        return userInfo;
    }
}
