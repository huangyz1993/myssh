package com.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class Url {
	String result = "";// 访问返回结果
	BufferedReader read = null;// 读取访问结果

	public String getContent(String url) {
		try {
			// 创建url
			URL realurl = new URL(url);
			// 打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
			connection.connect();
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段，获取到cookies等
			for (String key : map.keySet()) {
				// System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			read = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;// 循环读取
			while ((line = read.readLine()) != null) {
				// System.out.println(line);
				if ("".equals(line)) {
					result += line + "<br/>";
				} else {
					result += line+"<br/>";
				}
			}
			// System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * type=1图片 2大图片
	 * 
	 * @param url
	 * @param username
	 * @param type
	 * @return
	 * @throws MalformedURLException
	 */
	public String downloadNet(String url, String username, String type)
			throws MalformedURLException {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;
		String rootPath = "E:/apache-tomcat-7.0.70/webapps";
		String suffix = "/wuzhi/small/" + username + "." + new Date().getTime()
				+ ".jpg";
		String relPic = rootPath + suffix;
		if ("2".equals(type)) {
			suffix = "/wuzhi/big/" + username + "." + new Date().getTime()
					+ ".jpg";
			relPic = rootPath + suffix;
		}
		URL urlPic = new URL(url);

		try {
			URLConnection conn = urlPic.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream(relPic);

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				// System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return suffix;
	}

	public static void main(String args[]) throws InterruptedException {
		new Url().getContent("https://wuzhi.me/u/260749");

	}
}
