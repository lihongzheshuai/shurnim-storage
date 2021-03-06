package com.coderli.shurnim.storage;

import java.util.List;
import java.util.Map;

import com.coderli.shurnim.storage.plugin.model.Plugin;
import com.coderli.shurnim.storage.plugin.model.Resource;

/**
 * 后台模块的全局接口<br>
 * 通过该接口使用后台的全部功能。<br>
 * 使用方式:<br>
 * <li>
 * 1.先通过{@link #getSupportedPlugins()}方法获取所有支持的平台/插件列表。 <li>
 * 2.将列表中返回的ID传入对应的接口参数中，进行对应的平台的相关操作。<br>
 * 需要注意的是，不同平台的插件需要给不同的参数赋值，该值可以直接配置在配置文件中。<br>
 * 也可以在运行期动态赋值。(会覆盖配置文件中的值。)<br>
 * 
 * 参数列表的设计，方便UI开发人员动态的根据参数列表生成可填写的控件。并给参数赋值。增强了可扩展性。
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

	/**
	 * 给指定的插件的对应参数赋值<br>
	 * 此处赋值会覆盖配置文件中的默认值
	 * 
	 * @param pluginId
	 *            插件ID
	 * @param paramsKV
	 *            参数键值对
	 * @author OneCoder
	 * @date 2014年5月9日 上午12:41:53
	 */
	void setParamValues(String pluginId, Map<String, String> paramsKV);

	/**
	 * 获取插件对应目录下的资源列表
	 * 
	 * @param pluginId
	 *            插件ID
	 * @param path
	 *            指定路径
	 * @return
	 * @author OneCoder
	 * @date 2014年5月11日 上午8:52:00
	 */
	List<Resource> getResources(String pluginId, String path);

	/**
	 * 同步资源
	 * 
	 * @param fromPluginId
	 *            待同步的插件Id
	 * @param toPluginIds
	 *            目标插件Id
	 * @param resource
	 *            待同步的资源
	 * @return 同步结果
	 * @author OneCoder
	 * @date 2014年5月11日 上午11:41:24
	 */
	boolean syncResource(String fromPluginId, String toPluginId,
			Resource resource) throws Exception;

	/**
	 * 同步整个目录
	 * 
	 * @param fromPluginId
	 *            待同步的插件Id
	 * @param toPluginId
	 *            目标插件Id
	 * @param dirPath
	 *            待同步目录
	 * @param isSyncSubDir
	 *            是否同步子目录
	 * @throws Exception
	 */
	void syncDirectory(String fromPluginId, String toPluginId, String dirPath,
			boolean isSyncSubDir) throws Exception;
}
