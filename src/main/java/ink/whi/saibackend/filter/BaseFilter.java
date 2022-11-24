package ink.whi.saibackend.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BaseFilter implements Filter {

    private static final String OPTIONS = "OPTIONS";

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
//        response.addHeader("Access-Control-Allow-Headers", "Authorization,Accept, Origin,X-Requested-With, Content-Type, Last-Modified");
//        response.addHeader("Access-Control-Max-Age", "1800");

        if (OPTIONS.equals(((HttpServletRequest) servletRequest).getMethod())) {
            LOGGER.info("[INFO]: 响应预请求}");
            return;
        }
        filterChain.doFilter(servletRequest, response);
    }

}
