package com.mayi.mayispringboot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
@MapperScan(basePackages = "com.mayi.mayispringboot.mapper.test02", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSource2Config {

    /**
     * 将datasource注入到容器中
     * 设定为默认的数据源
     * 将对应前缀配置映射为一个对象
     * @return
     */
    @Bean("test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 设置sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/test02/*.xml")
        );
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("test2TranscationManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("test2DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 设置SessionTemplate
     * @param sqlSessionFactory
     * @return
     */
    @Bean("test2SqlSessionTemplate")
    public SqlSessionTemplate test2SqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
