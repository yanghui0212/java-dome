package com.yangqh.common.config.datasource;

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
 * Create time in 2018-07-20 21:24
 */
@Configuration
@MapperScan(basePackages = "com.yangqh.mapper.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DruidDataSourceDb1Config {
    public static final String DB1_MAPPER_LOCATION = "classpath:mapper/db1/*.xml";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1DataSource() {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory db1SqlSessionFactory()
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(db1DataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DB1_MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager db1TransactionManager() {
        return new DataSourceTransactionManager(db1DataSource());
    }

}
