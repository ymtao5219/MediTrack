package MediTrack.Medi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MediApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediApplication.class, args);
	}

	@GetMapping("/")
	public String apiRoot(){
		return "Hello World";
	}
}
