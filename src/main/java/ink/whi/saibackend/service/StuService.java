package ink.whi.saibackend.service;

import ink.whi.saibackend.pojo.StuInfo;

import java.util.List;

public interface StuService {

    void saveStu(StuInfo stuInform);

    List<StuInfo> getAll();

    StuInfo getStuByID(int id);

    List<StuInfo> queryRJ();

    List<StuInfo> queryYJ();

    void replace(StuInfo stuInfo);

}
