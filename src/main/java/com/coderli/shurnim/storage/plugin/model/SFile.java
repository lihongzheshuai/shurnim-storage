package com.coderli.shurnim.storage.plugin.model;

import java.io.Serializable;

/**
 * Shurnim系统的文件模型<br>
 * S是一个前缀，用于区分
 * 
 * @author OneCoder
 * @date 2014年4月22日 下午9:57:16
 * @website http://www.coderli.com
 */
public class SFile implements Serializable {

	private static final long serialVersionUID = -1161814824136771104L;
	// 文件名
	private String name;
	// 文件大小
	private long size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
