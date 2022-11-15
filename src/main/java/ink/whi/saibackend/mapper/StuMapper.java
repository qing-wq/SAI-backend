package ink.whi.saibackend.mapper;

import ink.whi.saibackend.pojo.AbilityInfo;
import ink.whi.saibackend.pojo.StuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StuMapper {

   int addStu(StuInfo stuInfo);

   int addAbility(AbilityInfo abilityInfo);

   int addLang( String lang, int id);

   List<StuInfo> getAllInfo();

}
