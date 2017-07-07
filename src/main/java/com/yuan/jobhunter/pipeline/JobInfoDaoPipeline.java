package com.yuan.jobhunter.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuan.jobhunter.dao.JobInfoMapper;
import com.yuan.jobhunter.model.LieTouJobInfo;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author 1432816213@qq.com
 */
@Component("JobInfoDaoPipeline")
public class JobInfoDaoPipeline implements PageModelPipeline<LieTouJobInfo> {
	// 对数据表操作的mapper类
	@Autowired
	private JobInfoMapper jobInfoDAO;

	@Override
	public void process(LieTouJobInfo lieTouJobInfo, Task task) {
		jobInfoDAO.add(lieTouJobInfo);
	}
}
