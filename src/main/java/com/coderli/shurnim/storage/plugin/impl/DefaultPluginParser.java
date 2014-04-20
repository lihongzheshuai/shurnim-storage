package com.coderli.shurnim.storage.plugin.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.plugin.FileParser;
import com.coderli.shurnim.storage.plugin.PluginParser;
import com.coderli.shurnim.storage.plugin.PluginResource;
import com.coderli.shurnim.storage.plugin.model.Plugin;

/**
 * 默认的配置文件解析类
 * 
 * @author OneCoder
 * @date 2014年4月20日 上午11:16:40
 * @website http://www.coderli.com
 */
public class DefaultPluginParser implements PluginParser {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultPluginParser.class);

	@Override
	public List<Plugin> parse(List<PluginResource> pluginResources) {
		logger.debug("开始解析插件资源列表。插件数目: {}。", pluginResources.size());
		List<Plugin> plugins = new ArrayList<Plugin>();
		for (PluginResource pluginResource : pluginResources) {
			File configFile = pluginResource.getConfigFile();
			FileParser parser = pluginResource.getFileParser();
			Plugin plugin = parser.parse(configFile);
			// 此处需要判断是否为空，因为当插件配置不合法的时候，会返回null。略过此插件。
			if (plugin != null) {
				plugins.add(plugin);
			}
		}
		return plugins;
	}
}
