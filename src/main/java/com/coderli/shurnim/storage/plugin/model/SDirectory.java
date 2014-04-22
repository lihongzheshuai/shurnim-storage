package com.coderli.shurnim.storage.plugin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Shurnim系统的文件夹模型<br>
 * 为了区别，统一在对应的单词前加上S前缀
 * 
 * @author OneCoder
 * @date 2014年4月22日 下午9:51:14
 * @website http://www.coderli.com
 */
public class SDirectory implements Serializable {

	private static final long serialVersionUID = 5647886829166796976L;
	// 文件夹名
	private String name;
	// 包含的子文件夹列表
	private List<SDirectory> subDirectorys;
	// 包含的文件列表
	private List<SFile> subFiles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SDirectory> getSubDirectorys() {
		return subDirectorys;
	}

	public void setSubDirectorys(List<SDirectory> subDirectorys) {
		this.subDirectorys = subDirectorys;
	}

	public List<SFile> getSubFiles() {
		return subFiles;
	}

	public void setSubFiles(List<SFile> subFiles) {
		this.subFiles = subFiles;
	}

}
