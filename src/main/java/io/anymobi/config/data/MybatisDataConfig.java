package io.anymobi.config.data;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan("io.anymobi.repositories.mybatis.mapper")
public class MybatisDataConfig extends DataConfig {

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sb = new SqlSessionFactoryBean();
        sb.setDataSource(dataSource);
        sb.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/config_mybatis.xml"));
        sb.setMapperLocations(new Resource[] { new ClassPathResource("mybatis/mapper/MemberMapper.xml"),
                new ClassPathResource("mybatis/mapper/DeptMapper.xml") });
        return sb.getObject();
    }

   /* @Bean
    public DataSourceTransactionManager txManager(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }*/
}
