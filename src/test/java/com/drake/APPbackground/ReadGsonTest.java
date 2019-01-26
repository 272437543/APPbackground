package com.drake.APPbackground;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.drake.APPbackground.entity.CommentEntity;
import com.drake.APPbackground.utils.JsonUtil;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadGsonTest {

	@Test
	public void problem() {
		int[] nums = { 1, 2, 3, 4, 5 };
		System.out.println("ans: " + minSubArrayLen(11, nums));
	}

	public int minSubArrayLen(int s, int[] nums) {
		int low = 0;
		int high = 0;
		int sum = nums[0];
		int ret = 9999;

		while (high < nums.length && low < nums.length) {
			if (sum < s)
				sum += nums[++high];
			else {
				ret = Math.min(ret, high - low + 1);
				sum -= nums[low++];
			}

		}

		return ret == 9999 ? 0 : ret;
	}

	private String urlAddr = "http://192.168.31.253:8080/con/allcomment";

	@Test
	public void readTest() throws Exception {
		URL url = new URL(urlAddr);
		String urlsource = getURLSource(url);
		System.out.println(urlsource);
		List<CommentEntity> list = JsonUtil.jsonToList(urlsource,
				CommentEntity.class);
		for (CommentEntity comm : list) {
			System.out.println("INFO: " + comm);
		}
	}

	/** */
	/**
	 * 通过网站域名URL获取该网站的源码
	 * 
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	public String getURLSource(URL url) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream(); // 通过输入流获取html二进制数据
		byte[] data = readInputStream(inStream); // 把二进制数据转化为byte字节数据
		String htmlSource = new String(data);
		return htmlSource;
	}

	/** */
	/**
	 * 把二进制流转化为byte字节数组
	 * 
	 * @param instream
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] readInputStream(InputStream instream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1204];
		int len = 0;
		while ((len = instream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		instream.close();
		return outStream.toByteArray();
	}

}
