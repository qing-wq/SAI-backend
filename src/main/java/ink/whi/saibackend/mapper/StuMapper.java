package ink.whi.saibackend.mapper;

import ink.whi.saibackend.pojo.AbilityInfo;
import ink.whi.saibackend.pojo.StuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StuMapper {

   void addStu(StuInfo stuInfo);

   void addAbility(AbilityInfo abilityInfo);

   void addLang(String lang, int id);

   List<StuInfo> getAllInfo();

   int selectId(int id);

   void updateStuById(StuInfo stuInfo);

   void updateAbiById(AbilityInfo info);

   void deleteLang(int id);
}
