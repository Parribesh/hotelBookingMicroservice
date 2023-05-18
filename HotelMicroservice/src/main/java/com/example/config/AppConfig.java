package com.example.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;

@Configuration
@PropertySource(value="classpath:db.properties")
public class AppConfig {
	
	@Autowired Environment env;
	
	@Bean
	DataSource  dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername(env.getProperty("username"));
		dataSource.setPassword(env.getProperty("password"));
		
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setPackagesToScan("com.example.domain");
		factory.setJpaProperties(jpaProperties());
		factory.afterPropertiesSet();
		return factory;
	}
	
	Properties jpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dailect", "org.hibernate.dialect.MySQL8Dialect");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		//jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		jpaProperties.setProperty("hibernate.show_SQL", "true");
		return jpaProperties;
	}
	
	//PlatformTransactionManager
	@Bean
	 TransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

}
