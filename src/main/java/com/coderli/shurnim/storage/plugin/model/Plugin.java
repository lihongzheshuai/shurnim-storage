package com.coderli.shurnim.storage.plugin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件模型
 * 
 * @author OneCoder
 * @date 2014年4月18日 下午10:29:31
 * @website http://www.coderli.com
 */
public class Plugin {

	// 插件名称，用于界面显示
	private String name;
	// 云存储接口实现类
	private String apiClass;
	// 接口初始化参数列表
	private List<ApiParam> params = new ArrayList<ApiParam>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiClass() {
		return apiClass;
	}

	public void setApiClass(String apiClass) {
		this.apiClass = apiClass;
	}

	public List<ApiParam> getParams() {
		return params;
	}

	public void addParam(ApiParam param) {
		this.params.add(param);
	}

	public void setParams(List<ApiParam> params) {
		this.params = params;
	}

	public class ApiParam {

		private String paramName;
		private String paramValue;

		public String getParamName() {
			return paramName;
		}

		public void setParamName(String paramName) {
			this.paramName = paramName;
		}

		public String getParamValue() {
			return paramValue;
		}

		public void setParamValue(String paramValue) {
			this.paramValue = paramValue;
		}

	}
}
