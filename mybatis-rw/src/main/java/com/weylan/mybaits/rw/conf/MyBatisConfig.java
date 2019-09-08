package com.weylan.mybaits.rw.conf;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan(value = {"com.weylan.mybaits.rw.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {


  @Resource(name = "routingDataSource")
  private DataSource routingDataSource;


  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(routingDataSource);
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager platformTransactionManager() {
    return new DataSourceTransactionManager(routingDataSource);
  }
}
