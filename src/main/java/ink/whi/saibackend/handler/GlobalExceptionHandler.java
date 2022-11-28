package ink.whi.saibackend.handler;

import ink.whi.saibackend.exception.BusinessException;
import ink.whi.saibackend.pojo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数格式异常处理
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> badRequestException(IllegalArgumentException ex) {
        log.error("[Error]: 参数格式不合法：{}", ex.getMessage());
        return new ApiResult<>(HttpStatus.BAD_REQUEST.value() + "", "参数格式不符！");
    }

    /**
     * 权限不足异常处理
     */
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResult<String> badRequestException(AccessDeniedException ex) {
        return new ApiResult<>(HttpStatus.FORBIDDEN.value() + "", ex.getMessage());
    }

    /**
     * 参数缺失异常处理
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> badRequestException(Exception ex) {
        return new ApiResult<>(HttpStatus.BAD_REQUEST.value() + "", "缺少必填参数！");
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> handleTypeMismatchException(NullPointerException ex) {
        log.error("[Error]: 空指针异常，{}", ex.getMessage());
        return ApiResult.fail("空指针异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> handleUnexpectedServer(Exception ex) {
        log.error("[Error]: 系统异常：", ex);
        return ApiResult.fail("系统发生异常，请联系管理员");
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> exception(Throwable throwable) {
        log.error("[Error]: 系统异常", throwable);
        return new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value() + "系统异常，请联系管理员！");
    }

    /*
    * 自定义异常处理类
    * */
    @Order
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ApiResult<String> BusinessHandler(BusinessException e) {
        String msg = e.getErrorMsg();
        log.error("[Error]: {}", e.getErrorMsg());
        return ApiResult.fail(msg);
    }
}

