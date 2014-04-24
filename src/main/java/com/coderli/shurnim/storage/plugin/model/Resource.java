package com.coderli.shurnim.storage.plugin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Shurnim系统的存储资源模型<br>
 * 根据type不同，分别代表文件夹和文件资源
 * 
 * @author OneCoder
 * @date 2014年4月22日 下午9:51:14
 * @website http://www.coderli.com
 */
public class Resource implements Serializable {

	private static final long serialVersionUID = 5647886829166796976L;

	public enum Type {
		DIRECTORY, FILE
	}

	// 文件夹名
	private String name;
	// 资源类型
	private Type type;
	// 创建时间
	private long ctime;
	// 资源大小
	private long size;

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	// 包含的文件列表
	private List<Resource> subResources;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Resource> getSubResources() {
		return subResources;
	}

	public void setSubResources(List<Resource> subResources) {
		this.subResources = subResources;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
