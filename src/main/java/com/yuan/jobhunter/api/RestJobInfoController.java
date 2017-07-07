package com.yuan.jobhunter.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yuan.jobhunter.base.model.Entity;
import com.yuan.jobhunter.base.model.PageDTO;
import com.yuan.jobhunter.model.LieTouJobInfo;
import com.yuan.jobhunter.model.vo.LieTouJobInfoDto;
import com.yuan.jobhunter.service.JobInfoService;

@RestController
@RequestMapping(value = "/api/v1/job")
public class RestJobInfoController {

	@Autowired
	private JobInfoService jobInfoService;

	// 此接口获取
	@RequestMapping(value = "/list")
	public ResponseEntity<Entity<PageDTO<LieTouJobInfo>>> getJobs(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String area, @RequestParam(required = false) String company,
			@RequestParam(required = false) String title, @RequestParam(required = false) String salary,
			@RequestParam(required = false) String education, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {
		try {
			if (keyword == null) {
				keyword = "";
			}
			pageNo = pageNo == null ? 1 : pageNo;
			pageSize = pageSize == null ? 10 : pageSize;
			LieTouJobInfoDto jobInfoDto = new LieTouJobInfoDto();
			jobInfoDto.setTitle(title);
			jobInfoDto.setKeyword(keyword);
			jobInfoDto.setCompany(company);
			jobInfoDto.setArea(area);
			jobInfoDto.setEducation(education);
			jobInfoDto.setSalary(salary);
			PageDTO<LieTouJobInfo> byKeyWord = jobInfoService.getByKeyWord(jobInfoDto, pageNo, pageSize);
			return Entity.success(byKeyWord);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "通过关键词获取招聘信息出错!" + e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/top/job")
	public ResponseEntity<Entity<Map<String, Object>>> getTopJob(@RequestParam(required = false) Integer topNum) {
		try {
			List<Map<String, String>> topJobAndNum = jobInfoService.getTopJobAndNum(topNum);
			Integer allJobCount = jobInfoService.getAllJobCount();
			Map<String, Object> map = new HashMap<>();
			map.put("list", topJobAndNum);
			map.put("count", allJobCount);
			return Entity.success(map);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "通过活跃工作信息出错!" + e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/top/area")
	public ResponseEntity<Entity<Map<String, Object>>> getTopArea(@RequestParam(required = false) Integer topNum) {
		try {
			List<Map<String, String>> topAreaAndNumAndNum = jobInfoService.getTopAreaAndNum(topNum);
			Integer allAreaCountCount = jobInfoService.getAllAreaCount();
			Map<String, Object> map = new HashMap<>();
			map.put("list", topAreaAndNumAndNum);
			map.put("count", allAreaCountCount);
			return Entity.success(map);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "获取活跃地区信息出错!" + e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/top/company")
	public ResponseEntity<Entity<Map<String, Object>>> getTopCompany(@RequestParam(required = false) Integer topNum) {
		try {
			List<Map<String, String>> topCompanyAndNumAndNum = jobInfoService.getTopCompanyAndNum(topNum);
			Integer allAreaCompanyCount = jobInfoService.getAllCompanyCount();
			Map<String, Object> map = new HashMap<>();
			map.put("list", topCompanyAndNumAndNum);
			map.put("count", allAreaCompanyCount);
			return Entity.success(map);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "获取活跃公司信息出错" + e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/percent/age")
	public ResponseEntity<Entity<Map<String, String>>> getPercentAge() {
		try {
			Map<String, String> map = jobInfoService.getAllAgePercent();
			return Entity.success(map);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "获取活跃公司信息出错" + e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/percent/experience")
	public ResponseEntity<Entity<Map<String, String>>> getPercentExperience() {
		try {
			Map<String, String> map = jobInfoService.getAllExperiencePercent();
			return Entity.success(map);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "经验比重查询出错！" + e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/percent/education")
	public ResponseEntity<Entity<Map<String, String>>> getPercentEducation() {
		try {
			Map<String, String> map = jobInfoService.getAllEducationPercent();
			return Entity.success(map);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "学历比重查询出错" + e.getMessage(), null);
		}
	}
}
