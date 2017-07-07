package com.yuan.jobhunter.base.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 用于Rest调用分页
 * @author dengteng
 *
 * 
 */
public class PageDTO<T> extends ParentDTO {
	private int count;
	private List<T> pageList;
	
	public PageDTO(){}

	public PageDTO(int count, List<T> pageList) {
		this.count = count;
		this.pageList = pageList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getPageList() {
		if (pageList == null)
			return new ArrayList<>();
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}


}
