package com.app.registration.context;

import org.hibernate.SessionFactory;
import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by User on 2016-11-05.
 */
@Configuration
public class TestCorePersistenceContext {


    @Bean
    public Properties getJPAProperties() {
        final Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }

    @Bean(name= "platformTransactionManager")
    @DependsOn("sessionFactory")
    public PlatformTransactionManager internalTransactionManager(SessionFactory sessionFactory) {
        final HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    @Bean(name = "sessionFactory")
    @DependsOn("dataSource")
    public LocalSessionFactoryBean getAnnotationSessionFactoryBean() {
        final LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setPackagesToScan("com.app.registration.model");
        localSessionFactoryBean.setDataSource(getDataSource());
        localSessionFactoryBean.setHibernateProperties(getJPAProperties());
        return localSessionFactoryBean;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUrl("jdbc:hsqldb:file:/home/konrad/server/test1.db");
        dataSource.setUser("user");
        dataSource.setPassword("pass");
        return dataSource;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
