package com.yuan.jobhunter.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yuan.jobhunter.base.mapper.IBaseMapper;
import com.yuan.jobhunter.base.model.IBaseModel;

@EnableTransactionManagement
@Configuration
public class SpringMyBatisConfig {
	@Bean
	public DataSource dataSource(@Value("${jdbc.driver}") String jdbcDriver, @Value("${jdbc.url}") String jdbcUrl,
			@Value("${jdbc.username}") String jdbcUsername, @Value("${jdbc.password}") String jdbcPassword) {
		// DataSource source = new DataSource();
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(jdbcDriver);
		source.setUrl(jdbcUrl);
		source.setUsername(jdbcUsername);
		source.setPassword(jdbcPassword);
		source.setTimeBetweenEvictionRunsMillis(1800000);
		source.setValidationQuery("select now()");
		source.setTestWhileIdle(true);
		return source;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	@Bean
	public FactoryBean<SqlSessionFactory> sqlSessionFactory(DataSource source) {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(source);
		sqlSessionFactory.setTypeAliasesPackage("com.yuan.jobhunter");
		sqlSessionFactory.setConfigLocation(new ClassPathResource("config/mybatis.xml"));
		sqlSessionFactory.setTypeAliasesSuperType(IBaseModel.class);
		return sqlSessionFactory;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.yuan.jobhunter");
		mapperScannerConfigurer.setMarkerInterface(IBaseMapper.class);
		return mapperScannerConfigurer;
	}

	// /**
	// * Scheduler Log job
	// */
	// @Bean(name = "jobLogService")
	// public QuartzJobLogService getQuartzJobLogService(DataSource dataSource)
	// throws Exception {
	// QuartzJobLogService quartzJobLogService = new QuartzJobLogService();
	// quartzJobLogService.setDataSource(dataSource);
	// return quartzJobLogService;
	// }
	//
	// @Bean(name = "quartzScheduler")
	// public QuartzSpringBean getQuartzSpringBean(DataSource dataSource,
	// IJobLogService jobLogService) throws Exception {
	// QuartzSpringBean quartzSpringBean = new QuartzSpringBean();
	// quartzSpringBean.setJobLogService(jobLogService);
	// return quartzSpringBean;
	// }
}
