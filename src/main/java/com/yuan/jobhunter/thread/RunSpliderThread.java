package com.yuan.jobhunter.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuan.jobhunter.service.SpliderService;

@Component
public class RunSpliderThread implements Runnable {
	@Autowired
	private SpliderService spliderService;

	@Override
	public void run() {
		spliderService.startSplider(10);
	}

}
