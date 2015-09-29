package polyquiz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class PolyquizApplication {
    @RequestMapping("/")
    String home() {
	return "Poly quiz home";
    }

    public static void main(String[] args) {
        SpringApplication.run(PolyquizApplication.class, args);
    }
}
