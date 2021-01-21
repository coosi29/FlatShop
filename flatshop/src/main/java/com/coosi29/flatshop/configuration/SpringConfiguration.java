package com.coosi29.flatshop.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "classpath:database.properties")
@EnableTransactionManagement
public class SpringConfiguration {

	@Autowired
	Environment environment;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("driver"));
		dataSource.setUrl(environment.getProperty("url"));
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.coosi29.flatshop.entity");
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		
		bean.setHibernateProperties(hibernateProperties);
		return bean;
	}
	
	@Bean(name = "transactionManager")
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
	}
	
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource());
//	}
}
