package tce.cmm.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import able.com.web.HController;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.ModelAndView;

import tce.cmm.Constant;
import tce.cmm.exception.ValidationException;
import tce.com.vo.SessionVO;
import tce.setting.service.UserService;
import tce.setting.vo.UserVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilInterceptor.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 4. 24.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 24.     LMC     	최초 생성
 * </pre>
 */

public class UtilInterceptor {
    
    /** LOG */
    private static Logger log = Logger.getLogger(UtilInterceptor.class);
    
    private static ApplicationContext appContext = ContextLoaderListener.getCurrentWebApplicationContext();
    private static UserService userService = (UserService) appContext.getBean("userService");
    
    private static URIValidator uriValidator = new URIValidator();
    
    /**
     * 세션 체크
     *
     * @param request
     * @param response
     * @throws Exception Exception
     */
    public static void checkSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        SessionVO sessionVO = (SessionVO) session.getAttribute(Constant.SESSION_ID);
        UserVO user         = sessionVO != null ? sessionVO.getUser() : null;
                
        // Login Duplicate Check
        int loginCheck = LoginDupCheck(request);
        
        if(loginCheck == 1 || loginCheck == 2 || loginCheck == 3){
            
            String message = "";
            
            // 1(S):Session finished, 2:Session finished(F) : Logged in from different places, 3(A):Session finished : Change your Auth
            if(loginCheck == 1){
                request.setAttribute("sessioncheck", "S");
                message = "Session finished";
            }else if(loginCheck == 2){
                request.setAttribute("sessioncheck", "F");
                message = "Session finished : Logged in from different places";
            }
            
            // 세션 정보 제거
            session.removeAttribute(Constant.SESSION_ID);
            session.removeAttribute(Constant.SESSION_IP);
            session.removeAttribute(HController.LOG_KEY);
            session.invalidate();
            
            // 쿠키 정보 제거
            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            
            request.getRequestDispatcher(Constant.MAIN_HOME).forward(request, response);
            
            return;
//            throw new Exception("Session finished : " + message); 
        }
        
        // Condition : Permission Check
        if(!authCheck(request)){
            throw new ValidationException(UtilCommon.toLocale(request, "msg.menu.auth.unable")); 
        }
        
        if(user != null){
            String checkSession = UtilStr.removeDelimStr(session.getId(),".");
            
            UserVO loginVO = new UserVO();
            loginVO.setUserId(user.getUserId());
            loginVO.setUserSid(checkSession);
            loginVO = userService.selectUserInfo(loginVO);
            
            // 매개변수 부정조작으로 인해 세션까지 체크
            if(loginVO == null){
                
                // 세션 정보 제거
                session.removeAttribute(Constant.SESSION_ID);
                session.removeAttribute(Constant.SESSION_IP);
                session.removeAttribute(HController.LOG_KEY);
                session.invalidate();
                
                // 쿠키 정보 제거
                Cookie cookie = new Cookie("JSESSIONID", null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                
                throw new ValidationException(UtilCommon.toLocale(request, "msg.user.session.invalid")); 
            }
        }
    }
    
    /**
     * Duplicate Login Check
     *
     * @param request
     * @param response 
     * @return int
     * @throws Exception Exception
     */
    private static int LoginDupCheck(HttpServletRequest request) throws Exception{
        
        // 1(S):Session finished, 2:Session finished(F) : Logged in from different places, 3(A):Session finished : Change your Auth
        
        int returnBool = 0;
        
        int validCheck = 0;
        String uri = request.getRequestURI();
        
        for(int i=0;i<Constant.DUP_LOGIN_CHECK_URL.length;i++){
            if(uri.endsWith(Constant.DUP_LOGIN_CHECK_URL[i])){
                validCheck = 4;
                break;
            }
        }
        
        if(validCheck == 0){
            
            HttpSession session = request.getSession();
            SessionVO sv = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
            UserVO loginManagerVO = new UserVO();
            
            if(sv == null){
                returnBool = 1;
            }else{
                loginManagerVO.setUserId(sv.getUser().getUserId());
                loginManagerVO = userService.selectUserInfo(loginManagerVO);
                String userCurrSid = UtilStr.removeDelimStr(session.getId(),".");
                
                if(!loginManagerVO.getUserSid().equals(userCurrSid)) {
                    returnBool = 2;
                }
            }
        }
        
        return returnBool;
    }
    
    /**
     * 권한 체크
     * (사용자 권한이 접근가능한 URI인지 판단하는 기능 추가(2019-06-04)
     *
     * @param request 요청정보
     * @return true:권한 성공, false:권한 실패
     * @throws Exception Exception
     */
    public static boolean authCheck(HttpServletRequest request) throws Exception {
        return uriValidator.isValidURI(request);
    }
    
    /**
     * Menu 정보
     *
     * @param request 요청정보
     * @param modelAndView modelAndView 객체
     * @throws Exception Exception
     */
    public static void menuInfo(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        if(svo != null)
            modelAndView.addObject("topMenuList",svo.getMenuList());
    }
    
    /**
     * 기본정보 세팅
     *
     * @param request
     * @param modelAndView
     * @throws Exception Exception
     */
    public static void basicInfo(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        // 기타정보 세팅
        try {
            // 현재년도 정보를 화면에 전달
            modelAndView.addObject("nowDay"    , UtilDate.getStrDate(UtilDate.FORMAT_DAY_DASH_NOT));
            // 현재일자 + 1년
            modelAndView.addObject("dftAftDay" , UtilDate.getCalcDay(UtilDate.getStrDate(UtilDate.FORMAT_DAY_DASH_NOT),365,UtilDate.FORMAT_DAY_DASH_NOT));
        } catch (Exception e) {
            log.debug("Etc info setting failed");
        }
    }
}