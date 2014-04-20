package com.coderli.shurnim.storage.plugin;

import java.util.List;

import com.coderli.shurnim.storage.plugin.model.Plugin;

/**
 * 插件配置文件解析接口
 * 
 * @author OneCoder
 * @date 2014年4月20日 上午11:12:57
 * @website http://www.coderli.com
 */
public interface PluginParser {

	/**
	 * 解析插件配置文件
	 * 
	 * @param configFiles
	 *            待解析的资源列表
	 * @return 解析配置文件得到的模型
	 * @author OneCoder
	 * @date 2014年4月20日 上午11:13:35
	 */
	List<Plugin> parse(List<PluginResource> pluginResources);

}
