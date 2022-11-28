package ink.whi.saibackend.controller;

import com.alibaba.fastjson.JSON;
import ink.whi.saibackend.exception.BusinessException;
import ink.whi.saibackend.pojo.ApiResult;
import ink.whi.saibackend.pojo.StuInfo;
import ink.whi.saibackend.service.StuService;
import ink.whi.saibackend.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    StuService service;

    @PostMapping("/submit")
    public String submit(@Validated @RequestBody StuInfo stuInfo, BindingResult result) throws IOException {
        Map<String, String> errMap = CheckUtil.CheckResult(result);
        if (!errMap.isEmpty()) {
//            return JSON.toJSONString(errMap);
            throw BusinessException.withErrorCode(JSON.toJSONString(errMap));
        }
        service.saveStu(stuInfo);
//        return ApiResult.success();
        return "success";
    }

    @GetMapping("/query")
//    @Cacheable(value = "all", key = "#root.method.name")
    public String query() {
        List<StuInfo> list = service.getAll();
        return JSON.toJSONString(list);
//        return ApiResult.success(list);
    }

    @GetMapping("/query/{type}")
    public String query2(@PathVariable String type) {
        List<StuInfo> list = null;
        if (type.equals("rj")) list = service.queryRJ();
        else if (type.equals("yj")) list = service.queryYJ();
        return JSON.toJSONString(list);
    }

    @GetMapping("/query/id/{id}")
//    @Cacheable(value = "stu", key = "#id")
    public String queryById(@PathVariable String id) {
        StuInfo stu = service.getStuByID(id);
        return JSON.toJSONString(stu);
    }

}
