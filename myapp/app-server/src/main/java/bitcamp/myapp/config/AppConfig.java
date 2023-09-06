package bitcamp.myapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@ComponentScan(
        basePackages = {"bitcamp.myapp.controller"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "bitcamp.myapp.controller.MemberController")
)
public class AppConfig {
  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }
}