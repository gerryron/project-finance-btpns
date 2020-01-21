package com.btpnsyariah.finalprojectfinance;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
},scanBasePackages = {"com"})
public class FinalProjectFinanceApplication {

  @Autowired
  private Environment env;

  public static void main(String[] args) {
    SpringApplication.run(FinalProjectFinanceApplication.class, args);
  }

  @Bean(name = "datasource")
  public DataSource getDataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
    dataSource.setUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));

    return dataSource;
  }

  @Autowired
  @Bean(name = "sessionFactory")
  public SessionFactory getSessionFactory (DataSource dataSource) throws IOException {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
    properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
    properties.put("current_session_contex_class", env.getProperty("spring.jpa.properties.hibernate.current_session_contex_class"));
    properties.put("hibernate.id.new_generator_mappings", env.getProperty("spring.jpa.properties.hibernate.id.new_generator_mappings"));
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));

    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setPackagesToScan(new String[] {""});
    factoryBean.setDataSource(dataSource);
    factoryBean.setHibernateProperties(properties);
    factoryBean.afterPropertiesSet();

    SessionFactory sf = factoryBean.getObject();
    return sf;
  }

  @Autowired
  @Bean(name = "transactionManager")
  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
    HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
    return transactionManager;
  }
}
