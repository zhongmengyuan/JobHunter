package com.yuan.jobhunter.base.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * Created by 李杨 [liyang@yihecloud.com] on 2016/7/29 9:18.
 */
public class ParentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(this,
				org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE);
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}

}