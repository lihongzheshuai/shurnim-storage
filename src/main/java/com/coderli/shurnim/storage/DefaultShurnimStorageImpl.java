package com.coderli.shurnim.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.plugin.PluginParser;
import com.coderli.shurnim.storage.plugin.PluginResource;
import com.coderli.shurnim.storage.plugin.PluginScanner;
import com.coderli.shurnim.storage.plugin.impl.DefaultPluginParser;
import com.coderli.shurnim.storage.plugin.impl.DefaultPluginScanner;
import com.coderli.shurnim.storage.plugin.model.Plugin;

/**
 * 默认的后台接口实现类
 * 
 * @author OneCoder
 * @date 2014年4月22日 下午9:25:27
 * @website http://www.coderli.com
 */
public class DefaultShurnimStorageImpl implements ShurnimStorage {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultShurnimStorageImpl.class);
	private List<Plugin> plugins;
	private PluginScanner pluginScanner = new DefaultPluginScanner();
	private PluginParser pluginParser = new DefaultPluginParser();

	public DefaultShurnimStorageImpl() {
		this(null);
	}

	public DefaultShurnimStorageImpl(String pluginFolder) {
		logger.info("开始初始化后端接口。");
		if (pluginFolder == null) {
			pluginFolder = getPluginFolder();
		}
		logger.debug("开始扫描插件文件夹: {} 。", pluginFolder);
		List<PluginResource> pluginResourceList = pluginScanner
				.scan(pluginFolder);
		if (pluginResourceList.size() == 0) {
			logger.warn("没有找到插件配置文件。后端接口无法使用。");
			throw new RuntimeException("没有找到插件配置文件。后端接口无法使用。");
		}
		logger.debug("开始解析插件配置文件。");
		plugins = pluginParser.parse(pluginResourceList);
	}

	/**
	 * 获取插件配置所在文件夹路径
	 * 
	 * @return
	 * @author OneCoder
	 * @date 2014年4月22日 下午9:29:09
	 */
	private String getPluginFolder() {
		String projectHome = System.getProperty("user.dir");
		return projectHome + File.separator + "plugins";
	}
}
