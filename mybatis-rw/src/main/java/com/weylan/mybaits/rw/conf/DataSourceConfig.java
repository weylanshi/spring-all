package com.weylan.mybaits.rw.conf;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.master")
  public DataSource masterDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.slave1")
  public DataSource slave1DataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.slave2")
  public DataSource slave2DataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public DataSource routingDataSource(
      @Qualifier("masterDataSource") DataSource masterDataSource,
      @Qualifier("slave1DataSource") DataSource slave1DataSource,
      @Qualifier("slave2DataSource") DataSource slave2DataSource) {
    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
    targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
    targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
    RoutingDataSource routingDataSource = new RoutingDataSource();
    routingDataSource.setDefaultTargetDataSource(masterDataSource);
    routingDataSource.setTargetDataSources(targetDataSources);
    return routingDataSource;
  }
}
