package com.coderli.shurnim.storage;

import java.util.List;

import com.coderli.shurnim.storage.plugin.model.Plugin;

/**
 * 后台模块的全局接口<br>
 * 通过该接口使用后台的全部功能
 * 
 * @author OneCoder
 * @date 2014年4月22日 下午9:21:58
 * @website http://www.coderli.com
 */
public interface ShurnimStorage {

	/**
	 * 获取当前支持的插件列表<br>
	 * 没有支持的插件的时候可能返回null
	 * 
	 * @return
	 * @author OneCoder
	 * @date 2014年5月7日 下午8:53:25
	 */
	List<Plugin> getSupportedPlugins();

}
