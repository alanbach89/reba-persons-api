package com.reba.api.person.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DatasourceConfig {

    @Value("${database.host}")
    String host;

    @Value("${database.user}")
    String usernanme;

    @Value("${database.password}")
    String password;

    @Value("${database.schema}")
    String schema;

    @Bean
    public DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://"+host+"/"+schema+"?createDatabaseIfNotExist=true")
                .username(usernanme)
                .password(password)
                .build();
    }
}