package com.coderli.shurnim.storage.plugin.impl;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.plugin.FileParser;
import com.coderli.shurnim.storage.plugin.model.Plugin;
import com.coderli.shurnim.storage.plugin.model.Plugin.ApiParam;

/**
 * XML格式配置文件解析接口
 * 
 * @author OneCoder
 * @date 2014年4月20日 下午12:02:41
 * @website http://www.coderli.com
 */
public class XmlFileParser implements FileParser {

	private static final Logger logger = LoggerFactory
			.getLogger(XmlFileParser.class);
	private static final String ELE_NAME = "name";
	private static final String ELE_API = "api";
	private static final String ELE_CLASSNAME = "className";
	private static final String ELE_PARAMS = "params";
	private static final String ELE_PARAM = "param";
	private static final String ATTR_NAME = "name";
	private static final String ATTR_DISPLAY_NAME = "displayName";

	@SuppressWarnings("unchecked")
	@Override
	public Plugin parse(File configFile) {
		// TODO 利用xsd校验配置文件的正确性，这里假定配置文件格式正确的情况下进行开发。
		logger.debug("开始解析插件配置文件: {} 。", configFile.getName());
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(configFile);
			Plugin plugin = new Plugin();
			Element pluginEle = doc.getRootElement();
			// 解析插件的名字
			Element nameEle = pluginEle.element(ELE_NAME);
			String pluginName = nameEle.getTextTrim();
			plugin.setName(pluginName);
			logger.debug("插件的名字为: {}。", pluginName);
			// 解析插件API
			Element apiEle = pluginEle.element(ELE_API);
			Element classEle = apiEle.element(ELE_CLASSNAME);
			String className = classEle.getTextTrim();
			if (className == null) {
				logger.warn("插件: {} 没有配置接口实现类。略过。", pluginName);
				return null;
			}
			plugin.setApiClass(className);
			logger.debug("插件的接口实现类为: {}。", className);
			Element paramsEle = apiEle.element(ELE_PARAMS);
			List<Element> paramEles = paramsEle.elements(ELE_PARAM);
			if (paramEles == null || paramEles.size() == 0) {
				logger.debug("没有可用的参数列表。");
				return plugin;
			}
			for (Element paramEle : paramEles) {
				String paramName = paramEle.attributeValue(ATTR_NAME);
				String paramDisplayName = paramEle
						.attributeValue(ATTR_DISPLAY_NAME);
				ApiParam param = plugin.new ApiParam();
				param.setParamName(paramName);
				param.setDisplayName(paramDisplayName);
				plugin.addParam(param);
				logger.debug("解析到参数: {}。 显示名: {}", paramName, paramDisplayName);
			}
			return plugin;
		} catch (DocumentException e) {
			logger.warn("读取插件配置文件: {} 出错。", configFile.getName(), e);
			logger.warn("异常信息：", e);
			return null;
		}
	}
}
