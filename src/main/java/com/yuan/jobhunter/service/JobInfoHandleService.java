package com.yuan.jobhunter.service;

//处理爬取的招聘数据，比如分析薪水范围，打tag之类的
public interface JobInfoHandleService {

	// 分离薪水,找出所有薪水分离出最大值最小值 ,面议是-1
	boolean separateSalary();

	// 删除无效数据
	boolean deleteUnUsedData();// 删除无效数据

}
