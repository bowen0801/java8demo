/**
 * 
 */
package com.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	
	/**
	 * 发送HTTP请求
	 *  @author zhumingzhou
	 *  @date 2014年12月22日
	 *	@param url 请求路径
	 *	@param pairs 参数键值对列表
	 *	@return
	 *	@throws IOException
	 */
	public static String sendHttpRequest(String url, Map<String, Object> nameValueMap) throws IOException {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams()
		.setConnectionTimeout(3000);
		client.getHttpConnectionManager().getParams().setSoTimeout(3000);
		List<NameValuePair> pairsList = new ArrayList<NameValuePair>();
		if (nameValueMap != null) {
			for (Map.Entry<String, Object> entity: nameValueMap.entrySet()) {
				NameValuePair pair = new NameValuePair(entity.getKey(), entity.getValue().toString());
				pairsList.add(pair);
			}
		}
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		PostMethod postMethod = new PostMethod(url);
		postMethod.setQueryString(pairsList.toArray(new NameValuePair[pairsList.size()]));
		client.executeMethod(postMethod);
		try {
			String result = postMethod.getResponseBodyAsString();
			return result;
		}
		finally {
			postMethod.releaseConnection();
		}
	}
}
