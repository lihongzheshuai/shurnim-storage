package com.coderli.shurnim.storage.plugin;

import java.io.File;

import com.coderli.shurnim.storage.plugin.model.Plugin;

/**
 * 实际的文件解析接口<br>
 * 根据不同的文件类型，采用不同的解析方式
 * 
 * @author OneCoder
 * @date 2014年4月20日 上午11:55:12
 * @website http://www.coderli.com
 */
public interface FileParser {

	/**
	 * 解析传入的配置文件
	 * 
	 * @param configFile
	 * @return 返回Plugin模型
	 * @author OneCoder
	 * @date 2014年4月20日 上午11:57:59
	 */
	Plugin parse(File configFile);

}
