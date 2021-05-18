package com.selimsql.lesson.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.selimsql.lesson.configuration" })
@PropertySource(value = { "classpath:spring/application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.selimsql.lesson.domain" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
    }

    @Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource(); //Uses Poolable Connection.

	    String driverClassName = environment.getProperty("jdbc.driverClassName");
	    dataSource.setDriverClassName(driverClassName);

		String driverUrl = environment.getProperty("jdbc.url");
	    dataSource.setUrl(driverUrl);

		String user = environment.getProperty("jdbc.username");
	    dataSource.setUsername(user);

		String pass = environment.getProperty("jdbc.password");
	    dataSource.setPassword(pass);

	    return dataSource;
	}

	@Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        String key = "hibernate.dialect";
        String dialect = environment.getRequiredProperty(key);
        properties.put(key, dialect);

        key = "hibernate.show_sql";
        String showSql = environment.getRequiredProperty(key);
        properties.put(key, showSql);

        key = "hibernate.format_sql";
        String formatSql = environment.getRequiredProperty(key);
        properties.put(key, formatSql);
        return properties;
    }

	@Bean
	@Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
    }
}
