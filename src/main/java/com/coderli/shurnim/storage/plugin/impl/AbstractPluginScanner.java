package com.coderli.shurnim.storage.plugin.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 插件扫描器的抽象基类<br>
 * 提供一些常用的通用工具方法，如校验路径是否合法等
 * 
 * @author OneCoder
 * @date 2014年4月19日 上午11:34:08
 * @website http://www.coderli.com
 */
public abstract class AbstractPluginScanner {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractPluginScanner.class);

	/**
	 * 校验插件扫描目录路径是否合法<br>
	 * 依次考察，路径参数是否为空，是否存在，是否为目录，是否可读。 <br>
	 * 全部通过则返回true。 否则返回false。
	 * 
	 * @param folderPath
	 * @return
	 * @author OneCoder
	 * @date 2014年4月19日 上午11:35:32
	 */
	protected boolean validatePluginFolder(String folderPath) {
		if (folderPath == null) {
			logger.error("待校验的路径为空，不合法。");
			return false;
		}
		File folderFile = new File(folderPath);
		if (!folderFile.exists()) {
			logger.error("待校验的路径不存在。{}", folderPath);
			return false;
		}
		if (!folderFile.isDirectory()) {
			logger.error("待校验的路径不是目录。{}", folderPath);
			return false;
		}
		if (!folderFile.canRead()) {
			logger.error("待校验的路径不可读。{}", folderPath);
			return false;
		}
		return true;
	}
}
