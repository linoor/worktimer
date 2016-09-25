package worktimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by linoor on 9/5/16.
 */

@SpringBootApplication
public class WorkTimerApp {
    public static void main(String[] args) {
        SpringApplication.run(WorkTimerApp.class, args);
    }
}
