package com.coderli.shurnim.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.plugin.PluginParser;
import com.coderli.shurnim.storage.plugin.PluginResource;
import com.coderli.shurnim.storage.plugin.PluginScanner;
import com.coderli.shurnim.storage.plugin.impl.DefaultPluginParser;
import com.coderli.shurnim.storage.plugin.impl.DefaultPluginScanner;
import com.coderli.shurnim.storage.plugin.model.Plugin;
import com.coderli.shurnim.storage.plugin.model.Resource;

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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coderli.shurnim.storage.ShurnimStorage#sycnResource(java.lang.String,
	 * java.util.List, com.coderli.shurnim.storage.plugin.model.Resource)
	 */
	@Override
	public boolean sycnResource(String fromPluginId, List<String> toPluginIds,
			Resource resource) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
