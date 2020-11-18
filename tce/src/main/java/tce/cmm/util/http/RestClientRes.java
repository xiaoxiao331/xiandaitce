package tce.cmm.util.http;

import org.springframework.http.HttpStatus;

/**
 * <pre>
 * Response 정보를 담는 클래스
 * </pre>
 *
 * @ClassName   : RestClientRes.java
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

public class RestClientRes {
    /** Response Status Code */
    private String statusCode;
    
    /** Response Body Message */
    private String body;

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = String.valueOf(statusCode);
    }
    
    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(HttpStatus noContent) {
        this.statusCode = String.valueOf(noContent.value());
    }
    
    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}
