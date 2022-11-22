package ink.whi.saibackend.interceptor;

import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.exception.BusinessException;
import ink.whi.saibackend.service.UserService;
import ink.whi.saibackend.utils.TaleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    private static final String USER_AGENT = "user-agent";

    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        LOGGER.info("[UserAgent]: {}", request.getHeader(USER_AGENT));
        LOGGER.info("[来路地址]: {}, [用户请求地址]: {}", TaleUtil.getIpAddrByRequest(request), requestURI);

        // 获取token
        String token = request.getHeader(WebConstant.JWT.Authorization);
        if (StringUtils.isBlank(token)) {
            LOGGER.info("[Error]: {}", WebConstant.JWT.TOKEN_NOT_EXIST);
            response.sendRedirect("/admin/login");
            return false;
        }
        // 验证token
        String sub = TaleUtil.isVerify(token);
        if (StringUtils.isBlank(sub) || !userService.hasUser(sub)) {
            LOGGER.error("[Error]: {}", WebConstant.JWT.TOKEN_ERROR);
            throw BusinessException.withErrorCode(WebConstant.JWT.TOKEN_ERROR);
        }
        if (TaleUtil.isNeedUpdate(token)) {
            response.setHeader(WebConstant.JWT.Authorization, TaleUtil.createToken(sub));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
