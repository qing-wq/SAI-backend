package ink.whi.saibackend.mapper;

import ink.whi.saibackend.pojo.AbilityInfo;
import ink.whi.saibackend.pojo.StuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StuMapper {

   void addStu(StuInfo stuInfo);

   void addAbility(AbilityInfo abilityInfo);

   void addLang(String lang, String id);

   List<StuInfo> getAllInfo();

   StuInfo getStuById(String id);

   List<StuInfo> getR();

   List<StuInfo> getY();

   Integer selectId(String id);

   void updateStuById(StuInfo stuInfo);

   void updateAbiById(AbilityInfo info);

   void deleteLang(String id);
}
