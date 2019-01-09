package io.anymobi.config.data;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import java.util.Properties;
import static org.hibernate.cfg.Environment.*;

@Configuration
@EnableJpaRepositories(value = "io.anymobi.repositories.jpa", entityManagerFactoryRef = "emf")
public class JpaDataConfig extends DataConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties properties = new Properties();
        properties.put(DIALECT, getEnv().getProperty("jpa.dialect"));
        properties.put(FORMAT_SQL, getEnv().getProperty("jpa.format-sql"));
        properties.put(MAX_FETCH_DEPTH, Integer.parseInt(getEnv().getProperty("jpa.max_fetch_depth")));
        properties.put(STATEMENT_FETCH_SIZE, Integer.parseInt(getEnv().getProperty("jpa.jdbc-fetch_size")));
        properties.put(STATEMENT_BATCH_SIZE, Integer.parseInt(getEnv().getProperty("jpa.jdbc-batch_size")));
        properties.put(SHOW_SQL, getEnv().getProperty("jpa.show-sql"));
        properties.put(HBM2DDL_AUTO, getEnv().getProperty("jpa.hbm2ddl-auto"));
        properties.put(NON_CONTEXTUAL_LOB_CREATION, getEnv().getProperty("jpa.jdbc.lob.non-contextual-creation"));

        emf.setJpaProperties(properties);
        emf.setPackagesToScan("io.anymobi.domain.entity");
        return emf;
    }

    @Bean(name = "transactionManager")
    @Primary
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf().getObject());
        return transactionManager;
    }

}
