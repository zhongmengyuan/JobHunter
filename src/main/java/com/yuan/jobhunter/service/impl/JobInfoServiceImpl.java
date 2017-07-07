package com.yuan.jobhunter.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuan.jobhunter.base.model.Page;
import com.yuan.jobhunter.base.model.PageDTO;
import com.yuan.jobhunter.dao.JobInfoMapper;
import com.yuan.jobhunter.model.LieTouJobInfo;
import com.yuan.jobhunter.model.vo.LieTouJobInfoDto;
import com.yuan.jobhunter.service.JobInfoService;

@Service
@Transactional
public class JobInfoServiceImpl implements JobInfoService {

	@Autowired
	private JobInfoMapper jobInfoDAO;

	@Transactional
	@Override
	public int add(LieTouJobInfo jobInfo) {
		return jobInfoDAO.add(jobInfo);
	}

	@Override
	public LieTouJobInfo getById(long id) {
		return jobInfoDAO.getById(id);
	}

	@Override
	public PageDTO<LieTouJobInfo> getByKeyWord(LieTouJobInfoDto job, Integer pageNo, Integer pageSize) {
		Page<LieTouJobInfo> page = new Page<LieTouJobInfo>(pageNo, pageSize);
		page.addAll(jobInfoDAO.getByKeyWord(job.getKeyword(), page.getStart(), page.getPageSize(), job.getArea(),
				job.getCompany(), job.getTitle(), job.getEducation(), job.getSalary()));
		page.setRecordCount(jobInfoDAO.getCountByKeyWord(job.getKeyword(), job.getArea(), job.getCompany(),
				job.getTitle(), job.getEducation(), job.getSalary()));
		return new PageDTO<>(page.getRecordCount(), page);
	}

	@Override
	public PageDTO<LieTouJobInfo> getByTitle(String title, Integer pageNo, Integer pageSize) {
		Page<LieTouJobInfo> page = new Page<LieTouJobInfo>(pageNo, pageSize);
		page.addAll(jobInfoDAO.getByTitle(title, page.getStart(), page.getPageSize()));
		page.setRecordCount(jobInfoDAO.getCountByTitle(title));
		return new PageDTO<>(page.getRecordCount(), page);
	}

	@Override
	public PageDTO<LieTouJobInfo> getBySalary(String salary, Integer pageNo, Integer pageSize) {
		Page<LieTouJobInfo> page = new Page<LieTouJobInfo>(pageNo, pageSize);
		page.addAll(jobInfoDAO.getBySalary(salary, page.getStart(), page.getPageSize()));
		page.setRecordCount(jobInfoDAO.getCountByTitle(salary));
		return new PageDTO<>(page.getRecordCount(), page);
	}

	@Override
	public PageDTO<LieTouJobInfo> getByCompany(String company, Integer pageNo, Integer pageSize) {
		Page<LieTouJobInfo> page = new Page<LieTouJobInfo>(pageNo, pageSize);
		page.addAll(jobInfoDAO.getByCompany(company, page.getStart(), page.getPageSize()));
		page.setRecordCount(jobInfoDAO.getCountByCompany(company));
		return new PageDTO<>(page.getRecordCount(), page);
	}

	@Override
	public List<LieTouJobInfo> getAllCompany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getAllCompanyAndNum() {
		return null;
	}

	@Override
	public List<Map<String, String>> getAllJobinfo() {
		return null;
	}

	@Override
	public List<Map<String, String>> getTopCompanyAndNum(int topNum) {
		return jobInfoDAO.getAllCompanyAndNum(0, topNum);
	}

	@Override
	public List<Map<String, String>> getTopAreaAndNum(int topNum) {
		return jobInfoDAO.getAllAreaAndNum(0, topNum);
	}

	@Override
	public List<Map<String, String>> getTopJobAndNum(int topNum) {
		return jobInfoDAO.getAllTitleAndNum(0, topNum);
	}

	@Override
	public Integer getAllCompanyCount() {
		return jobInfoDAO.getAllCompanyCount();
	}

	@Override
	public Integer getAllAreaCount() {
		return jobInfoDAO.getAllAreaCount();
	}

	@Override
	public Integer getAllJobCount() {
		return jobInfoDAO.getAllJobCount();
	}

	@Override
	public Map<String, String> getAllExperiencePercent() {
		Map<String, String> result = new HashMap<>();
		List<Map<String, Object>> allExperienceAndNum = jobInfoDAO.getAllExperienceAndNum(0, 999);
		Integer totalCount = jobInfoDAO.getTotalCount();
		//
		int c = 0;
		// 取前7名，其它就叫其它
		DecimalFormat dFormat = new DecimalFormat(".00");
		double used = 0;
		for (Map<String, Object> map : allExperienceAndNum) {
			if (c <= 6) {
				System.out.println(map.get("num").toString());
				Integer num = Integer.parseInt(map.get("num").toString());
				double per = (double) num * 100 / (double) totalCount;
				result.put(map.get("experience").toString(), dFormat.format(per).toString());
				c++;
				used += per;
			} else {
				break;
			}
		}
		result.put("其它", String.valueOf(dFormat.format(100 - used)));
		return result;
	}

	@Override
	public Map<String, String> getAllAgePercent() {
		Map<String, String> result = new HashMap<>();
		List<Map<String, Object>> allAgeAndNum = jobInfoDAO.getAllAgeAndNum(0, 999);
		Integer totalCount = jobInfoDAO.getTotalCount();
		//
		DecimalFormat dFormat = new DecimalFormat(".00");
		double used = 0;
		for (Map<String, Object> map : allAgeAndNum) {
			Integer num = Integer.parseInt(map.get("num").toString());
			double per = (double) num * 100 / (double) totalCount;
			result.put(map.get("age").toString(), dFormat.format(per).toString());
			used += per;

		}
		return result;
	}

	@Override
	public Map<String, String> getAllEducationPercent() {
		Map<String, String> result = new HashMap<>();
		List<Map<String, Object>> allExperienceAndNum = jobInfoDAO.getAllEducationAndNum(0, 999);
		Integer totalCount = jobInfoDAO.getTotalCount();
		// int c = 0;
		// 取前7名，其它就叫其它
		DecimalFormat dFormat = new DecimalFormat(".00");
		double total = 0;
		for (Map<String, Object> map : allExperienceAndNum) {
			// if (c <= 6) {
			Integer num = Integer.parseInt(map.get("num").toString());
			double per = (double) num * 100 / (double) totalCount;
			result.put(map.get("education").toString(), dFormat.format(per).toString());
			// c++;
			total += per;
			// }
		}
		return result;
	}

}
