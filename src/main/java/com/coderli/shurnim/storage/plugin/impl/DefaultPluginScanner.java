package com.coderli.shurnim.storage.plugin.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.common.Constants;
import com.coderli.shurnim.storage.plugin.PluginResource;
import com.coderli.shurnim.storage.plugin.PluginScanner;

/**
 * 默认的插件扫描器<br>
 * 扫描规则为: 从给定的目录扫描名为*-plugin.xml的文件。<br>
 * 如果给定的目录地址无效，则抛出 {@code IllegalArgumentException}。
 * 
 * 
 * @author OneCoder
 * @date 2014年4月19日 上午11:27:37
 * @website http://www.coderli.com
 */
public class DefaultPluginScanner extends AbstractPluginScanner implements
		PluginScanner {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultPluginScanner.class);

	@Override
	public List<PluginResource> scan(String pluginFolder) {
		if (!validatePluginFolder(pluginFolder)) {
			logger.error("待扫描的插件路径不合法。{}", pluginFolder);
			throw new IllegalArgumentException("待扫描的插件路径不合法。");
		}
		File pluginFolderFile = new File(pluginFolder);
		File[] allFiles = pluginFolderFile.listFiles();
		if (allFiles == null) {
			logger.error("插件配置文件夹: {} 中没有文件。", pluginFolder);
			throw new IllegalArgumentException("待扫描的插件路径不合法。");
		}
		List<PluginResource> resources = new ArrayList<PluginResource>();
		for (File file : allFiles) {
			String fileName = file.getName();
			if (fileName.endsWith(Constants.XML_PLUGIN_CONFIG_FILE_POSTFIX)) {
				logger.debug("检测到插件配置文件: {}。", fileName);
				PluginResource pResource = new XmlPluginResource(file);
				resources.add(pResource);
			}
		}
		return resources;
	}
}
