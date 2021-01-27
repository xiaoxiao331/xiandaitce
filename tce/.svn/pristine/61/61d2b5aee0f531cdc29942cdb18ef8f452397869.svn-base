package tce.cmm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import able.com.secure.WebSecurityUtil;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tce.cmm.util.URIValidator;
import tce.cmm.util.UtilInterceptor;
import tce.cmm.util.UtilSecurity;

/**
 * <pre>
 * WEB 호출 전/후 로직을 담당하는 클래스
 * (Securiry 관련)
 * </pre>
 *
 * @ClassName   : MgrInterceptor.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 이명철
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.     이명철                최초 생성
 * </pre>
 */
public class MgrInterceptor extends HandlerInterceptorAdapter{
    /*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(URIValidator.isAuthExclusiveURL(request) == false) {
            UtilSecurity.checkSecurity(request, response);
            UtilInterceptor.checkSession(request, response);
        }
        //REFERER 정보가 변조 체크
        checkReferer(request);
        //HttpOnly 옵션 세팅
        setHttpOnly(response);
        //NoCache 옵션 세팅
        setNoCache(request, response);
        //X FRAME 옵션 세팅
        setXframe(response);
        //able-core 설정
        checkAble(request);
        return super.preHandle(request, response, handler);
    }
    
    /*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //공통 메뉴
        super.postHandle(request, response, handler, modelAndView);
        UtilInterceptor.menuInfo (request, modelAndView); // MENU 정보
        UtilInterceptor.basicInfo(request, modelAndView);
    }
    
    /*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    
    //WebSecurityInterceptor.java 合并内容
     
    private MultipartHttpServletRequest multipartRequest;

    
    
    //able-core 설정
    public void checkAble(HttpServletRequest request) throws Exception {
        
        HashMap<String, String> securityResult = null;
        @SuppressWarnings("unchecked")
        Map<String, String[]> params = request.getParameterMap();
        boolean result = false;
        String[] values = null;

        for (String key : params.keySet()) {
            values = (String[]) params.get(key);
            if (values != null) {
                for (int i = 0; i < values.length; ++i) {
                    securityResult = WebSecurityUtil.checkDownloadParams(values[i]);
                    if ("true".equals(securityResult.get("result"))) {
                        result = true;
                        break;
                    }
                }
                if (result) {
                    throw new Exception("Web Security Violation :  " + ((String) securityResult.get("securitySort"))
                            + ", Violation Char:: ' " + ((String) securityResult.get("violationChar")) + "'");
                }
            }

        }

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        if (multipartResolver.isMultipart(request)) {
            multipartRequest = (MultipartHttpServletRequest) request;
            @SuppressWarnings("unchecked")
            Iterator<String> iter = multipartRequest.getFileNames();
            while (iter.hasNext()) {
                String uploadFileName = (String) iter.next();
                MultipartFile file = multipartRequest.getFile(uploadFileName);
                String fileName = file.getOriginalFilename();
                securityResult = WebSecurityUtil.uploadFileExtCheck(fileName, "uploadExt");
                if ("true".equals(securityResult.get("result"))) {
                    throw new Exception("Web Security Violation " + ((String) securityResult.get("securitySort"))
                            + ", Violation Char:: ' " + ((String) securityResult.get("violationChar")) + "'");
                }

                securityResult = WebSecurityUtil.uploadFileExtCheck(fileName, "uploadDetour");
                if ("true".equals(securityResult.get("result"))) {
                    throw new Exception("Web Security Violation : " + ((String) securityResult.get("securitySort"))
                            + ", Violation Char:: ' " + ((String) securityResult.get("violationChar")) + "'");
                }
            }

            for (String key : params.keySet()) {
                values = (String[]) params.get(key);
                System.out.println(values);
                if (values != null) {
                    for (int i = 0; i < values.length; ++i) {
                        securityResult = WebSecurityUtil.checkDownloadParams(values[i]);
                        if ("true".equals(securityResult.get("result"))) {
                            result = true;
                            break;
                        }
                    }
                    if (result) {
                        throw new Exception("Web Security Violation : " + ((String) securityResult.get("securitySort"))
                                + ", Violation Char:: ' " + ((String) securityResult.get("violationChar")) + "'");
                    }

//URL修改                    values = WebSecurityUtil.convertXSSParams(values);
                    params.put(key, values);
                    System.out.println(params);
                }
            }
        }        
    }
    
    /**
     * REFERER 정보가 변조 체크
     *
     * @param request 요청정보
     * @throws Exception
     */
    public void checkReferer(HttpServletRequest request) throws Exception{
        
        String referer    = request.getHeader("referer");
        String requestUrl = request.getRequestURL().toString();
        String context    = requestUrl.substring(0, requestUrl.indexOf("/", 8));
        
        if(referer!=null && referer.indexOf(context) < 0){
            throw new Exception("REFERER 정보가 변조되었습니다.");
        }
    }
    
    /**
     * HttpOnly 옵션 세팅
     *
     * @param response 응답정보
     * @throws Exception
     */
    public static void setHttpOnly(HttpServletResponse response) throws Exception{
        response.setHeader("Set-Cookie", "name=HttpOnly; HttpOnly");
    }    
    
    /**
     * NoCache 옵션 세팅
     * 
     * @param request 
     * @param response 응답정보
     * @throws Exception Exception
     */
    public static void setNoCache(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        response.setHeader("Cache-Control","no-store");   
        response.setHeader("Pragma","no-cache");   
        response.setDateHeader("Expires",0);   
        
        if(request.getProtocol().equals("HTTP/1.1")){response.setHeader("Cache-Control", "no-cache");}
    }
    
    /**
     * X FRAME 옵션 세팅
     * 
     * @param response 응답정보
     * @throws Exception Exception
     */
    public static void setXframe(HttpServletResponse response) throws Exception{
        response.setHeader("X-Frame-Options", "SAMEORIGIN"); 
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("X-Content-Type-Options", "nosniff"); 

    }    
    
}