package ink.whi.saibackend.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.whi.saibackend.mapper.StuMapper;
import ink.whi.saibackend.pojo.StuInfo;
import ink.whi.saibackend.service.StuService;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DataController {

    @Autowired
    StuService service;

    @PostMapping("/submit")
    public String submit(@Validated @RequestBody StuInfo stuInfo, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();
            result.getFieldErrors().forEach((item) -> {
                errMap.put(item.getField(), item.getDefaultMessage());
            });
            return JSON.toJSONString(errMap);
        }
        service.saveStu(stuInfo);
        return "success";
    }


    @GetMapping("/query")
    public String query() {
        List<StuInfo> list = service.getAll();
        return JSON.toJSONString(list);
    }

    @GetMapping("/query/{id}")
    public String queryR(@PathVariable String id) {
        List<StuInfo> list = null;
        if (id.equals("rj")) list = service.queryRJ();
        else if (id.equals("yj")) list = service.queryYJ();
        return JSON.toJSONString(list);
    }


    /**
     * 数据校验
     * 日志
     * 鉴权
     * 缓存
     * 测试事务
     */

}
