package ink.whi.saibackend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一异常处理类
 */
public class BusinessException extends RuntimeException{

    public static final Logger logger = LoggerFactory.getLogger(BusinessException.class);

    protected String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public static BusinessException withErrorCode(String message) {
        BusinessException businessException = new BusinessException();
        businessException.setErrorCode(message);
        return businessException;
    }

}
