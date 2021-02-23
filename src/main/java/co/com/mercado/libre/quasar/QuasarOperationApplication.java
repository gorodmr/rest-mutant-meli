package co.com.mercado.libre.quasar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "co.com.mercado.libre.quasar")
public class QuasarOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuasarOperationApplication.class, args);
	}
}
