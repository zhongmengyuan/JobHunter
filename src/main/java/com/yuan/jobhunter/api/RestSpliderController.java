package com.yuan.jobhunter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yuan.jobhunter.base.model.Entity;
import com.yuan.jobhunter.service.SpliderService;

import us.codecraft.webmagic.Spider.Status;

@RestController
@RequestMapping(value = "/api/v1/splider")
public class RestSpliderController {

	@Autowired
	private SpliderService spliderService;

	// 启动爬虫
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ResponseEntity<?> startSplider(@RequestParam(required = false) int threadNum) {
		try {
			if (threadNum == 0) {
				threadNum = 5;
			}

			// StartThread startThread = new StartThread();
			// startThread.setNum(threadNum);
			// startThread.run();
			Thread thread = new Thread(new Runnable() {
				private int num = 5;

				@Override
				public void run() {
					spliderService.startSplider(num);
				}
			});
			thread.start();
			// spliderService.startSplider(threadNum);
			return Entity.success("爬虫启动成功");
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "启动爬虫!" + e.getMessage(), null);
		}
	}

	// 终止爬虫
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public ResponseEntity<?> stopSplider() {
		try {
			spliderService.shutdownSplider();
			System.out.println("爬虫已停止！");
			return Entity.success("爬虫终止成功！");
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "通过关键词获取招聘信息出错!" + e.getMessage(), null);
		}
	}

	// 终止爬虫
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ResponseEntity<Entity<Status>> getSpliderStatus() {
		try {
			Status status = spliderService.getSplierStatus();
			return Entity.success(status);
		} catch (Exception e) {
			System.err.println(e);
			return Entity.builder(500).build(500, "通过关键词获取招聘信息出错!" + e.getMessage(), null);
		}
	}

	class StartThread implements Runnable {

		private int num = 5;

		public void setNum(int num) {
			this.num = num;
		}

		@Override
		public void run() {
			spliderService.startSplider(num);

		}

	}

}
