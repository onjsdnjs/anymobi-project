package io.anymobi.config.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataConfig {

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

    protected Environment getEnv() {
        return env;
    }
}
