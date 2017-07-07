package com.yuan.jobhunter.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.yuan.jobhunter.service.JobInfoHandleService;

@Configuration
@SpringBootApplication
@Import({ SpringMyBatisConfig.class, SpringWebMvcConfig.class })
@ComponentScan(value = { "com.yuan.jobhunter" })
public class SampleTomcatApplication implements ServletContextInitializer {
	private static Log logger = LogFactory.getLog(SampleTomcatApplication.class);
	public static ConfigurableApplicationContext applicationContext = null;

	public static void main(String[] args) {
		// 日志纪录会用到这个Key
		applicationContext = SpringApplication.run(SampleTomcatApplication.class, args);
		System.out.println("系统启动成功");
		JobInfoHandleService bean = (JobInfoHandleService) applicationContext.getBean("jobInfoHandleServiceImpl");
		bean.deleteUnUsedData();// 系统启动成功后删除无用的数据
		// bean.separateSalary();//
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

	}

}
