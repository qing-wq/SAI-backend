package ink.whi.saibackend.service;

import ink.whi.saibackend.mapper.StuMapper;
import ink.whi.saibackend.pojo.AbilityInfo;
import ink.whi.saibackend.pojo.StuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    StuMapper stuMapper;

    @Autowired
    StuService stuService;

    @Override
    @Transactional
//    @Caching(evict = {
//            @CacheEvict(value = "all", allEntries = true),
//            @CacheEvict(value = "stu", key = "#stuInfo.id")
//    })
    public void saveStu(StuInfo stuInfo) {
        stuInfo.getInfo().setSid(stuInfo.getId());
        if (hasStu(stuInfo)) {
            stuService.replace(stuInfo);
            return;
        }
        stuMapper.addStu(stuInfo);
        stuMapper.addAbility(stuInfo.getInfo());
        for (String s : stuInfo.getInfo().getLanguage()) {
            stuMapper.addLang(s, stuInfo.getId());
        }
    }

    @Override
    @Transactional
    public void replace(StuInfo stuInfo) {
        stuMapper.updateStuById(stuInfo);
        stuMapper.updateAbiById(stuInfo.getInfo());
        stuMapper.deleteLang(stuInfo.getId());
        for (String s : stuInfo.getInfo().getLanguage()) {
            stuMapper.addLang(s, stuInfo.getId());
        }
    }

    @Override
    public List<StuInfo> getAll() {
        return stuMapper.getAllInfo();
    }

    @Override
    public StuInfo getStuByID(String id) {
        return stuMapper.getStuById(id);
    }

    @Override
    public List<StuInfo> queryRJ() {
        return stuMapper.getR();
    }

    @Override
    public List<StuInfo> queryYJ() {
        return stuMapper.getY();
    }

    public boolean hasStu(StuInfo stuInfo) {
        return stuMapper.selectId(stuInfo.getId()) != 0;
    }
}
