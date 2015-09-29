package polyquiz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class PolyquizApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolyquizApplication.class, args);
    }
}
