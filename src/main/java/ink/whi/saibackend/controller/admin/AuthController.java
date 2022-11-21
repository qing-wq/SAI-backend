package ink.whi.saibackend.controller.admin;

import com.alibaba.fastjson.JSON;
import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.pojo.UserInfo;
import ink.whi.saibackend.service.UserService;
import ink.whi.saibackend.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String toLogin(@RequestBody @Validated UserInfo userInfo, BindingResult result) {
        Map<String, String> errMap = CheckUtil.CheckResult(result);
        if (!errMap.isEmpty()) {
            return JSON.toJSONString(errMap);
        }
        UserInfo login = userService.login(userInfo.getUsername(), userInfo.getPassword());

        return WebConstant.Auth.LOGIN_SUCCESS;
    }

}
