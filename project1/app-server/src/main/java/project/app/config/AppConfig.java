package project.app.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.util.Bean;
import project.util.ComponentScan;
import project.util.SqlSessionFactoryProxy;

@ComponentScan(basePackages={"project.app.dao", "project.app.handler"})
public class AppConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    return new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("project/app/config/mybatis-config.xml")));
  }
}