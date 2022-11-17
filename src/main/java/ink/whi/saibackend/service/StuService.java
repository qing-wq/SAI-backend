package ink.whi.saibackend.service;

import ink.whi.saibackend.pojo.StuInfo;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface StuService {

    void saveStu(StuInfo stuInform);

    List<StuInfo> getAll();

}
