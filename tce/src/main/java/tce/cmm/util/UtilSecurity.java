package tce.cmm.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import able.com.secure.WebSecurityUtil;

import org.apache.commons.codec.binary.Base64;

import tce.cmm.Constant;
import tce.cmm.exception.CodeException;
import tce.cmm.exception.ValidationException;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilSecurity.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.     LMC     	최초 생성
 * </pre>
 */

public class UtilSecurity {
    private static String xssFilter = "<script>(.*?)</script>,src[\r\n]*=[\r\n]*\\\'(.*?)\\\',src[\r\n]*=[\r\n]*\\\"(.*?)\\\",</script>,<script(.*?)>,eval\\((.*?)\\),expression\\((.*?)\\),javascript:,vbscript:,onload(.*?)=,onmouse,toString,valueOf,window,alert,asp,jsp,href,src,iframe,import,onfocus,prompt,document,cookie,onclick";
    
    /**
     * 보안 체크
     *
     * @param request
     * @param response
     * @throws Exception Exception
     */
    public static void checkSecurity(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try {
            UtilSecurity.setHttpOnly(request, response); // HttpOnly 옵션 세팅
            UtilSecurity.setNoCache (request, response); // NoCache 옵션 세팅
            UtilSecurity.setXframe  (request, response); // X FRAME 옵션 세팅
            
            UtilSecurity.checkIp     (request); // 변조된 세션 IP 체크
            UtilSecurity.checkReferer(request); // 변조된 세션 REFERER 체크
            UtilSecurity.checkRequest(request); // REQUEST 보안 체크
        } catch(CodeException e) {
            throw new ValidationException(UtilCommon.toLocale(request, e.getCode()));
        }
    }
    
