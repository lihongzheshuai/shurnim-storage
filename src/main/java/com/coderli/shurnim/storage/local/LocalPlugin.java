package com.coderli.shurnim.storage.local;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderli.shurnim.storage.plugin.AbstractPluginAPI;
import com.coderli.shurnim.storage.plugin.model.Resource;
import com.coderli.shurnim.storage.plugin.model.Resource.Type;
import com.google.common.io.Files;

/**
 * 操作本地文件插件，用于本地文件和云存储直接的交互
 * 
 * @author OneCoder(http://www.coderli.com)
 * 
 */
public class LocalPlugin extends AbstractPluginAPI {

	private static final Logger logger = LoggerFactory
			.getLogger(LocalPlugin.class);

	@Override
	public List<Resource> getChildResources(String parentPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource downloadResource(String parentPath, String name,
			String storePath) {
		String fullPath;
		if (parentPath != null) {

			fullPath = getFullPath(parentPath, name);
		} else {
			fullPath = name;
		}
		String absolutePath = System.getProperty("user.dir") + File.separator
				+ fullPath;
		File file = new File(absolutePath);
		File to = new File(storePath);
		try {
//			if (!to.exists()) {
//				to.createNewFile();
//			}
			Files.copy(file, to);
		} catch (IOException e) {
			logger.error("复制文件失败。 {}", name);
			logger.error("异常：", e);
			return null;
		}
		Resource resource = new Resource();
		resource.setName(name);
		resource.setPath(parentPath);
		resource.setType(Type.FILE);
		resource.setSize(file.getTotalSpace());
		resource.setLocalFile(file);
		return resource;
	}

	@Override
	public boolean mkdir(String path, boolean auto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean uploadResource(String parentPath, String name,
			File uploadFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
