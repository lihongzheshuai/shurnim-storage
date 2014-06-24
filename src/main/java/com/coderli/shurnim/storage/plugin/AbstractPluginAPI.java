package com.coderli.shurnim.storage.plugin;

import java.io.File;

public abstract class AbstractPluginAPI implements PluginAPI {

	public abstract void init();

	protected String getFullPath(String parentPath, String name) {
		if (!parentPath.endsWith(File.separator)) {
			parentPath = parentPath + File.separator;
		}
		return parentPath + name;
	}

}
