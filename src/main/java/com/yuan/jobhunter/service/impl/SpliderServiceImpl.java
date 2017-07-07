package com.yuan.jobhunter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuan.jobhunter.model.LieTouJobInfo;
import com.yuan.jobhunter.service.SpliderService;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Service
public class SpliderServiceImpl implements SpliderService {

	@Autowired
	private PageModelPipeline jobInfoDaoPipeline;

	// 爬虫配置，设置pipeline和pageProcessor
	private OOSpider splider = OOSpider.create(
			Site.me().setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),
			jobInfoDaoPipeline, LieTouJobInfo.class);

	// 启动爬虫
	@Override
	public boolean startSplider(int threadNum) {
		try {
			// 设置爬虫入口地址，并启动爬虫
			splider.addUrl("https://www.liepin.com/sojob/?dqs=020&curPage=0").thread(threadNum).run();
			SpiderMonitor.instance().register(splider);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

	}

	// 终止爬虫
	@Override
	public boolean shutdownSplider() {
		try {
			splider.stop();
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	@Override
	public Status getSplierStatus() {
		Status status = splider.getStatus();
		return status;
	}
}
