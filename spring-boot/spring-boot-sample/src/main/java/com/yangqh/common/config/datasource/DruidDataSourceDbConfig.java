package com.yangqh.common.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author yangq
 * Create time in 2018-07-20 21:24
 */
@Configuration
@MapperScan(basePackages = "com.yangqh.mapper.db",sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDataSourceDbConfig {

    private static final String DB_MAPPER_LOCATION =  "classpath:mapper/db/*.xml";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DB_MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
