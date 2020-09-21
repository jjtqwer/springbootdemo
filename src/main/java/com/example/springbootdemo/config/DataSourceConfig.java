package com.example.springbootdemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.minmumIdle}")
    int minmumIdle;
    @Value("${spring.datasource.maxmumPoolSize}")
    int maxmumPoolSize;
    @Value("${spring.datasource.maxLifeTime}")
    long maxLifetime;
    @Value("${spring.datasource.leakDetectionThreshold}")
    long leakDetectionThreshold;

    @Bean
    @Primary
    public DataSource dataSource(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMinimumIdle(minmumIdle);
        hikariConfig.setMaximumPoolSize(maxmumPoolSize);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setLeakDetectionThreshold(leakDetectionThreshold);
        HikariDataSource dataSource=new HikariDataSource(hikariConfig);
        return dataSource;
    }
}
