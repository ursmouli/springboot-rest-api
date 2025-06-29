package com.app.restapi.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = "com.app.jpa.model", entityManagerFactoryRef = "gatewayEntityManagerFactoryBean", transactionManagerRef = "gatewayTransactionManager")
public class JpaConfig {

    @Bean
    public DataSource appDataSource() throws Exception {
        return (DataSource) appDataSourceFactoryBean().getObject();
    }

    @Bean
    public FactoryBean<Object> appDataSourceFactoryBean() {

        JndiObjectFactoryBean factory = new JndiObjectFactoryBean();
        factory.setJndiName("java:comp/env/dsSibos");
        factory.setLookupOnStartup(false);
        factory.setProxyInterface(DataSource.class);

        return factory;
    }

    @Bean
    public PlatformTransactionManager gatewayTransanctionManager(final @Qualifier("gatewayEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean gatewayEntityManagerFactoryBean) throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager(Objects.requireNonNull(gatewayEntityManagerFactoryBean.getObject()));
        transactionManager.setDataSource(appDataSource());
        return transactionManager;
    }

    @Bean
    public EntityManagerFactory gatewayEntityManagerFactory() throws Exception {
        return gatewayEntityManagerFactoryBean(null, null).getObject();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean gatewayEntityManagerFactoryBean(JpaVendorAdapter jpaVendorAdapter, JpaDialect jpaDialect) throws Exception {
        LocalContainerEntityManagerFactoryBean gatewayEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        gatewayEntityManagerFactoryBean.setPersistenceUnitName("gatewayPersistentUnit");
        gatewayEntityManagerFactoryBean.setPersistenceXmlLocation("classpath:META-INF/jpa/persistance-gateway.xml");
        gatewayEntityManagerFactoryBean.setDataSource(appDataSource());
        gatewayEntityManagerFactoryBean.setJpaDialect(jpaDialect);
        gatewayEntityManagerFactoryBean.setJpaPropertyMap(new HashMap<>());
        gatewayEntityManagerFactoryBean.setLoadTimeWeaver(loadTimeWeaver());
        return gatewayEntityManagerFactoryBean;
    }

    @Bean
    public LoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }
}
