package ink.whi.saibackend.handler;

import ink.whi.saibackend.pojo.ApiResult;
import org.assertj.core.util.Lists;
import org.springframework.core.MethodParameter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class MyHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler handler;

    public MyHandlerMethodReturnValueHandler(HandlerMethodReturnValueHandler handler) {
        this.handler = handler;
    }

    /**
     * 处理器支持相应的返回值类型
     * @param returnType the method return type to check
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
//        return ApiResult.class.isAssignableFrom(returnType.getParameterType());  // 处理返回值为ApiResult的情况
        return handler.supportsReturnType(returnType);  // 处理全部返回值
    }

    /**
     * 对方法返回值进行处理
     * @param returnValue the value returned from the handler method
     * @param returnType the type of the return value. This type must have
     * previously been passed to {@link #supportsReturnType} which must
     * have returned {@code true}.
     * @param mavContainer the ModelAndViewContainer for the current request
     * @param webRequest the current request
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 判断返回值外层是否被ApiResult包裹，如果是就交给handler处理
        if (returnValue instanceof ApiResult) {
            this.handler.handleReturnValue(returnValue,returnType,mavContainer,webRequest);
            return;
        }
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        assert nativeRequest != null;
        String method = nativeRequest.getMethod();
        String servletPath = nativeRequest.getServletPath();
        String restfulPath = method + ":" + servletPath;
        boolean isHandlerReturnValue = true;
        AntPathMatcher matcher = new AntPathMatcher();
        // 判断api是否需要封装
        for (String s : Lists.newArrayList("POST:/aaa")) {
            if (matcher.match(s, restfulPath)) {
                isHandlerReturnValue = false;
            }
            break;
        }
        this.handler.handleReturnValue(
                isHandlerReturnValue ? ApiResult.success(returnValue) : returnValue,
                returnType, mavContainer, webRequest);
    }
}