package uj.jwzp.w2.e3.launchers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uj.jwzp.w2.e3.logic.converter.YamlHttpMessageConverter;

import java.util.List;

@SpringBootApplication
public class SpringMain implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class, args);
    }

    @Override
    public void extendMessageConverters (List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlHttpMessageConverter());
    }
}
