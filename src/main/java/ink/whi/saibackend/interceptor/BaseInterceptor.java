package ink.whi.saibackend.interceptor;

import ink.whi.saibackend.utils.TaleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    private static final String USER_AGENT = "user-agent";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        LOGGER.info("[UserAgent]: {}", request.getHeader(USER_AGENT));
        LOGGER.info("[来路地址]: {}, [用户请求地址]: {}", TaleUtil.getIpAddrByRequest(request), requestURI);

        return true;
    }
}
