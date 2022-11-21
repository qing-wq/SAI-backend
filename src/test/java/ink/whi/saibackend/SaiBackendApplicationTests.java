package ink.whi.saibackend;

import ink.whi.saibackend.constant.WebConstant;
import ink.whi.saibackend.mapper.StuMapper;
import ink.whi.saibackend.pojo.AbilityInfo;
import ink.whi.saibackend.pojo.StuInfo;
import ink.whi.saibackend.pojo.Student;
import ink.whi.saibackend.service.StuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SaiBackendApplicationTests {

	@Autowired
	StuMapper mapper;

	@Autowired
	StuService service;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	@Test
	void contextLoads() {
		List<String> list = new ArrayList<>();
		list.add("py");
		list.add("java");
		StuInfo stuInfo = new StuInfo("jingjing", 2101630102, "2702461713@qq.com", "2703482342",
				 "wssb",false,
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

	@Test
	void test2() {
		List<String> list = new ArrayList<>();
		list.add("py");
		list.add("java");
		StuInfo stuInfo = new StuInfo("jingjing", 2101630102, "2702461713@qq.com", "2703482342",
				"wssb",false,
				new AbilityInfo(2101630102, 1, "ruanjian", list, ""));
		service.saveStu(stuInfo);
	}

	@Test
	public void test3() {
		redisTemplate.opsForValue().set("wang","wcs");
		System.out.println(redisTemplate.opsForValue().get("wang"));
	}

	@Test
	public void test4() {
		String source = "root" + "sai2022";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] encode = messageDigest.digest((source + WebConstant.MD5.MD5_SALT).getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();
			for (byte en : encode) {
				hexString.append(String.format("%02x", en));
			}
			System.out.println(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
//			LOGGER.error("[Error]: {}", e.getMessage());
//			return "";
		}
	}

}
