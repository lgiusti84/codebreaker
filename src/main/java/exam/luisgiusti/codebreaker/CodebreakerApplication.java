package exam.luisgiusti.codebreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:dna.properties")
public class CodebreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodebreakerApplication.class, args);
	}
}
