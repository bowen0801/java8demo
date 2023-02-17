/**
 * 
 */
package com.util;

import com.google.common.base.Charsets;
import com.vo.HeaderVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhumingzhou
 * @date 2015年1月27日
 */
public class HttpUtil2 {

	
	private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";
	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_JSON = "text/json";
	private static final String APPLICATION_FORM = "application/x-www-form-urlencoded";
	private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";
 
    static {
    	RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build();
        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();  
        //MaxTotal 值不应该太大  
        pcm.setMaxTotal(200);
        //DefaultMaxPerRoute 是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。  
        //设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。  
        pcm.setDefaultMaxPerRoute(pcm.getMaxTotal());//（目前只有一个路由，因此让他等于最大值）  
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				return 180 * 1000;
			}
			
		};
        httpClient = HttpClientBuilder.create().setConnectionManager(pcm).setDefaultRequestConfig(config).setKeepAliveStrategy(myStrategy).build();
    }
    
    public static String doDelete(String url, Map<String, String> params){
        return doDelete(url, params,CHARSET, false);
    }
    public static String doPut(String url, Map<String,String> params){
    	return doPut(url, params,CHARSET, false);
    }
    public static String doGet(String url, Map<String, String> params){
        return doGet(url, params,CHARSET, false);
    }
    public static String doGet(String url, Map<String, String> params,List<HeaderVo> headers){
		return doGet(url, params,CHARSET, false,headers);
    }
    public static String doPost(String url, Map<String, String> params){
        return doPost(url, params,CHARSET, false);
    }
    
    public static String doGetObj(String url, Map<String, Object> params){
    	return doGetObj(url, params,CHARSET, false);
    }
    public static String doPostObj(String url, Map<String, Object> params){
    	return doPostObj(url, params,CHARSET, false);
    }
    public static String doPostObj(String url, Map<String, Object> params,int timeout){
    	return doPostObj(url, params,CHARSET, false,timeout);
    }
    public static String doPostJSON(String url,String josnStr){
    	return doPostJSON(url, josnStr, false,null);
    }
    public static String doPostJsonWithHeader(String url,String josnStr,Map<String, String> headers){
    	return doPostJSON(url, josnStr, false,headers);
    }
    
    public static String doGet(String url, Map<String, String> params, boolean isExceptionThrow){
    	return doGet(url, params,CHARSET, isExceptionThrow);
    }
    public static String doPost(String url, Map<String, String> params, boolean isExceptionThrow){
    	return doPost(url, params,CHARSET, isExceptionThrow);
    }
    
    public static String doGetObj(String url, Map<String, Object> params, boolean isExceptionThrow){
    	return doGetObj(url, params,CHARSET, isExceptionThrow);
    }
    public static String doPostObj(String url, Map<String, Object> params, boolean isExceptionThrow){
    	return doPostObj(url, params,CHARSET, isExceptionThrow);
    }
    
    /**
     * HTTP Get 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @return    页面内容
     */
    public static String doGet(String url,Map<String,String> params,String charset, boolean isExceptionThrow){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpGet.abort();
	                System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
            }
            finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
        	if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
        }
        return null;
    }
    
    /**
     * HTTP Get 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @param headers header内容
     * @return    页面内容
     */
    public static String doGet(String url,Map<String,String> params,String charset, boolean isExceptionThrow,List<HeaderVo> headers){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        try {
        	String method = params.get("method");
			System.err.println("msg method【"+ method +"】URL：" + url);
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
			if (headers != null && headers.size() > 0) {
            	for(HeaderVo headerVo : headers){
            		httpGet.setHeader(headerVo.getKey(),headerVo.getValue());
            	}
            }
			
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpGet.abort();
					System.err.println("HTTP连接异常  msg,error status code :" + statusCode);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
				System.out.println(method+"："+entity);

				System.out.println("【"+method+"】 end http status :" + statusCode);
	            return result;
            }
            finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
        	if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
        }
        return null;
    }
    
    
    public static String doGetJson(String url,Map<String,String> params, List<HeaderVo> headerList){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

			if (headerList != null && headerList.size() > 0) {
				headerList.forEach(h -> {
					httpGet.addHeader(h.getKey(), h.getValue());
				});
			}
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpGet.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
            }
            finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
        }
        return null;
    }
    
    /**
     * HTTP Delete 删除内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @return    页面内容
     */
    public static String doDelete(String url,Map<String,String> params,String charset, boolean isExceptionThrow){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpDelete httpDelete = new HttpDelete(url);
            CloseableHttpResponse response = httpClient.execute(httpDelete);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	            	httpDelete.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
            }
            finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
        	if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
        }
        return null;
    }
     
    /**
     * HTTP Post 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @return    页面内容
     */
    public static String doPost(String url,Map<String,String> params,String charset, boolean isExceptionThrow){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpPost.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode+",url="+url);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
            } finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage()+"url="+url);
        	if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
        }
        return null;
    }
    
    /**
     * HTTP Post 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @return    页面内容
     */
    public static String doPostJSON(String url,String josnStr, boolean isExceptionThrow) {
    	return doPostJSON(url,josnStr,isExceptionThrow,null);
    }
    public static String doPostJSON(String url,String josnStr, boolean isExceptionThrow,Map<String,String> headers){
        if(StringUtils.isEmpty(url) || StringUtils.isEmpty(josnStr)){
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            if(headers != null && !headers.isEmpty()){
                for(Map.Entry<String,String> entry : headers.entrySet()){
                	httpPost.addHeader(entry.getKey(),entry.getValue());
                }
            }
            StringEntity se = new StringEntity(josnStr, "utf-8");
            se.setContentType(CONTENT_TYPE_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpPost.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode+",url="+url);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
            } finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
        	if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
        }
        return null;
    }
    
    /***
     * POST提交文件
     * @param surl
     * @param params
     * @param imgName
     * @param bytes
     * @param charset
     * @param ip
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
	public static String postFile(String url,Map<String,Object> params,String fileName, boolean isExceptionThrow){
		
		HttpPost post = new HttpPost(url);
		
		// 
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		
		Set<String> pNames = params.keySet();
		
		//构建参数信息
		for (String key : pNames) {
			
			Object obj = params.get(key);
			if (obj instanceof String) {//普通文本
				builder.addTextBody(key, obj.toString(), ContentType.TEXT_PLAIN);
			}
			
			if (obj instanceof Integer) {//普通文本
				builder.addTextBody(key, obj.toString(), ContentType.TEXT_PLAIN);
			}
			
			if (obj instanceof Long) {//普通文本
				builder.addTextBody(key, obj.toString(), ContentType.TEXT_PLAIN);
			}
			
			if (obj instanceof byte[]) {//byte数组的处理
				builder.addBinaryBody(key, (byte[])obj, ContentType.DEFAULT_BINARY, fileName);
			}
		}
		
		
		// 
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		try {
			CloseableHttpResponse response = httpClient.execute(post);
			
			try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	            	post.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
	            }
	            HttpEntity entity2 = response.getEntity();
	            String result = null;
	            if (entity2 != null){
	                result = EntityUtils.toString(entity2, "utf-8");
	            }
	            EntityUtils.consume(entity2);
	            return result;
	        } finally {
	        	response.close();
	        }
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			if(isExceptionThrow) {
        		throw new RuntimeException( e.getMessage(), e);
        	}
		} catch (IOException e) {
			e.printStackTrace();
			if(isExceptionThrow) {
        		throw new RuntimeException( e.getMessage(), e);
        	}
		}
		
		
		
		return null;
          
    }
	
	/***
     * POST提交文件
     * @param surl
     * @param params
     * @param imgName
     * @param bytes
     * @param charset
     * @param ip
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
	public static String doPostFile(String url,byte[] content, boolean isExceptionThrow){
		HttpPost post = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("file", content,ContentType.create("image/png"),"1.png");
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		try {
			CloseableHttpResponse response = httpClient.execute(post);			
			try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	            	post.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
	            }
	            HttpEntity entity2 = response.getEntity();
	            String result = null;
	            if (entity2 != null){
	                result = EntityUtils.toString(entity2, "utf-8");
	            }
	            EntityUtils.consume(entity2);
	            return result;
	        } finally {
	        	response.close();
	        }
			
		} catch (ClientProtocolException e) {
			System.err.println("上传文件异常: ");
			if(isExceptionThrow) {
        		throw new RuntimeException( e.getMessage(), e);
        	}
		} catch (IOException e) {
			System.err.println("上传文件异常: ");
			if(isExceptionThrow) {
        		throw new RuntimeException( e.getMessage(), e);
        	}
		}
		return null;
    }
	/**
	 * HTTP Get 获取内容
	 * @param url  请求的url地址 ?之前的地址
	 * @param params 请求的参数
	 * @param charset    编码格式
	 * @return    页面内容
	 */
	public static String doGetObj(String url,Map<String,Object> params,String charset, boolean isExceptionThrow){
		if(StringUtils.isEmpty(url)){
			return null;
		}
		try {
			if(params != null && !params.isEmpty()){
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for(Map.Entry<String,Object> entry : params.entrySet()){
					Object value = entry.getValue();
					if(value != null){
						pairs.add(new BasicNameValuePair(entry.getKey(),String.valueOf(value)));
					}
				}
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			System.out.println(url);
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpGet.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null){
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
				return result;
			}
			finally {
				response.close();
			}
		} catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
			if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
		}
		return null;
	}
	
	/**
	 * HTTP Post 获取内容
	 * @param url  请求的url地址 ?之前的地址
	 * @param params 请求的参数
	 * @param charset    编码格式
	 * @return    页面内容
	 */
	public static String doPostObj(String url,Map<String,Object> params,String charset, boolean isExceptionThrow){
		if(StringUtils.isEmpty(url)){
			return null;
		}
		try {
			List<NameValuePair> pairs = null;
			if(params != null && !params.isEmpty()){
				pairs = new ArrayList<NameValuePair>(params.size());
				for(Map.Entry<String,Object> entry : params.entrySet()){
					Object value = entry.getValue();
					if(value != null){
						pairs.add(new BasicNameValuePair(entry.getKey(),String.valueOf(value)));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if(pairs != null && pairs.size() > 0){
				httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpPost.abort();
					//处理中宝系统返回500错误，不再打印error改为打印info
					if(url.equals("http://101.68.83.91:9801/formal/erp/order/rest/")){
						System.out.println("HTTP连接异常    HttpClient,error status code :" + statusCode+",url="+url);
					}else{
						System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode+",url="+url);
					}
					
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null){
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
				return result;
			} finally {
				response.close();
			}
		} catch (Exception e) {
			if(url.equals("http://172.16.2.125/action")){//小票短链请求异常不再打印error,duyongqing
				System.out.println("URL="+url+"HTTP连接异常  " +e.getMessage());
			}else{
				System.err.println("URL="+url+"HTTP连接异常  " +e.getMessage());
			}
			if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
		}
		return null;
	}
	/**
	 * HTTP Post 获取内容
	 * @param url  请求的url地址 ?之前的地址
	 * @param params 请求的参数
	 * @param charset    编码格式
	 * @return    页面内容
	 */
	public static String doPostObj(String url,Map<String,Object> params,String charset, boolean isExceptionThrow,int timeout){
		if(StringUtils.isEmpty(url)){
			return null;
		}
		try {
			List<NameValuePair> pairs = null;
			if(params != null && !params.isEmpty()){
				pairs = new ArrayList<NameValuePair>(params.size());
				for(Map.Entry<String,Object> entry : params.entrySet()){
					Object value = entry.getValue();
					if(value != null){
						pairs.add(new BasicNameValuePair(entry.getKey(),String.valueOf(value)));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if(pairs != null && pairs.size() > 0){
				httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
			}
			RequestConfig requestConfig = RequestConfig.custom()    
			        .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)    
			        .setSocketTimeout(timeout).build();    
			httpPost.setConfig(requestConfig); 
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpPost.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null){
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
				return result;
			} finally {
				response.close();
			}
		} catch (Exception e) {
			System.err.println("HTTP连接异常:" + url + "\n" + e.getMessage());
			if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
		}
		return null;
	}
	public static String doPut(String url,Map<String,String> params,String charset, boolean isExceptionThrow){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPut httpPut = new HttpPut(url);
            httpPut.addHeader("Content-Type", APPLICATION_FORM);
            if(pairs != null && pairs.size() > 0){
            	httpPut.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPut);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	            	httpPut.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
            } finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
        	if(isExceptionThrow) {
        		throw new RuntimeException("HTTP连接异常  " + e.getMessage(), e);
        	}
        }
        return null;
    }

	
	public static String doPost(String url, Map<String, String> apiMap, Map<String, String> headerMap) {
		try {
            List<NameValuePair> parameters = null;
            if(null != apiMap && !apiMap.isEmpty()){
            	parameters = new ArrayList<NameValuePair>(apiMap.size());
                for(Map.Entry<String, String> entry : apiMap.entrySet()) {
                    if(null != entry.getValue()){
                    	parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_FORM);
            if(null != headerMap && !headerMap.isEmpty()) {
            	for(Map.Entry<String,String> header : headerMap.entrySet()){
            		httpPost.setHeader(header.getKey(), header.getValue());
            	}
            }
            if(null != parameters && !parameters.isEmpty()){
                httpPost.setEntity(new UrlEncodedFormEntity(parameters, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpPost.abort();
					System.err.println("http请求失败，地址：{}，状态码：{}");
	            }
	            HttpEntity httpEntity = response.getEntity();
	            String apiResult = null;
	            if (null != httpEntity){
	            	apiResult = EntityUtils.toString(httpEntity, CHARSET);
	            }
	            EntityUtils.consume(httpEntity);
	            return apiResult;
            } finally {
            	response.close();
            }
        } catch (Exception ex) {
			System.err.println("http连接异常");
        }
        return null;
    }

	/**
	 *
	 * @param url
	 * @param josnStr
	 * @return
	 */
	public static String postJson(String url,String josnStr){
		if(StringUtils.isEmpty(url) || StringUtils.isEmpty(josnStr)){
			return null;
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
			httpPost.addHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
			StringEntity se = new StringEntity(josnStr, "utf-8");
			se.setContentType(CONTENT_TYPE_JSON);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpPost.abort();
					System.err.println("HTTP连接异常   HttpClient,error status code :" + statusCode+",url="+url);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null){
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
				return result;
			} finally {
				response.close();
			}
		} catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
		}
		return null;
	}
	/**
	 * 使用ContentType application/x-www-form-urlencoded 发送post请求
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * */
	public static String post(String url, Map<String, Object> params)
			throws RuntimeException {
		StringBuffer sb = new StringBuffer();
		String strParam = "";
		if (params != null) {
			for (String key : params.keySet()) {
				sb.append(String.format("%s=%s&", key, params.get(key)));
			}
			strParam = sb.substring(0, sb.length() - 1);
		}
		String response = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse res = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			ContentType ctype = ContentType.create(
					ContentType.APPLICATION_FORM_URLENCODED.getMimeType(),
					Charsets.UTF_8.name());
			StringEntity fsf = new StringEntity(strParam, ctype);
			httpPost.setEntity(fsf);

			System.out.println(String.format(
					"Request send params [%s], xxx-form-urlencord", strParam));
			res = httpclient.execute(httpPost);
			System.out.println("doPost[" + url + "] status:" + res);
			HttpEntity entity = res.getEntity();
			response = EntityUtils.toString(entity, Charsets.UTF_8.name());
		} catch (Exception e) {
			String info = String.format("请求URL=%s失败", url);
			throw new RuntimeException(info);
		} finally {
			try {
				res.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return response;
	}

	/**
	 * 提交JSON数据
	 *
	 * @param url
	 * @param json
	 * @param headerList
	 * @return
	 */
	public static String postJson(String url, String json, List<HeaderVo> headerList) {
		if (StringUtils.isEmpty(url) || StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

			if (headerList != null && headerList.size() > 0) {
				headerList.forEach(h -> {
					httpPost.addHeader(h.getKey(), h.getValue());
				});
			}

			StringEntity se = new StringEntity(json, "utf-8");
			se.setContentType(CONTENT_TYPE_JSON);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpPost.abort();
					System.err.println("HTTP连接异常   HttpClient,error status code :" + statusCode + ",url=" + url);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null) {
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
				return result;
			} finally {
				response.close();
			}
		} catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
		}
		return null;
	}

	/**
	 * @param url
	 * @param json
	 * @param headerList
	 * @param timeout
	 * @return
	 */
	public static String postJson(String url, String json, List<HeaderVo> headerList, int timeout) {
		if (StringUtils.isEmpty(url) || StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

			if (headerList != null && headerList.size() > 0) {
				headerList.forEach(h -> {
					httpPost.addHeader(h.getKey(), h.getValue());
				});
			}

			StringEntity se = new StringEntity(json, "utf-8");
			se.setContentType(CONTENT_TYPE_JSON);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
			httpPost.setEntity(se);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
					.setSocketTimeout(timeout).build();
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpPost.abort();
					System.err.println("HTTP连接异常   HttpClient,error status code :" + statusCode + ",url=" + url);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null) {
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
				return result;
			} finally {
				response.close();
			}
		} catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
		}
		return null;
	}
	
	public static String doPostToCds(String url, String jsonStr, Map<String, String> headerMap) {
		try {
            HttpPost httpPost = new HttpPost(url);
            
            StringEntity se = new StringEntity(jsonStr, "utf-8");
            se.setContentType(CONTENT_TYPE_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);
            
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            if(null != headerMap && !headerMap.isEmpty()) {
            	for(Map.Entry<String,String> header : headerMap.entrySet()){
            		httpPost.setHeader(header.getKey(), header.getValue());
            	}
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpPost.abort();
					System.err.println("http请求失败，地址：{}，状态码：{}");
	            }
	            HttpEntity httpEntity = response.getEntity();
	            String apiResult = null;
	            if (null != httpEntity){
	            	apiResult = EntityUtils.toString(httpEntity, CHARSET);
	            }
	            EntityUtils.consume(httpEntity);
	            return apiResult;
            } finally {
            	response.close();
            }
        } catch (Exception ex) {
			System.err.println("http连接异常");
        }
        return null;
    }
	
	public static String doPostForApi(String url,String josnStr){
        if(StringUtils.isEmpty(url) || StringUtils.isEmpty(josnStr)){
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity se = new StringEntity(josnStr, "utf-8");
            se.setContentType(CONTENT_TYPE_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpPost.abort();
					System.err.println("HTTP连接异常    HttpClient,error status code :" + statusCode+",url="+url);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
            } finally {
            	response.close();
            }
        } catch (Exception e) {
			System.err.println("HTTP连接异常  " + e.getMessage());
        }
        return null;
    }
	
}
