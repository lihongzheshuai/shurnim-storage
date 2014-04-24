package com.coderli.shurnim.storage.plugin;

import java.util.List;

import com.coderli.shurnim.storage.plugin.model.Resource;

/**
 * 各种云存储插件需要实现的通用接口
 * 
 * @author OneCoder
 * @date 2014年4月22日 下午9:43:41
 * @website http://www.coderli.com
 */
public interface PluginAPI {

	/**
	 * 获取子资源列表
	 * 
	 * @param parentPath
	 * @return
	 * @author OneCoder
	 * @date 2014年4月24日 下午11:29:14
	 */
	List<Resource> getChildResources(String parentPath);

	/**
	 * 下载特定的资源
	 * 
	 * @param parentPath
	 *            目录路径
	 * @param name
	 *            资源名称
	 * @return
	 * @author OneCoder
	 * @date 2014年4月24日 下午11:30:19
	 */
	Resource downloadResource(String parentPath, String name);

}
