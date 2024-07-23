package ru.vihub.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final static List<String> HOME_URLS = List.of("/", "/home", "/hello");

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        for (String url : HOME_URLS) {
            registry.addViewController(url).setViewName("home");
        }
    }
}
