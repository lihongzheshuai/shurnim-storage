package com.coderli.shurnim.test.shurnimstorage;

import java.io.File;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coderli.shurnim.storage.DefaultShurnimStorageImpl;
import com.coderli.shurnim.storage.ShurnimStorage;
import com.coderli.shurnim.storage.plugin.model.Resource;
import com.coderli.shurnim.storage.plugin.model.Resource.Type;

/**
 * 全局接口测试类<br>
 * 时间有限，目前仅作整体接口测试。细粒度的单元测试，随开发补充。
 * 
 * @author OneCoder
 * @date 2014年5月19日 下午10:50:27
 * @website http://www.coderli.com
 */
public class ShurnimStorageTest {

	private static ShurnimStorage shurnim;

	@BeforeClass
	public static void init() {
		shurnim = new DefaultShurnimStorageImpl(
				System.getProperty("user.dir") + File.separator + "plugins");
	}

	/**
	 * 测试同步文件
	 */
	@Test
	public void testSyncResource() {
		Resource syncResource = new Resource();
		syncResource.setPath("/api");
		syncResource.setName("api.html");
		syncResource.setType(Type.FILE);
		try {
			Assert.assertTrue(shurnim.syncResource("upyun", "qiniu",
					syncResource));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试同步文件夹
	 */
	@Test
	public void testSyncDir() {
		String dir = "backup";
		try {
			shurnim.syncDirectory("local", "qiniu", dir, true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("同步文件夹异常。");
		}
	}
}
