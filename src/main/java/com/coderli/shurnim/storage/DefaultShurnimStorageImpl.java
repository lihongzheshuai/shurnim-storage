package com.coderli.shurnim.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.exception.ShurnimException;
import com.coderli.shurnim.storage.plugin.PluginAPI;
import com.coderli.shurnim.storage.plugin.PluginParser;
import com.coderli.shurnim.storage.plugin.PluginResource;
import com.coderli.shurnim.storage.plugin.PluginScanner;
import com.coderli.shurnim.storage.plugin.impl.DefaultPluginParser;
import com.coderli.shurnim.storage.plugin.impl.DefaultPluginScanner;
import com.coderli.shurnim.storage.plugin.model.Plugin;
import com.coderli.shurnim.storage.plugin.model.Plugin.ApiParam;
import com.coderli.shurnim.storage.plugin.model.Resource;
import com.coderli.shurnim.storage.plugin.model.Resource.Type;

/**
 * 默认的后台接口实现类
 * 
 * @author OneCoder
 * @date 2014年4月22日 下午9:25:27
 * @website http://www.coderli.com
 */
public class DefaultShurnimStorageImpl extends AbstractShurinimStorageImpl
		implements ShurnimStorage {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultShurnimStorageImpl.class);
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
		logger.debug("构造插件接口实例。");
		createInstance();
		logger.info("后端接口初始化完成。");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coderli.shurnim.storage.ShurnimStorage#getSupportedPlugins()
	 */
	@Override
	public List<Plugin> getSupportedPlugins() {
		logger.debug("获取支持的插件列表。");
		List<Plugin> clonePlugins = new ArrayList<Plugin>(plugins);
		return clonePlugins;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coderli.shurnim.storage.ShurnimStorage#setParamValues(java.lang.String
	 * , java.util.Map)
	 */
	@Override
	public void setParamValues(String pluginId, Map<String, String> paramsKV) {
		if (pluginId == null || paramsKV == null) {
			logger.error("输入参数为空，不合法。");
			throw new IllegalArgumentException("输入参数为空。不合法。");
		}
		Plugin plugin = searchPlugin(pluginId);
		if (plugin == null) {
			logger.error("没有对应找到指定的插件信息。{}", pluginId);
			throw new ShurnimException("没有找到插件: " + pluginId + " 相关信息。");
		}
		List<ApiParam> params = plugin.getParams();
		Iterator<Entry<String, String>> entryItor = paramsKV.entrySet()
				.iterator();
		while (entryItor.hasNext()) {
			Entry<String, String> entry = entryItor.next();
			String paramKey = entry.getKey();
			for (ApiParam param : params) {
				String pName = param.getParamName();
				if (pName != null && pName.equals(paramKey)) {
					String value = entry.getValue();
					logger.debug("设置参数: {} 的值: {} 。", paramKey, value);
					param.setParamValue(value);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coderli.shurnim.storage.ShurnimStorage#getResources(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<Resource> getResources(String pluginId, String path) {
		if (pluginId == null) {
			logger.error("输入的插件Id为null，不合法。");
			throw new IllegalArgumentException("输入的插件Id为null，不合法。");
		}
		logger.info("准备获取插件: {}, 路径: {} 下的资源列表。", pluginId, path);
		PluginAPI pluginApi = apiMap.get(pluginId);
		if (pluginApi == null) {
			logger.warn("没有找到插件: {} 的接口实例。", pluginId);
			throw new ShurnimException("没有找到插件: " + pluginId + " 的接口实例。");
		}
		return pluginApi.getChildResources(path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coderli.shurnim.storage.ShurnimStorage#sycnResource(java.lang.String,
	 * java.util.List, com.coderli.shurnim.storage.plugin.model.Resource)
	 */
	@Override
	public boolean sycnResource(String fromPluginId, String toPluginId,
			Resource resource) throws Exception {
		if (fromPluginId == null) {
			logger.error("输入的插件Id为null，不合法。");
			throw new IllegalArgumentException("输入的插件Id为null，不合法。");
		}
		PluginAPI fromPluginApi = apiMap.get(fromPluginId);
		if (fromPluginApi == null) {
			logger.warn("没有找到插件: {} 的接口实例。", fromPluginId);
			throw new ShurnimException("没有找到插件: " + fromPluginId + " 的接口实例。");
		}
		if (toPluginId == null) {
			logger.warn("待同步到的插件Id为空。无需同步。");
			return false;
		}
		PluginAPI toPluginApi = apiMap.get(toPluginId);
		if (toPluginApi == null) {
			logger.warn("没有找到插件: {} 的接口实例。略过同步。", toPluginApi);
			return false;
		}
		Type resourceType = resource.getType();
		String resourceName = resource.getName();
		String path = resource.getPath();
		String fullPath = getFullPath(path, resourceName);
		if (resourceType.equals(Type.DIRECTORY)) {
			toPluginApi.mkdir(fullPath, true);
			logger.debug("给插件:{} 创建文件夹: {}", toPluginId, fullPath);
			return true;
		}
		String tempPath = System.getProperty("java.io.tmpdir") + File.separator
				+ resourceName;
		logger.debug("开始将文件: {} 下载到临时文件夹。", resourceName);
		Resource tempResource = fromPluginApi.downloadResource(
				resource.getPath(), resourceName, tempPath);
		logger.debug("文件: {} 下载完成。准备上传。");
		File file = new File(tempPath);
		boolean result = toPluginApi.uploadResource(path, resourceName, file);
		if (result) {
			logger.info("文件: {} 从插件: {} 同步到: {} 完成。", resourceName,
					fromPluginId, toPluginId);
		} else {
			logger.warn("文件: {} 从插件: {} 同步到: {} 失败。", resourceName,
					fromPluginId, toPluginId);

		}
		return result;
	}
}
