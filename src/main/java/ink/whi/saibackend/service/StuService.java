package ink.whi.saibackend.service;

import ink.whi.saibackend.pojo.StuInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StuService {

    void saveStu(StuInfo stuInfo);

    List<StuInfo> getAll();

}
