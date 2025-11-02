package ch.noseryoung.blj.login;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173",
                                "http://localhost",
                                "capacitor://localhost",
                                "file://",
                                "http://172.20.240.1:8080",
                                "http://192.168.160.137:8080",
                                "http://192.168.169.1:5173",
                                "http://10.0.2.2:5173",
                                "http://133.0.69.43",
                                "138.0.72.04.179",
                                "10.0.2.2",
                                "192.168.160.137"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
