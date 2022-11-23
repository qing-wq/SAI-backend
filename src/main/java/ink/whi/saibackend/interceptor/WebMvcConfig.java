package ink.whi.saibackend.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private BaseInterceptor baseInterceptor;

    private static final String STATIC_RESOURCE = "/static/**";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor).addPathPatterns("/**").excludePathPatterns(STATIC_RESOURCE);
        registry.addInterceptor(authInterceptor).addPathPatterns("/query/**").excludePathPatterns(STATIC_RESOURCE).order(1);  // 设置优先级
    }

}
