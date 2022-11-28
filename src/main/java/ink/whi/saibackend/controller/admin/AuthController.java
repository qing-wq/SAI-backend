package ink.whi.saibackend.controller.admin;

import com.alibaba.fastjson.JSON;
import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.exception.BusinessException;
import ink.whi.saibackend.pojo.UserInfo;
import ink.whi.saibackend.service.UserService;
import ink.whi.saibackend.utils.CheckUtil;
import ink.whi.saibackend.utils.TaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public String toLogin(@RequestBody @Validated UserInfo userInfo, BindingResult result, HttpServletResponse response) {
        Map<String, String> errMap = CheckUtil.CheckResult(result);
        if (!errMap.isEmpty()) {
            throw BusinessException.withErrorCode(JSON.toJSONString(errMap));
        }
        UserInfo user = userService.login(userInfo.getUsername(), userInfo.getPassword());
        String token = TaleUtil.createToken(user.getUsername());
        response.setHeader(WebConstant.JWT.Authorization, token);
        return WebConstant.Auth.LOGIN_SUCCESS;
    }

}
