package com.yqh.springcloud.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author yangq
 * Create time in 2018-08-01 12:46
 */
@Configuration
@MapperScan(basePackages = "com.yangqh.mapper", sqlSessionFactoryRef = "sqlSessionFactory")

public class DruidDataSourceConfig {

    public static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory()
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager db1TransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
