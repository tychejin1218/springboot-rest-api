package com.example.springbootrestapi.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = "com.example.springbootrestapi.service")
@MapperScan(
    basePackages = "com.example.springbootrestapi.mapper",
    sqlSessionFactoryRef = "sqlSessionFactory"
)
@Configuration
public class DataSourceConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .build();
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource,
      ApplicationContext applicationContext
  ) throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver()
            .getResources("classpath:mapper/*.xml"));
    return sessionFactory.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSession(
      SqlSessionFactory sqlSessionFactory
  ) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }
}