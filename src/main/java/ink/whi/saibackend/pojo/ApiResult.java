package ink.whi.saibackend.pojo;

import ink.whi.saibackend.constant.ResponseCodeEnums;
import lombok.Data;

@Data
public class ApiResult<T> {
    /**
     * 状态码
     */
    protected int code;
    /**
     * 响应信息
     */
    protected String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> ApiResult<T> success() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> fail(String message) {
        return new ApiResult<>(ResponseCodeEnums.FAIL.getCode(), message);
    }

    public ApiResult() {
        this.code = ResponseCodeEnums.SUCCESS.getCode();
        this.message = ResponseCodeEnums.SUCCESS.getMessage();
    }

    public ApiResult(ResponseCodeEnums statusEnums) {
        this.code = statusEnums.getCode();
        this.message = statusEnums.getMessage();
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     */
    public ApiResult(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    /**
     * 有数据返回时，状态码为200，默认提示信息为“操作成功！”
     */
    public ApiResult(T data) {
        this.data = data;
        this.code = ResponseCodeEnums.SUCCESS.getCode();
        this.message = ResponseCodeEnums.SUCCESS.getMessage();
    }

    /**
     * 有数据返回，状态码为 200，人为指定提示信息
     */
    public ApiResult(T data, String msg) {
        this.data = data;
        this.code = ResponseCodeEnums.SUCCESS.getCode();
        this.message = msg;
    }
}