package com.shaun.blog.util;

import com.shaun.blog.frame.MessageStatus;
import com.shaun.blog.frame.ServiceRuntimeException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-4-25 Time: 上午1:41 To
 * change this template use File | Settings | File Templates.
 */
public class HttpProtocolUtils {

	private final static Logger logger = LoggerFactory
			.getLogger(HttpProtocolUtils.class);


	/**
	 * POST方法请求 参数为键值对
	 *
	 * @param map
	 * @param httpUrl
	 */
	public static String postMethod(Map<String, String> map, String httpUrl) {
		String result = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(httpUrl);
		postMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		postMethod.setRequestHeader("accept", "application/json");
		// postMethod.setRequestHeader("accept", "application/xml");
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry entry : entrySet) {
			NameValuePair nameValuePair = new NameValuePair(
					(String) entry.getKey(), (String) entry.getValue());
			logger.debug("##### post method param {} -- {} #######",
					entry.getKey(), entry.getValue());
			list.add(nameValuePair);
		}
		postMethod.setRequestBody(list.toArray(new NameValuePair[0]));
		try {
			int httpStatus = client.executeMethod(postMethod);
			if (httpStatus == HttpStatus.SC_OK) {
				byte[] bytes = postMethod.getResponseBody();
				result = new String(bytes, "UTF-8");
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}

	/**
	 * POST方法请求 参数为字符串
	 *
	 * @param queryString
	 * @param httpUrl
	 */
	public static String postMethod(String queryString, String httpUrl) {
		String result = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(httpUrl);
		postMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		postMethod.setRequestHeader("accept", "application/json");
		postMethod.setRequestBody(queryString);
		try {
			int httpStatus = client.executeMethod(postMethod);
			if (httpStatus == HttpStatus.SC_OK) {
				byte[] bytes = postMethod.getResponseBody();
				result = new String(bytes, "UTF-8");
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}

	public static HttpResponse postExecute(Map<String, String> params,
			String url) throws Exception {
		org.apache.http.client.HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("content-type", "application/json");
		String queryStr = HttpProtocolUtils.getQueryString(params);
		try {
			ByteArrayEntity entity = new ByteArrayEntity(
					queryStr.getBytes("UTF-8"));
			post.setEntity(entity);
			return client.execute(post);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ServiceRuntimeException(
					MessageStatus
							.getStatusText(MessageStatus.HTTP_ERROR_STATUS),
					MessageStatus.HTTP_ERROR_STATUS);
		}
	}

	/**
	 * 模拟表单上传文件和数据
	 *
	 * @param params
	 * @param url
	 * @param fileurl
	 * @return
	 * @throws Exception
	 */
	public static int postMultipartMethod(Map<String, String> params,
			String url, String fileurl) throws Exception {
		MultipartPostMethod filePost = new MultipartPostMethod(url);
		if (!"".equals(fileurl)) {
			FilePart file = new FilePart("file", new File(fileurl));
			file.setContentType("image/*");
			filePost.addPart(file);
		}
		Set<String> sets = params.keySet();
		for (String str : sets) {
			StringPart stringPart = new StringPart(str, params.get(str));
			stringPart.setCharSet("UTF-8");
			filePost.addPart(stringPart);
			// filePost.addParameter(str, params.get(str));
		}
		HttpClient client = new HttpClient();
		int result = 0;
		try {
			result = client.executeMethod(filePost);
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return result;

	}

	/**
	 * 模拟表单上传文件和数据
	 *
	 * @param url
	 * @param fileurl
	 * @return
	 * @throws Exception
	 */
	public static int postMultipartMethod(String url, String fileurl)
			throws Exception {
		MultipartPostMethod filePost = new MultipartPostMethod(url);
		if (!"".equals(fileurl)) {
			FilePart file = new FilePart("file", new File(fileurl));
			file.setContentType("image/*");
			filePost.addPart(file);
		}
		HttpClient client = new HttpClient();
		int result = 0;
		try {
			result = client.executeMethod(filePost);
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return result;

	}

	public static String getQueryString(Map<String, String> parameterMap)
			throws Exception {
		/*
		 * Set<String> keys = parameterMap.keySet(); StringBuilder sb = new
		 * StringBuilder(); for (String key : keys) {
		 * sb.append(key).append("=").append(parameterMap.get(key)).append("&");
		 * } String params = sb.toString(); params = params.substring(0,
		 * params.length() - 1);
		 */
		return "\"" + getPostQueryString(parameterMap) + "\""; // 特殊处理
	}

	public static String getPostQueryString(Map<String, String> parameterMap)
			throws Exception {
		Set<String> keys = parameterMap.keySet();
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			sb.append(key).append("=").append(parameterMap.get(key))
					.append("&");
		}
		String params = sb.toString();
		params = params.substring(0, params.length() - 1);
		return params; // 特殊处理
	}

	/**
	 * 测试GET方法请求
	 */
	public static String getMethod(String url) {
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		String result = null;
		try {
			int httpStatus = client.executeMethod(getMethod);
			logger.info("HttpStatus code{}", httpStatus);
			if (httpStatus != HttpStatus.SC_OK) {
				logger.info("地址{}返回错误。", getMethod.getURI());
				System.out.println("get请求错误！");
			} else {
				byte[] bytes = getMethod.getResponseBody();
				result = new String(bytes, "utf-8");
				// logger.info("####" + result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return result;
	}

	/**
	 * 抓取测试
	 * 
	 * @param url
	 * @return
	 */
	public static String getMethodCatch(String url) {
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		String result = null;
		getMethod.setRequestHeader("referer", "http://www.dianping.com");
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		getMethod
				.getParams()
				.setParameter(
						HttpMethodParams.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:7.0.1) Gecko/20100101 Firefox/7.0.1");
		try {
			int httpStatus = client.executeMethod(getMethod);
			logger.info("HttpStatus code{}", httpStatus);
			if (httpStatus != HttpStatus.SC_OK) {
				logger.info("地址{}返回错误。", getMethod.getURI());
				System.out.println("get请求错误！");
			} else {
				byte[] bytes = getMethod.getResponseBody();
				result = new String(bytes, "utf-8");
				// logger.info("####" + result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return result;
	}

	public static String getSubContent(String url) {
		String content = getMethodCatch(url);
		return findKey(content);
	}

	public static String findKey(String txt) {
		// name
		Pattern pattern = Pattern
				.compile("<span class=\"name\" title=\"(.+?)\">");
		Matcher matcher = pattern.matcher(txt);
		StringBuilder sb = new StringBuilder();
		if (matcher.find()) {
			String s = matcher.group(1);
			// logger.info("findKey={}",s);
			sb.append(s);
		}
		// 区
		pattern = Pattern
				.compile("<span itemprop=\"locality region\">(.+?)</span>");
		matcher = pattern.matcher(txt);
		if (matcher.find()) {
			String s = matcher.group(1);
			// logger.info("findKey={}",s);
			sb.append(",").append(s);
		}
		// 地址
		pattern = Pattern
				.compile("<span class=\"item\" itemprop=\"street-address\">((.|\n)+?)</span>");
		matcher = pattern.matcher(txt);
		if (matcher.find()) {
			String s = matcher.group(1);
			s = s.replaceAll("\\s", "");
			// logger.info("findKey={}",s);
			sb.append(",").append(s);
		}
		// tel
		pattern = Pattern
				.compile("<span\\s*itemprop=\"tel\">((.|\n)+?)</span>");
		matcher = pattern.matcher(txt);
		sb.append(", ");
		while (matcher.find()) {
			String s = matcher.group(1);
			s = s.replaceAll("\\n", "");
			// logger.info("findKey={}",s);
			sb.append(" ").append(s);
		}
		return sb.toString();
	}

	public static void getContent(String url) {
		String content = getMethod(url);
		Pattern pattern = Pattern
				.compile("target=\"_blank\" href=\"/shop/(.+?)\"  >");
		Matcher matcher = pattern.matcher(content);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			String s = matcher.group(1);
			logger.info("##########{}#######", s);
			System.out.println(getSubContent("http://www.dianping.com/shop/"
					+ s));
		}
	}

	public static void main(String[] args) throws Exception {
		// HttpClientTest httpClientTest = new HttpClientTest();
		// httpClientTest.getMethod();
		/*
		 * HttpProtocolHandler httpProtocolHandler=new HttpProtocolHandler();
		 * Map<String, String> map = new TreeMap<String, String>();
		 * map.put("ApiKey", "ds"); String
		 * sign=HttpParamUtil.buildSecretSign(map); map.put("Sign", sign);
		 * httpProtocolHandler.postMethod(map);
		 */

		/*
		 * 发短信 String
		 * url="http://pos.polejeter.com/UMSPos/CplusConsumptionService/SmsSend"
		 * ; Map<String,String> map=new HashMap<String,String>();
		 * map.put("phone","18610461028"); map.put("message","test");
		 * map.put("sender","test"); map.put("smstype","测试");
		 * System.out.println(HttpProtocolUtils.postExecute(map,
		 * url).getStatusLine());
		 */
		/*
		 * HttpProtocolUtils httpProtocolHandler = new HttpProtocolUtils();
		 * Map<String, String> map = new TreeMap<String, String>();
		 * map.put("wxname", "oV5V_jsq69MZyAfs_zSW01e9ee7Q"); map.put("phone",
		 * "18071025533");
		 * System.out.println(httpProtocolHandler.postMethod(map,
		 * "http://api.polejet.cn/jlwx/wx/vc"));
		 */
		// getContent("http://www.dianping.com/search/category/2/45/g150");
		// for (int i = 15;i <= 16;i++){
		// getContent("http://www.dianping.com/search/category/2/45/g150p"+i);
		// }
		//
		// System.out.println(getSubContent("http://www.dianping.com/shop/2120716"));
		//
		// System.out.println(getMethod("http://www.dianping.com"));
		System.out
				.println(getMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxea8a79c703508d58&secret=4368ecaf9a4f4721167ff1637209fe79"));

	}
}
