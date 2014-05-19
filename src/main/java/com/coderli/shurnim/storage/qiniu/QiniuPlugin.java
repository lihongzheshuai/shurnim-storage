package com.coderli.shurnim.storage.qiniu;

import java.io.File;
import java.util.List;

import org.json.JSONException;

import com.coderli.shurnim.storage.plugin.AbstractPluginAPI;
import com.coderli.shurnim.storage.plugin.model.Resource;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

/**
 * @author OneCoder
 * @date 2014年5月19日 下午10:28:59
 * @website http://www.coderli.com
 */
public class QiniuPlugin extends AbstractPluginAPI {

	private String access_key;
	private String secret_key;
	private String bucketName;
	private Mac mac;

	public String getAccess_key() {
		return access_key;
	}

	public void setAccess_key(String acccess_key) {
		this.access_key = acccess_key;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		PutPolicy putPolicy = new PutPolicy(bucketName);
		String uptoken;
		try {
			uptoken = putPolicy.token(mac);
			PutExtra extra = new PutExtra();
			String key = name;
			String localFile = uploadFile.getAbsolutePath();
			PutRet ret = IoApi.putFile(uptoken, key, localFile, extra);
			return true;
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coderli.shurnim.storage.plugin.AbstractPluginAPI#init()
	 */
	@Override
	public void init() {
		mac = new Mac(access_key, secret_key);
	}
}
