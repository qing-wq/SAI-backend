package ink.whi.saibackend.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnums {

    SUCCESS(200, "success"),
    FAIL(500, "fail"),

    HTTP_STATUS_200(200, "ok"),
    HTTP_STATUS_400(400, "request error"),
    HTTP_STATUS_401(401, "no authentication"),
    HTTP_STATUS_403(403, "authorities"),
    HTTP_STATUS_500(500, "server error");

    private final int code;

    private final String message;

}