    /**
     * HttpOnly 옵션 세팅
     * 
     * @param request 
     * @param response 응답정보
     * @throws Exception Exception
     */
    public static void setHttpOnly(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        String sessionid = request.getSession().getId();
        response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly; secure");
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
     * @param request 
     * @param response 응답정보
     * @throws Exception Exception
     */
    public static void setXframe(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("X-Frame-Options", "SAMEORIGIN"); 
    }
    /**
     * X FRAME 옵션 세팅
     * X-XSS-Protection:          XSS 공격 차단
     * Strict-Transport-Security: Client Brower에 HTTPS 접속을 max-age동안 강제하도록 하는 설정(31536000초=1년)
     * X-Content-Type-Options:    StyleSheet는 MIMETYPE이 text/css와 일치할 때까지 Stylesheet를 로드하지 않음
     *                            공격자가 다른 확장자(JPG)로 서버에 파일을 업로드한 후 script를 변경해서 로그하는 공격 방지
     * Cache-control:             악의적인 사용자가 브라우저 히스토리를 악용할 수 있기 때문에 브라우저가 웹페이지 컨텐츠를 캐싱하지 않도록 설정
     * Set-Cookie: XSRF-TOKEN:    각 Request를 서버가 수신할 때마다 서버가 앞서 Response에서 전달한 XRF-TOKEN값과 
     *                            비교해서 Request Valid한 지 확인
     *                            
     * @param request 
     * @param response 응답정보
     * @throws Exception Exception
     */
    public static void setHeaderSecurity(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains"); 
        response.setHeader("X-Content-Type-Options", "nosniff"); 
        response.setHeader("Content-Security-Policy", "script-src 'self'");
        response.setHeader("Cache-control", "no-cache");

    }
    
    public static void setHeaderSecurityExp(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains"); 
        response.setHeader("X-Content-Type-Options", "nosniff"); 
        response.setHeader("Cache-control", "no-cache");
        response.setHeader("Content-Security-Policy", "script-src 'self'");

    }
    
    /**
     * 변조된 세션 IP 체크
     *
     * @param request 요청정보
     * @throws Exception Exception
     */
    public static void checkIp(HttpServletRequest request) throws Exception{
        String ip = UtilStr.isNull((String)request.getSession().getAttribute(Constant.SESSION_IP));
        //[CCSC]
        String remoteAddr = getIp(request);
        
        System.out.println("#### ip : " + ip + ", remoteAddr : " + remoteAddr + ", RequestURL : " + request.getRequestURL().toString());
        if (!ip.equals("") && !ip.equals(remoteAddr)) {
            // 세션정보가 변조되었습니다.
            throw new CodeException("msg.user.session.invalid");
        }
    }
    
    public static String getIp(HttpServletRequest request) {
        
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        return ip;
    }    
    /**
     * 변조된 세션 REFERER 체크
     *
     * @param request 요청정보
     * @throws Exception Exception
     */
    public static void checkReferer(HttpServletRequest request) throws Exception{
        String referer    = request.getHeader("referer");
        String requestUrl = request.getRequestURL().toString();

        //포트번호는 체크해제
        String context = requestUrl.substring(0, requestUrl.indexOf(":", 8));
        
        if(requestUrl!=null && requestUrl.indexOf(Constant.MAIN_HOME) < 0 && requestUrl.indexOf("/com/loginForm.do") < 0){
            if(referer!=null && referer.indexOf(context) < 0){
                // REFERER 정보가 변조되었습니다.
                throw new CodeException("msg.user.session.invalid");
            }
        }
    }
    
    /**
     * REQUEST 보안 체크
     *
     * @param request 요청정보
     * @throws Exception Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public static void checkRequest(HttpServletRequest request) throws Exception{
        
        HashMap securityResult = null;
        Map params             = request.getParameterMap();
        boolean result         = false;
        String values[]        = null;
        
        for(Iterator i$ = params.keySet().iterator(); i$.hasNext();){
            
            String key = (String)i$.next();
            values     = (String[])params.get(key);
            
            if(values != null){
                int i = 0;
                do{
                    if(i >= values.length) break;
                    
                    values[i] = replaceBanWord(values[i]);
//                    values[i] = replaceHtmlTag(values[i]);  // Commented by Song 201-06-04
                    
                    checkStringXss(values[i]);
                    checkPath     (values[i]);
                    
                    securityResult = WebSecurityUtil.checkDownloadParams(values[i]);
                    
                    if("true".equals(securityResult.get("result"))){
                        result = true;
                        break;
                    }
                    i++;
                } while(true);
                if(result) 
                    throw new Exception((new StringBuilder()).append("Web Security Violation :  ").append((String)securityResult.get("securitySort")).append(", Violation Char:: ' ").append((String)securityResult.get("violationChar")).append("'").toString());
            }
        }
    }
    
    /**
     * 변조된 매개변수 PATH 체크
     *
     * @param request
     * @throws Exception Exception
     */
    public static void checkPath(String value) throws Exception{
        if(value!=null && value.indexOf("..")> -1) {throw new Exception("잘못된 접근 방법입니다.");}
    }
    
    /**
     * checkStringXss
     *
     * @param value
     * @return String
     * @throws Exception Exception
     */
    public static String checkStringXss(String value) throws Exception {
        if (isXssValidInput(value))
            return value;
        else
            throw new Exception("isValid Input value!");
    }
    
    /**
     * isXssValidInput
     *
     * @param value
     * @return boolean
     */
    private static boolean isXssValidInput(String value) {
        
        String[] pattern = xssFilter.split(",");
        
        if (value != null) {
            value = value.replaceAll("", "");
            for (String ptn : pattern) {
                Pattern scriptPattern = Pattern.compile(ptn, Pattern.CASE_INSENSITIVE);
                if (!ptn.equals("asp") && !ptn.equals("jsp") && !ptn.equals("src") && scriptPattern.matcher(value).find()){
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }
    
    /**
     * HTML 태크 문자열 치환
     *
     * @param value 입력 문자열
     * @return String 치환 문자열
     */
    public static String replaceHtmlTag(String value){
        
        if (value == null) {return null;}
        
        StringBuffer result = new StringBuffer(value.length());
        
        for (int i=0; i<value.length(); ++i) {
            switch (value.charAt(i)) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>': 
                    result.append("&gt;");
                    break;
                /*case '"': 
                    result.append("&quot;");
                    break;*/
                case '\'': 
                    result.append("&#39;");
                    break;
                case '%': 
                    result.append("&#37;");
                    break;
                case ';': 
                    result.append("&#59;");
                    break;
                case '(': 
                    result.append("&#40;");
                    break;
                case ')': 
                    result.append("&#41;");
                    break;
                case '&': 
                    result.append("&amp;");
                    break;
//                case ':': 
//                    result.append("&#58;");
//                    break;
//                //新加
//                case '.': 
//                    result.append("\\.");
//                    break;
                /*case '+':
                    result.append("&#43;");
                    break;*/
                default:
                    result.append(value.charAt(i));
                    break;
            }
        }
        
        return result.toString();
    }
    
    /**
     * 문자열 치환
     *
     * @param value 입력 문자열
     * @return String 치환 문자열
     */
    public static String replaceReverseHtmlTag(String value){
        if (value == null) {return null;}
        
        String result = ""; 
        result = value .replaceAll("&#58;" , ":" );
        result = value .replaceAll("&lt;"  , "<" );
        result = result.replaceAll("&gt;"  , ">" );
        result = result.replaceAll("&quot;", "\"");
        result = result.replaceAll("&#39;" , "\\");
        result = result.replaceAll("&#37;" , "%" );
        result = result.replaceAll("&#59;" , ";" );
        result = result.replaceAll("&#40;" , "(" );
        result = result.replaceAll("&#41;" , ")" );
        result = result.replaceAll("&amp;" , "&" );
        result = result.replaceAll("&#43;" , "+" );
        result = result.replaceAll("&#64;" , "@" ); //CCS ID 이메일로 인한 처리
        //result = result.replaceAll("\\." , "." );
        
        //System.out.println("Reulst");
        
        return result.toString();
    }
    
    /**
     * 금칙어 문자열 치환
     *
     * @param value 입력 문자열
     * @return String 치환 문자열
     */
    public static String replaceBanWord(String value){
        
        String val = value;
        
        val = val.replaceAll("eval\\((.*)\\)", "");
        val = val.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        val = val.replaceAll("script", "");
        val = val.replaceAll("alert", "");
//        val = val.replaceAll("html", "");
        val = val.replaceAll("asp", "");
        val = val.replaceAll("jsp", "");
        val = val.replaceAll("--", "");
        val = val.replaceAll(" or ", "");
        val = val.replaceAll("onmouse", "");
        val = val.replaceAll("onerror", "");
        val = val.replaceAll("toString", "");
        val = val.replaceAll("valueOf", "");
        val = val.replaceAll("window", "");
        val = val.replaceAll("href", "");
        val = val.replaceAll("HREF", "");
        val = val.replaceAll("src", "");
        val = val.replaceAll("SRC", "");
        val = val.replaceAll("iframe", "");
        val = val.replaceAll("IFRAME", "");
        val = val.replaceAll("import", "");
        val = val.replaceAll("IMPORT", "");
        val = val.replaceAll(" on", "");
        val = val.replaceAll("<", "");
        val = val.replaceAll(">", "");
        val = val.replaceAll("\\(", "");
        val = val.replaceAll("\\)", "");
        
        return val;
    }
    
    /**
     * 데이터 일방향 암호화
     *
     * @param data 암호화 대상 문자열
     * @param id 솔트 ID
     * @return 암호화 문자열
     * @throws Exception Exception
     */
    public static String encryptData(String data, String id) throws Exception {
        
        if (data == null) {return "";}
        
        byte[] hashValue = null; // 해쉬값
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        md.reset();
        md.update(id.getBytes());
        
        hashValue = md.digest(data.getBytes());
        
        return new String(Base64.encodeBase64(hashValue));
    }
    
    /**
     * 허용 확장자 체크
     * 
     * @param fileName
     * @param regex
     * @return true - 허용, false - 미허용
     * @throws Exception
     */
    public static boolean checkFileAllowExt(String fileName, String regex) throws Exception {
        
        boolean result = false;
        
        Pattern pattern = Pattern.compile("([^\\s]+(\\.(?i)(" + regex +  ")){1,})");
        
        Matcher matcher = pattern.matcher(fileName);
        
        if(matcher.find()){result = true;}
        
        return result;
    }
    
    public static void main(String arg[]) {
        try {
            String str = UtilSecurity.encryptData("1", "admin");
            System.out.println(str);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
