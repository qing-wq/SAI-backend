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
public class DataController {

    @Autowired
    StuService service;

    @CrossOrigin
    @PostMapping("/submit")
    public String submit(@Validated @RequestBody StuInfo stuInfo, BindingResult result) throws IOException {
//            StuInfo stuInfo = JSON.parseObject(json, StuInfo.class);
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


    @CrossOrigin
    @RequestMapping("/query")
    public String query() {
        List<StuInfo> list = service.getAll();
        String json = JSON.toJSONString(list);
        return json;
    }


    /**
     * 数据校验
     * 日志
     * 鉴权
     * 缓存
     */

}
