package ink.whi.saibackend.handler;

import ink.whi.saibackend.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Order
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public String BusinessHandler(BusinessException e) {
        String msg = e.getErrorCode();
        LOGGER.error("[Error]: {}",e.getMessage());
        return msg;
    }

//    @ExceptionHandler(value = Exception.class)
//    public String GlobalHandler(Exception exception){
//        LOGGER.error("[Error]: {}", exception.getMessage());
//        return "Request Error";
//    }
}