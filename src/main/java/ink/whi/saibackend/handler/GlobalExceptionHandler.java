package ink.whi.saibackend.handler;

import ink.whi.saibackend.exception.BusinessException;
import ink.whi.saibackend.pojo.ResponseInfo;
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
    public ResponseInfo<String> badRequestException(IllegalArgumentException ex) {
        log.error("[Error]: 参数格式不合法：{}", ex.getMessage());
        return new ResponseInfo<>(HttpStatus.BAD_REQUEST.value() + "", "参数格式不符！");
    }

    /**
     * 权限不足异常处理
     */
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseInfo<String> badRequestException(AccessDeniedException ex) {
        return new ResponseInfo<>(HttpStatus.FORBIDDEN.value() + "", ex.getMessage());
    }

    /**
     * 参数缺失异常处理
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo<String> badRequestException(Exception ex) {
        return new ResponseInfo<>(HttpStatus.BAD_REQUEST.value() + "", "缺少必填参数！");
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo<String> handleTypeMismatchException(NullPointerException ex) {
        log.error("[Error]: 空指针异常，{}", ex.getMessage());
        return ResponseInfo.fail("空指针异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo<String> handleUnexpectedServer(Exception ex) {
        log.error("[Error]: 系统异常：", ex);
        return ResponseInfo.fail("系统发生异常，请联系管理员");
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo<String> exception(Throwable throwable) {
        log.error("[Error]: 系统异常", throwable);
        return new ResponseInfo<>(HttpStatus.INTERNAL_SERVER_ERROR.value() + "系统异常，请联系管理员！");
    }

    /*
    * 自定义异常处理类
    * */
    @Order
    @ExceptionHandler(value = BusinessException.class)
    public ResponseInfo<String> BusinessHandler(BusinessException e) {
        String msg = e.getErrorCode();
        log.error("[Error]: {}", e.getMessage());
        return ResponseInfo.fail(msg);
    }
}

