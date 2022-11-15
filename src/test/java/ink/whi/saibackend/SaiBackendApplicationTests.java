package ink.whi.saibackend;

import ink.whi.saibackend.mapper.StuMapper;
import ink.whi.saibackend.pojo.AbilityInfo;
import ink.whi.saibackend.pojo.StuInfo;
import ink.whi.saibackend.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SaiBackendApplicationTests {

	@Autowired
	StuMapper mapper;


	@Test
	void contextLoads() {
		List<String> list = new ArrayList<>();
		list.add("py");
		list.add("java");
		StuInfo stuInfo = new StuInfo("jingjing", 2101630102, "2702461713@qq.com", "2703482342",
				false, "wssb",
				new AbilityInfo(2101630102, 1, "ruanjian", list, ""));
		mapper.addStu(stuInfo);
		mapper.addAbility(stuInfo.getInfo());
		for (String s : stuInfo.getInfo().getLanguage()) {
			mapper.addLang(s, stuInfo.getId());
		}
	}

	@Test
	void test() {
		System.out.println(mapper.getAllInfo());
	}

}
