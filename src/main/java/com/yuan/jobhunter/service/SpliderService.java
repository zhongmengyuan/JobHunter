package com.yuan.jobhunter.service;

import us.codecraft.webmagic.Spider.Status;

public interface SpliderService {

	public boolean startSplider(int threadNum);

	public boolean shutdownSplider();

	public Status getSplierStatus();
}
