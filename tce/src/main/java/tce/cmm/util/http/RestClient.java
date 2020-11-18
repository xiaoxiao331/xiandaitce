package tce.cmm.util.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.ResponseEntity;

/**
 * <pre>
 * Rest Client 전송 담당 클래스
 * </pre>
 *
 * @ClassName   : RestClient.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 이명철
 * @since 2019. 1. 24.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 1. 24.     이명철     	최초 생성
 * </pre>
 */

public class RestClient {
    /** LOG */
    private Log log = LogFactory.getLog(getClass());
    
    /** Rest Client Template */
    private RestTemplate client = new RestTemplate();
    /** Header Info */
    private HttpHeaders headers = new HttpHeaders();
    /** Request Body Info */
    private Object body = null;
    /** URL Parameter Setting */
    private Map<String,String> params = new HashMap<String,String>();
    /** HTTP METHOD(POST,GET,DELETE,PUT) */
    private HttpMethod httpMethod;
    /** URL */
    private String url;
    
    /**
     * Create a new instance
     */
    public RestClient(){}
    
    /**
     * Mapping Values All Clear
     */
    public void clear(){
        headers.clear();
        params.clear();
    }
    
    /**
     * Create a new instance
     *
     * @param httpMethod HTTP METHOD(POST,GET,PUT,DELETE)
     * @param url
     */
    public RestClient(HttpMethod httpMethod,String url){
        this.httpMethod = httpMethod;
        this.url = url;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * Header Append
     * @param key 
     * @param value
     */
    public void addHeader(String key, String value){
        headers.add(key, value);
    }
    
    /**
     * Param Append(URL Parameter)
     * @param name
     * @param value
     */
    public void addParam(String name, String value){
        params.put(name, value);
    }
    
    /**
     * @param httpMethod the httpMethod to set
     */
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * 
     *
     * @param sendType
     * @return
     */
    public RestClientRes send() throws Exception{
        RestClientRes returnRes = new RestClientRes();
        
        //전송데이터 설정
        HttpEntity<Object> entity = null;
        ResponseEntity<String> responseEntity = null;

        log.debug("[API CALL URL ] : " + url);
        
        if(httpMethod.name().equals(HttpMethod.POST.name())){
            entity = new HttpEntity<Object>(body,headers);
            responseEntity = client.exchange(url, HttpMethod.POST, entity, String.class);
            returnRes.setStatusCode(responseEntity.getStatusCode());
            returnRes.setBody(responseEntity.getBody());
        } 
        else if(httpMethod.name().equals(HttpMethod.GET.name())){
            entity = new HttpEntity<Object>(body,headers);
            responseEntity = client.exchange(url, HttpMethod.GET, entity, String.class, params);
            returnRes.setStatusCode(responseEntity.getStatusCode());
            returnRes.setBody(responseEntity.getBody());
        }

        //Clear the Parameter and Header.
        this.clear();
        
        log.debug("[API CALL STATUS_CODE] : " + returnRes.getStatusCode());
        log.debug("[API CALL BODY] : " + returnRes.getBody());
        
        //200,204 : Api Call Success
        if(!returnRes.getStatusCode().equals("200") && !returnRes.getStatusCode().equals("204"))
            throw new Exception("API CALL FAIL");
        
        return returnRes;
    }

    /**
     * @return the body
     */
    public Object getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the headers
     */
    public HttpHeaders getHeaders() {
        return headers;
    }

    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return params;
    }
}
