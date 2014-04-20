package com.coderli.shurnim.storage.plugin;

import java.util.List;

/**
 * 插件配置文件扫描器<br>
 * 用于扫描当前系统中包含集中云存储的插件<br>
 * 
 * @author OneCoder
 * @date 2014年4月19日 上午11:23:50
 * @website http://www.coderli.com
 */
public interface PluginScanner {

	/**
	 * 从指定的目录下扫描插件的配置文件
	 * 
	 * @param pluginFolder
	 *            待扫描目录
	 * @return 插件资源队列
	 * @author OneCoder
	 * @date 2014年4月19日 上午11:25:59
	 */
	List<PluginResource> scan(String pluginFolder);

}
