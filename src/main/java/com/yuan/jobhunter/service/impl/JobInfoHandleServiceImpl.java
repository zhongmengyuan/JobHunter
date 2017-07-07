package com.yuan.jobhunter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuan.jobhunter.dao.JobInfoMapper;
import com.yuan.jobhunter.model.LieTouJobInfo;
import com.yuan.jobhunter.service.JobInfoHandleService;

@Service
@Transactional
public class JobInfoHandleServiceImpl implements JobInfoHandleService {

	@Autowired
	private JobInfoMapper jobInfoDAO;

	@Override
	public boolean separateSalary() {
		try {
			List<LieTouJobInfo> allsalary = jobInfoDAO.getAllsalary();
			for (LieTouJobInfo lieTouJobInfo : allsalary) {
				jobInfoDAO.updateSalary(lieTouJobInfo);
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteUnUsedData() {
		try {
			Integer deleteUsedData = jobInfoDAO.deleteUsedData();
			System.out.println("无用数据删除" + deleteUsedData);
		} catch (Exception e) {
			System.out.println(e + "删除数据操作失败！");
			return false;
		}
		return true;
	}

}
