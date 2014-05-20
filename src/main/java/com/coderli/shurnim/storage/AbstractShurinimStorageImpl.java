package com.coderli.shurnim.storage;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.plugin.PluginAPI;
import com.coderli.shurnim.storage.plugin.model.Plugin;
import com.coderli.shurnim.storage.plugin.model.Plugin.ApiParam;

/**
 * 后台借口实现的抽象基类<br>
 * 包含了一些基本的工具方法。
 * 
 * @author OneCoder
 * @date 2014年5月11日 下午12:02:09
 * @website http://www.coderli.com
 */
public class AbstractShurinimStorageImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractShurinimStorageImpl.class);
	protected List<Plugin> plugins;
	protected Map<String, PluginAPI> apiMap = new HashMap<String, PluginAPI>();

	/**
	 * 从解析出来的插件列表中获取指定的插件信息。<br>
	 * 
	 * @param pluginId
	 * @return 如果找不到则返回null
	 * @author OneCoder
	 * @date 2014年5月11日 下午7:21:09
	 */
	protected Plugin searchPlugin(String pluginId) {
		if (plugins == null || pluginId == null) {
			logger.warn("没有找到插件: {} 的相关信息。", pluginId);
			return null;
		}
		for (Plugin plugin : plugins) {
			if (pluginId.equals(plugin.getId())) {
				logger.debug("匹配到插件: {} 的相关信息。", pluginId);
				return plugin;
			}
		}
		return null;
	}

	/**
	 * 构造插件借口实例
	 * 
	 * @author OneCoder
	 * @date 2014年5月11日 下午7:20:50
	 */
	@SuppressWarnings("unchecked")
	protected void createInstance() {
		if (plugins == null) {
			return;
		}
		for (Plugin plugin : plugins) {
			String apiClass = plugin.getApiClass();
			String pluginId = plugin.getId();
			try {
				Class<PluginAPI> apiClz = (Class<PluginAPI>) Class
						.forName(apiClass);
				PluginAPI api = apiClz.newInstance();
				List<ApiParam> apiList = plugin.getParams();
				for (ApiParam param : apiList) {
					String paramName = param.getParamName();
					String paramValue = param.getParamValue();
					String setMethodName = getSetMethodName(paramName);
					Method setMethod = apiClz.getMethod(setMethodName,
							String.class);
					setMethod.invoke(api, paramValue);
				}
				api.init();
				apiMap.put(pluginId, api);
			} catch (Exception e) {
				logger.error("构造插件: {} 接口实例失败。", plugin.getName());
				logger.error("异常信息。", e);
			}
		}
	}

	/**
	 * 构造资源完整路径
	 * 
	 * @param parentPath
	 * @param name
	 * @return
	 * @author OneCoder
	 * @date 2014年5月15日 下午10:27:40
	 */
	protected String getFullPath(String parentPath, String name) {
		if (!parentPath.endsWith(File.separator)) {
			parentPath = parentPath + File.separator;
		}
		return parentPath + name;
	}

	/**
	 * @param paramName
	 * @return
	 * @author OneCoder
	 * @date 2014年5月11日 下午8:26:06
	 */
	private String getSetMethodName(String paramName) {
		return "set" + WordUtils.capitalize(paramName);
	}
}
