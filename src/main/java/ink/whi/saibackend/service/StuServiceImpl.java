package ink.whi.saibackend.service;

import ink.whi.saibackend.mapper.StuMapper;
import ink.whi.saibackend.pojo.StuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    StuMapper mapper;

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "all", allEntries = true),
            @CacheEvict(value = "stu", key = "#stuInfo.id")
    })
    public void saveStu(StuInfo stuInfo) {
        stuInfo.getInfo().setSid(stuInfo.getId());
        if (hasStu(stuInfo)) {
            replace(stuInfo);
            return;
        }
        mapper.addStu(stuInfo);
        mapper.addAbility(stuInfo.getInfo());
        for (String s : stuInfo.getInfo().getLanguage()) {
            mapper.addLang(s, stuInfo.getId());
        }
    }

    @Override
    public void replace(StuInfo stuInfo) {
        mapper.updateStuById(stuInfo);
        mapper.updateAbiById(stuInfo.getInfo());
        mapper.deleteLang(stuInfo.getId());
        for (String s : stuInfo.getInfo().getLanguage()) {
            mapper.addLang(s, stuInfo.getId());
        }
    }

    @Override
    public List<StuInfo> getAll() {
        return mapper.getAllInfo();
    }

    @Override
    public StuInfo getStuByID(int id) {
        return mapper.getStuById(id);
    }

    @Override
    public List<StuInfo> queryRJ() {
        return mapper.getR();
    }

    @Override
    public List<StuInfo> queryYJ() {
        return mapper.getY();
    }

    public boolean hasStu(StuInfo stuInfo) {
        return mapper.selectId(stuInfo.getId()) != 0;
    }
}
