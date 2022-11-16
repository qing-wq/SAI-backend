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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    StuService service;

    @CrossOrigin
    @RequestMapping("/submit")
    public String submit(@RequestBody String json) throws IOException {
        try {
            StuInfo stuInfo = JSON.parseObject(json, StuInfo.class);
            service.saveStu(stuInfo);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "网络异常，请稍后再试";
        }
    }


    @CrossOrigin
    @RequestMapping("/query")
    public String query() {
        List<StuInfo> list = service.getAll();
        String json = JSON.toJSONString(list);
        return json;
    }

}
