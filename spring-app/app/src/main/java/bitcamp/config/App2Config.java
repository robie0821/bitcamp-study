package bitcamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

@EnableWebMvc
@ComponentScan("bitcamp/app2")
public class App2Config implements WebMvcConfigurer {

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setPrefix("/WEB-INF/jsp2/");
    vr.setSuffix(".jsp");
    vr.setViewClass(JstlView.class);
    return vr;
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    UrlPathHelper pathHelper = new UrlPathHelper();
    pathHelper.setRemoveSemicolonContent(false);
    configurer.setUrlPathHelper(pathHelper);
  }
}