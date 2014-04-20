package com.coderli.shurnim.storage.plugin;

import java.io.File;

/**
 * 插件资源<br>
 * 定义了插件的描述信息以及对应的处理器信息(例如: 文件解析器。{@code PluginParser})
 * 
 * @author OneCoder
 * @date 2014年4月20日 下午8:53:17
 * @website http://www.coderli.com
 */
public interface PluginResource {

	/**
	 * 获取对应的配置文件解析器
	 * 
	 * @return
	 * @author OneCoder
	 * @date 2014年4月20日 下午8:56:32
	 */
	FileParser getFileParser();

	/**
	 * 获取实际的文件资源
	 * 
	 * @return
	 * @author OneCoder
	 * @date 2014年4月20日 下午8:58:37
	 */
	File getConfigFile();

}
