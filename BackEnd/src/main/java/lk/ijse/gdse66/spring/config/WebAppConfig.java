package lk.ijse.gdse66.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"lk.ijse.gdse66.spring.api"})
@EnableWebMvc
public class WebAppConfig {
    public WebAppConfig() {
        System.out.println("WebAppConfig");
    }
}
