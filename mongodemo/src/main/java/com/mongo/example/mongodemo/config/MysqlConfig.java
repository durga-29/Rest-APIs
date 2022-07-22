//package com.mongo.example.mongodemo.config;
//
//import javax.sql.DataSource;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class MysqlConfig {
//	
//	@Autowired
//    private Environment env;
//	
//	 @Primary
//	    @Bean
//	    public DataSource userDataSource() {
//	 
//	        DriverManagerDataSource dataSource
//	          = new DriverManagerDataSource();
//	        dataSource.setDriverClassName(
//	          env.getProperty("spring.datasource.driver-class-name"));
//	        dataSource.setUrl(env.getProperty("spring.datasource.jdbc-url"));
//	        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//	        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//
//	        return dataSource;
//	    }
//	 
//	 @Bean
//		public JdbcTemplate jdbcTemplateOne(@Qualifier("userDataSource") DataSource ds)
//		{
//			return new JdbcTemplate(ds);
//		}
//}
