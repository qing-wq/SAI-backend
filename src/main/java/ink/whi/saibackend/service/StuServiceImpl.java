package ink.whi.saibackend.service;

import ink.whi.saibackend.mapper.StuMapper;
import ink.whi.saibackend.pojo.StuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class StuServiceImpl implements StuService{

    @Autowired
    StuMapper mapper;

    @Override
    @Transactional
    public void saveStu(StuInfo stuInfo) {
        mapper.addStu(stuInfo);
        stuInfo.getInfo().setSid(stuInfo.getId());
        mapper.addAbility(stuInfo.getInfo());
        for (String s : stuInfo.getInfo().getLanguage()) {
            mapper.addLang(s, stuInfo.getId());
        }
    }

    @Override
    public List<StuInfo> getAll() {
        return mapper.getAllInfo();
    }
}
