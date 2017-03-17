/**
 * birkhoff
 * Created on 2017年02月11日
 */
package util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class HtmlUtil{
    public static boolean isHttps(String url) {
        if (StringUtils.isBlank(url)) {
            throw new NullPointerException("url is blank");
        }
        return url.substring(0, 5).equals("https");
    }

    public static String getRandomUserAgent() {
        return UserAgents.getRandomUserAgent();
    }

    public static Document getDocument(String url) throws IOException {
        Connection connection=Jsoup.connect(url).userAgent(getRandomUserAgent()).timeout(200000);
        return connection.get();
    }

    public static HttpClient getHttpsClient() {
        HttpClient httpClient = null;
        SSLContext context;
        try {
            context = SSLContext.getInstance("SSL");
            context.init(null, new TrustManager[] {new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }}, new SecureRandom());

            HostnameVerifier verifier = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(context, verifier);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpClient;
    }

    private static HttpClient getHttpClientByUrl(String url) {
        if (isHttps(url)) {
            return getHttpsClient();
        } else {
            return HttpClients.createDefault();
        }
    }

    public static InputStream getInputStreamByUrl(String url, int timeout) throws ClientProtocolException, IOException {
        HttpClient httpClient = getHttpClientByUrl(url);
        HttpGet request = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();// 设置请求和传输超时时间
        request.setConfig(requestConfig);
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            return entity.getContent();
        }
        return null;
    }

    public static void downloadImgFromUrl(String url, String title, String dirPath, String fileName, int timeout)
            throws ClientProtocolException, IOException {
        InputStream in = getInputStreamByUrl(url, timeout);
        byte[] b = new byte[1024];
        int len = 0;
        File dir = new File(dirPath + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File f = new File(dirPath + "/" + fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        OutputStream out = new FileOutputStream(f);
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        out.close();
        System.out.println("Download " + title + " finished");
    }

    public static JSONObject jsonResponse(HttpRequestBase req, HttpClient httpClient) throws ClientProtocolException,
            IOException {
        HttpResponse response = httpClient.execute(req);
        String responseJsonStr = EntityUtils.toString(response.getEntity());
        return JSON.parseObject(responseJsonStr);
    }
}