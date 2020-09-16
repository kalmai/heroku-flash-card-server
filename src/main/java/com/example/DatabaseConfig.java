package com.example;

import com.zaxxer.hikari.*;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;


@Configuration
public class DatabaseConfig {
	  @Value("${spring.datasource.url}")
	  private String dbUrl;

	  @Bean
	  public BasicDataSource dataSource() {
	      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("org.postgresql.Driver");
	      String herokuDbUrl = System.getenv("JDBC_DATABASE_URL");
	      if (herokuDbUrl != null) {
	    	  dataSource.setUrl(herokuDbUrl);
	      }
	      else {
	    	  dataSource.setUrl("jdbc:postgresql://localhost:5432/flashcard");
	    	  dataSource.setUsername("postgres");
	    	  dataSource.setPassword("postgres1");
	      }
	      return dataSource;
	  }
	  
}