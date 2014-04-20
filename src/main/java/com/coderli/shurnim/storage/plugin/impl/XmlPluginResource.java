package com.coderli.shurnim.storage.plugin.impl;

import java.io.File;

import com.coderli.shurnim.storage.plugin.FileParser;
import com.coderli.shurnim.storage.plugin.PluginResource;

/**
 * XML格式配置的插件资源
 * 
 * @author OneCoder
 * @date 2014年4月20日 下午8:57:36
 * @website http://www.coderli.com
 */
public class XmlPluginResource implements PluginResource {

	private File configFile;

	public XmlPluginResource(File cFile) {
		this.configFile = cFile;
	}

	@Override
	public FileParser getFileParser() {
		return new XmlFileParser();
	}

	@Override
	public File getConfigFile() {
		return configFile;
	}

}
