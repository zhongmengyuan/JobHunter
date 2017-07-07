package com.yuan.jobhunter;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuan.jobhunter.model.LieTouJobInfo;
import com.yuan.jobhunter.service.SpliderService;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

public class JobCrawlerTest extends SuperTest {

	@Autowired
	private PageModelPipeline jobInfoDaoPipeline;

	@Autowired
	private SpliderService spliderService;

	@Ignore
	@Test
	public void testLieBaoSplider() {
		OOSpider.create(
				Site.me().setUserAgent(
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),
				jobInfoDaoPipeline, LieTouJobInfo.class).addUrl("https://www.liepin.com/sojob/?dqs=020&curPage=0")
				.thread(5).run();
	}

	@Test
	public void startSplider() {
		spliderService.startSplider(10);
	}

}
