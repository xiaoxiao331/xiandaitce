package tce.com;

import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;

public class SMSSender {
    public static void sendSMS(IFType type, TextSMS textSMS, Header[] headers)
            throws Throwable {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String json = JSON.toJSONString(textSMS);
            HttpPost httppost = new HttpPost(type.getUri());
            HttpEntity req = new StringEntity(json, Charset
                    .forName("UTF-8"));

            httppost.setHeaders(headers);
            httppost.setEntity(req);

            System.out.println("Executing request: "
                    + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
