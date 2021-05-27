package jp.co.sunseer.sunseerservice.sunseerhht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"jp.co.sunseer"})
@SpringBootApplication
public class sunseerhttApplication {

    public static void main(String[] args) {
        SpringApplication.run(sunseerhttApplication.class, args);
    }
}
