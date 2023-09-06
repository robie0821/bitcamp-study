package bitcamp.myapp.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected String getServletName() {
    return "app";
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{RootConfig.class, DbConfig.class, MybatisConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{AppConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/app/*"};
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(null, 10000000, 15000000, 1000000));
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[]{new CharacterEncodingFilter("UTF-8")};
  }
}