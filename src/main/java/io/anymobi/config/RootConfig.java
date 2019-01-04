package io.anymobi.config;

import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("io.anymobi.repositories"), @ComponentScan("io.anymobi.services") })
public class RootConfig {

    @Autowired
    private Environment env;

    @Bean//(destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("spring.datasource.driver"));
        hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
        hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(env.getProperty("spring.datasource.password"));

        hikariConfig.setMaximumPoolSize(Integer.parseInt(env.getProperty("hikari.maximum-pool-size")));
        hikariConfig.setConnectionTestQuery(env.getProperty("hikari.connection-test-query"));
        hikariConfig.setPoolName(env.getProperty("hikari.pool-name"));

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", env.getProperty("hikari.cache-prep-stmts"));
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", env.getProperty("hikari.prep-stmt-cache-size"));
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", env.getProperty("hikari.prep-stmt-cache-sql-limit"));
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", env.getProperty("hikari.use-server-prep-stmts"));

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties properties = new Properties();
        properties.put(DIALECT, env.getProperty("jpa.dialect"));
        properties.put(FORMAT_SQL, env.getProperty("jpa.format-sql"));
        properties.put(MAX_FETCH_DEPTH, Integer.parseInt(env.getProperty("jpa.max_fetch_depth")));
        properties.put(STATEMENT_FETCH_SIZE, Integer.parseInt(env.getProperty("jpa.jdbc-fetch_size")));
        properties.put(STATEMENT_BATCH_SIZE, Integer.parseInt(env.getProperty("jpa.jdbc-batch_size")));
        properties.put(SHOW_SQL, env.getProperty("jpa.show-sql"));
        properties.put(HBM2DDL_AUTO, env.getProperty("jpa.hbm2ddl-auto"));
        properties.put(NON_CONTEXTUAL_LOB_CREATION, env.getProperty("jpa.jdbc.lob.non-contextual-creation"));

        emf.setJpaProperties(properties);
        emf.setPackagesToScan("io.anymobi.entity");
        return emf;
    }



    @Bean
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf().getObject());
        return transactionManager;
    }

}
