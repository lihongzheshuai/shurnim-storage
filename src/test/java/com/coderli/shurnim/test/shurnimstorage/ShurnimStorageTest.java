package com.coderli.shurnim.test.shurnimstorage;

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
				"/Users/apple/git/shurnim-storage-for-UPYUN/plugins");
	}

	@Test
	public void testSycnResource() {
		Resource syncResource = new Resource();
		syncResource.setPath("/api");
		syncResource.setName("api.html");
		syncResource.setType(Type.FILE);
		try {
			Assert.assertTrue(shurnim.sycnResource("upyun", "qiniu",
					syncResource));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
