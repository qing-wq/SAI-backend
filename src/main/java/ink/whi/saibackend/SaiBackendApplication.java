package ink.whi.saibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SaiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaiBackendApplication.class, args);
	}

}
