package ink.whi.saibackend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一异常处理类
 */
public class BusinessException extends RuntimeException{

    public static final Logger logger = LoggerFactory.getLogger(BusinessException.class);

    protected String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static BusinessException withErrorCode(String message) {
        BusinessException businessException = new BusinessException();
        businessException.setErrorMsg(message);
        return businessException;
    }
}
