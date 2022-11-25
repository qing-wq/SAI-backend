package ink.whi.saibackend;

import ink.whi.saibackend.mapper.StuMapper;
import ink.whi.saibackend.pojo.AbilityInfo;
import ink.whi.saibackend.pojo.StuInfo;
import ink.whi.saibackend.service.StuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


@SpringBootTest
class SaiBackendApplicationTests {

	@Autowired
	StuMapper mapper;

	@Autowired
	StuService service;


	@Test
	void contextLoads() {
	}


}
