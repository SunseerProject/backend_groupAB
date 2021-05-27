package jp.co.sunseer.sunseerservice.sunseerprj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"jp.co.sunseer"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class sunseerprjApplication {

	public static void main(String[] args) {

		SpringApplication.run(sunseerprjApplication.class, args);
	}

}
