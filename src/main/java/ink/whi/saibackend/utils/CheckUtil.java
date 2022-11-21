package ink.whi.saibackend.utils;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class CheckUtil {

    public static Map<String, String> CheckResult(BindingResult result) {
        Map<String, String> errMap = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach((item) -> {
                errMap.put(item.getField(), item.getDefaultMessage());
            });
        }
        return errMap;
    }

}
