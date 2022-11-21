package ink.whi.saibackend.interceptor;

import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.exception.BusinessException;
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
public class BaseInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);

    private static final String USER_AGENT = "user-agent";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURI();

        LOGGER.info("UserAgent: {}", request.getHeader(USER_AGENT));
        LOGGER.info("来路地址: {}, 用户请求地址: {}", TaleUtil.getIpAddrByRequest(request), requestURL);

        // 获取token
        String token = request.getHeader(WebConstant.JWT.Authorization);
        if (StringUtils.isBlank(token)) {
            LOGGER.info("token不存在");
            response.sendRedirect("/admin/login");
            return false;
        }
        // 验证token
        String sub = TaleUtil.isVerify(token);
        if (StringUtils.isBlank(sub)) {
            LOGGER.error("[Error]: token验证失败");
            throw BusinessException.withErrorCode("token错误");
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
