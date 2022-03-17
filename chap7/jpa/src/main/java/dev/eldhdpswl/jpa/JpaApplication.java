package dev.eldhdpswl.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing  //auditing은 스프링부트 기본 configuration 설정에 들어가있지 않기 때문에 허용을 해야된다.
// @EnableJpaAuditing를 지운 이유는 테스트를 진행하면서 JPA 기능들을 사용하게 되면 테스트를 할떄 애플리케이션 전체를 구성하기에는 비효율적이기 때문에 그래서 JpaAuditConfig 클래스에 붙인다.
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
