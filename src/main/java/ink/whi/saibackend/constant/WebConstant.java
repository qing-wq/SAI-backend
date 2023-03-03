package ink.whi.saibackend.constant;

public interface WebConstant {

    interface Auth {
        String NOT_LOGIN = "用户未登录";
        String USERNAME_PASSWORD_IS_EMPTY = "用户名和密码不可为空";
        String USERNAME_PASSWORD_ERROR = "用户名或密码错误";
        String LOGIN_SUCCESS = "success";
    }

    interface MD5{
        String MD5_SALT = "GUET-SAI-CXJD";
    }

    interface JWT{
        String JWT_KEY = "${KEY}";
        String Authorization = "Authorization";
        String TOKEN_PREFIX = "Bearer ";
        String TOKEN_ERROR = "token错误！";
        String TOKEN_NOT_EXIST = "token不存在！";
    }

}
