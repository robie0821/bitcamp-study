package bitcamp.myapp.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@ComponentScan(basePackages = {
        "bitcamp.myapp.dao",
        "bitcamp.myapp.controller",
        "bitcamp.myapp.service"})
@PropertySource({"classpath:bitcamp/myapp/config/ncloud/jdbc.properties"})
@MapperScan("bitcamp.myapp.dao")
public class AppConfig {

//  @Bean
//  public SqlSessionFactory sqlSessionFactory() throws Exception {
//    return new SqlSessionFactoryProxy(
//            new SqlSessionFactoryBuilder().build(
//                    Resources.getResourceAsStream("bitcamp/myapp/config/mybatis-config.xml")));
//  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx) throws Exception {

    org.apache.ibatis.logging.LogFactory.useLog4J2Logging();

    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("bitcamp.myapp.vo");

    factoryBean.setMapperLocations(appCtx.getResources("classpath:bitcamp/myapp/dao/mysql/*Dao.xml"));

    return factoryBean.getObject();
  }

  @Bean
  public DataSource dataSource(
          @Value("${jdbc.driver}") String driver,
          @Value("${jdbc.url}") String url,
          @Value("${jdbc.username}") String username,
          @Value("${jdbc.password}") String password) {

    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(driver);
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    return ds;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}