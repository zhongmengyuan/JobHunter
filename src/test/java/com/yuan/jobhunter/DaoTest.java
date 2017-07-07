package com.yuan.jobhunter;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuan.jobhunter.dao.JobInfoMapper;

public class DaoTest extends SuperTest {

	@Autowired
	private JobInfoMapper jobInfoMapper;

	@Ignore
	@Test
	public void testGetAllJobInfo() {
		/*
		 * List<Map<String, String>> allJobinfo = jobInfoMapper.getAllJobinfo();
		 * System.out.println(allJobinfo.size());
		 */
	}

	// @Ignore
	@Test
	public void testGetJobInfoByKeyword() {

		// List<LieTouJobInfo> byKeyWord = jobInfoMapper.getByKeyWord("java", 0,
		// 10, null, null, null);
		// System.out.println(byKeyWord.size());
	}
}
