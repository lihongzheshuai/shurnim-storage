package com.coderli.shurnim.storage.upyun.plugin;

import java.io.File;
import java.util.List;

import com.coderli.shurnim.storage.plugin.AbstractPluginAPI;
import com.coderli.shurnim.storage.plugin.model.Resource;
import com.coderli.shurnim.storage.plugin.model.Resource.Type;
import com.coderli.shurnim.storage.upyun.api.UpYun;

public class UpYunPlugin extends AbstractPluginAPI {

	private UpYun upyun;
	private String username;
	private String password;
	private String bucketName;

	public UpYun getUpyun() {
		return upyun;
	}

	public void setUpyun(UpYun upyun) {
		this.upyun = upyun;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coderli.shurnim.storage.plugin.PluginAPI#getChildResources(java.lang
	 * .String)
	 */
	@Override
	public List<Resource> getChildResources(String parentPath) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coderli.shurnim.storage.plugin.PluginAPI#downloadResource(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	@Override
	public Resource downloadResource(String parentPath, String name,
			String storePath) {
		File storeFile = new File(storePath);
		// if (!storeFile.exists()) {
		// try {
		// storeFile.createNewFile();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		String filePath = getFullPath(parentPath, name);
		upyun.readDir("/api");
		if (upyun.readFile(filePath, storeFile)) {
			Resource result = new Resource();
			result.setName(name);
			result.setPath(parentPath);
			result.setType(Type.FILE);
			result.setLocalFile(storeFile);
			return result;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coderli.shurnim.storage.plugin.PluginAPI#mkdir(java.lang.String,
	 * boolean)
	 */
	@Override
	public boolean mkdir(String path, boolean auto) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coderli.shurnim.storage.plugin.PluginAPI#uploadResource(java.lang
	 * .String, java.lang.String, java.io.File)
	 */
	@Override
	public boolean uploadResource(String parentPath, String name,
			File uploadFile) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coderli.shurnim.storage.plugin.AbstractPluginAPI#init()
	 */
	@Override
	public void init() {
		upyun = new UpYun(bucketName, username, password);
	}

}
