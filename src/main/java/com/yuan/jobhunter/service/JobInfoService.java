package com.yuan.jobhunter.service;

import java.util.List;
import java.util.Map;

import com.yuan.jobhunter.base.model.PageDTO;
import com.yuan.jobhunter.model.LieTouJobInfo;
import com.yuan.jobhunter.model.vo.LieTouJobInfoDto;

public interface JobInfoService {

	int add(LieTouJobInfo jobInfo);

	LieTouJobInfo getById(long id);

	PageDTO<LieTouJobInfo> getByKeyWord(LieTouJobInfoDto job, Integer pageNo, Integer pageSize);

	PageDTO<LieTouJobInfo> getByTitle(String title, Integer pageNo, Integer pageSize);

	PageDTO<LieTouJobInfo> getBySalary(String salary, Integer pageNo, Integer pageSize);

	PageDTO<LieTouJobInfo> getByCompany(String company, Integer pageNo, Integer pageSize);

	// -- 查询招聘的公司
	List<LieTouJobInfo> getAllCompany();

	// -- 查询所有公司在招岗位数
	List<Map<String, String>> getAllCompanyAndNum();

	// -- 查询所有公司在招岗位数
	List<Map<String, String>> getTopCompanyAndNum(int topNum);

	List<Map<String, String>> getTopAreaAndNum(int topNum);

	List<Map<String, String>> getTopJobAndNum(int topNum);

	// -- 查询所有的工作类别
	List<Map<String, String>> getAllJobinfo();

	// -- 查询工作经验占比
	Map<String, String> getAllExperiencePercent();

	// 获得年龄占比
	Map<String, String> getAllAgePercent();

	// 获得学历占比
	Map<String, String> getAllEducationPercent();

	// 查询所有公司的数量
	Integer getAllCompanyCount();

	// 查询所有地区数量
	Integer getAllAreaCount();

	// 所有工作数量
	Integer getAllJobCount();

}
