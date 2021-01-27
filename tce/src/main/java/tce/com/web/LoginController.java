package tce.com.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import able.com.web.HController;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.alibaba.fastjson.JSONObject;
import com.ibm.icu.text.SimpleDateFormat;

import tce.cmm.Constant;
import tce.cmm.TCELog;
import tce.cmm.TCEProperties;
import tce.cmm.exception.ValidationException;
import tce.cmm.util.UtilCommon;
import tce.cmm.util.UtilDate;
import tce.cmm.util.UtilSecurity;
import tce.cmm.util.UtilStr;
import tce.com.IFType;
import tce.com.SMSSender;
import tce.com.TMSHeader;
import tce.com.TextSMS;
import tce.com.vo.SessionVO;
import tce.setting.service.MenuService;
import tce.setting.service.UserService;
import tce.setting.vo.MenuVO;
import tce.setting.vo.UserVO;


/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : LoginController.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.     LMC        최초 생성
 * </pre>
 */
@Controller
public class LoginController extends HController{
    private Log log = LogFactory.getLog(this.getClass());
    
    /** 사용자 Service */
    @Resource(name = "userService")
    private UserService userService;
    
    /** 메뉴 Service */
    @Resource(name = "menuService")
    private MenuService menuService;
    
    /**
     * Login Form 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/com/loginForm.do", method=RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 동일 세션 ID 체크 - App Scan 보안취약으로 인한 기존 세션 무효화
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();
        return "empty/com/loginForm";
    }
    
    /**
     * login
     *
     * @param request 요청정보
     * @param response 응답정보
     * @param paramVO UserVO
     * @return ModelAndView
     * @throws Exception Exception
     */
    @RequestMapping(value = "/com/login.do", method=RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //ARIA 복호화
        paramVO.setUserId(new String(Base64.decodeBase64(paramVO.getUserId().getBytes("UTF-8")),"UTF-8"));
        paramVO.setUserPw(new String(Base64.decodeBase64(paramVO.getUserPw().getBytes("UTF-8")),"UTF-8"));
        
        //LOCALE 초기 정보 세팅
        String localeNm = paramVO.getLocale();
        HttpSession sessionLocale = request.getSession();
        
        Locale locales = null;
        if (localeNm.matches("KR"))
            locales = Locale.KOREAN;
        else if (localeNm.matches("ZH"))
            locales = Locale.CHINESE;
        
        // 세션에 존재하는 LOCALE 을 새로운 언어로 변경해준다.
        sessionLocale.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales); 
        
        String id = paramVO.getUserId();
        
        // 초기 비밀번호 체크용
        String checkDefault = Constant.GUBN_N;
        
        // 비밀번호 유효기간 체크용(Y:변경 팝업창 띄움 N:그대로 진행)
        String checkExpire = Constant.GUBN_N;
        
        // 로그인 기본 FORWARD 경로
        String viewStr = "";
        
        // 이중 로그인 체크
        if((SessionVO)request.getSession().getAttribute(Constant.SESSION_ID) != null)
            viewStr = "forward:/com/logout.do";
        
        // 조회 - 로그인
        String paramDefaultPw = paramVO.getUserPw();
        if(paramDefaultPw != null && paramDefaultPw.trim().length() > 0) 
            paramVO.setUserPw(UtilSecurity.encryptData(paramDefaultPw, paramVO.getUserId()));
        
        UserVO result = userService.selectUserInfo(paramVO);
        
        // 조건 : 로그인 정보가 있을 경우
        if(result != null) {
            HttpSession session = request.getSession(true);
            
            // 동일 세션 ID 체크 - App Scan 보안취약으로 인한 SessionID UpdateCheck
            // Login을 통해 Db와 동일 ID를 가지고 있다면 강제 로그아웃을 시킨다.
            String checkSessionId = session.getId();
            UserVO userVO = new UserVO();
            userVO.setUserId(paramVO.getUserId());
            UserVO checkListenerVO = userService.selectUserInfo(userVO);
            
            if(checkListenerVO != null && checkListenerVO.getUserSid() != null){
                if(UtilStr.removeDelimStr(checkSessionId,".").equals(checkListenerVO.getUserSid())){
                    viewStr = "forward:/com/logout.do";
                    ModelAndView mav = new ModelAndView(viewStr);
                    return mav;
                }
            }
            
            // 사용자 업무유효기간과 오늘날짜 비교
            String startDate = result.getAcctStartDate();
            String endDate = result.getAcctEndDate();
            String today = UtilDate.getStrDate(UtilDate.FORMAT_DAY_DASH_NOT);
            if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
                int from = Integer.parseInt(startDate);
                int to = Integer.parseInt(endDate);
                int now = Integer.parseInt(today);
                if(!(from <= now && now <= to))
                    throw new ValidationException(UtilCommon.toLocale(request, "msg.login.account.expired"));
            }
            
            // 조건 : 사용자 사용유무 정보가 N 인 경우
            if(result.getUserUseYn().equals(Constant.GUBN_N))
                throw new ValidationException(UtilCommon.toLocale(request,  "msg.login.account.unable"));
            
            // 조건 : 미접속 기간이 90일이 초과한 경우
            String lastLoginDate = result.getUserLastLgiPot();
            if(lastLoginDate != null) {
                Date mdate = UtilDate.getDate(result.getUserLastLgiPot(), UtilDate.FORMAT_DEFAULT);
                if(UtilDate.getBetweenDate(mdate, new Date()) > TCEProperties.getLoginNotDay())
                    throw new ValidationException(UtilCommon.toLocale(request,  "msg.login.account.unused"));
            }
            
            // 초기 비밀번호 체크
            checkDefault = UtilSecurity.encryptData(paramDefaultPw, result.getUserId()).equals(UtilSecurity.encryptData(TCEProperties.getDefaultPw(), result.getUserId())) ? Constant.GUBN_Y : Constant.GUBN_N;

            // 조건 : 초기 비밀번호일 경우
            if(checkDefault.equals(Constant.GUBN_Y)) {
                viewStr = "empty/com/loginForm";
            } else {
                //비밀번호 유효기간
                //현재날짜 가져오기
                String nowDateStr = UtilDate.getStrDate(UtilDate.FORMAT_DAY_DASH_NOT);
                
                //현재날짜와 유효일자 비교
                if(nowDateStr.compareTo(result.getExprMdfyPot()) > 0){
                    // 유효기간 팝업창을 띄우기 위한 값
                    checkExpire  = Constant.GUBN_Y;
                    // View jsp
                    viewStr = "empty/com/loginForm";
                }
                
                //최종적으로 로그인 성공
                if(checkExpire.equals(Constant.GUBN_N)) {
                    // 사용자 메뉴 리스트
                    MenuVO menuVO = new MenuVO();
                    menuVO.setUserId(id);
                    // 등록된 메뉴 전체 목록 조회(사용가능 유무 포함)
                    List<MenuVO> authMenuList = menuService.selectAuthMenuForSession(menuVO);
                    
                    // 로긴된 사용자가 사용가능한 메뉴만 조회
                    menuVO.setUseYn(Constant.GUBN_Y);
                    List<MenuVO> menuList = menuService.selectUserAuthMenuLocale(request, menuVO);
                    
                    // 세션 정보 세팅
                    SessionVO sessionVO = new SessionVO();
                    sessionVO.setUser(result);
                    sessionVO.setMenuList(menuList);
                    sessionVO.setAuthMenuList(authMenuList);
                    session = request.getSession(true);
                    
                    session.setAttribute(Constant.SESSION_ID, sessionVO              );
                    session.setAttribute(Constant.SESSION_IP, UtilSecurity.getIp(request));//[CCSC]
                    session.setAttribute(LOG_KEY            , result.getUserId()     );
                    
                    //중복 로그인을 방지하기 위한 설정
                    UserVO sessionListenerVO = new UserVO();
                    sessionListenerVO.setUserId(result.getUserId());
                    sessionListenerVO.setUserSid(UtilStr.removeDelimStr(session.getId(), "."));
                    userService.updateSession(sessionListenerVO);
                    
                    // 최종 로그인 일시 세팅
                    UserVO uservo = new UserVO();
                    uservo.setUserId(result.getUserId());
                    uservo.setUserLastLgiPot(new Date().toString());
                    // 로그인시 패스워드 실패 카운트 초기화
                    uservo.setUserPwFailCnt(0);
                    // 최종 로그인 일시 수정
                    userService.updateUserInfo(uservo);
                    
                    // 로그인 성공 시 로그처리를 위한 상태 세팅
                    request.setAttribute(Constant.INTERCEPTOR_CHK_LOGIN, Constant.INTERCEPTOR_CHK_VAL00);
                    
                    // 로그인 성공시 이동할 페이지
                    if(viewStr.equals("")) {
                        ModelAndView mav = new ModelAndView("forward:/work/workMainPage.do");
                        return mav;
                    }
                }
            }
        }else{
            // 로그인 실패 시 로그처리를 위한 상태 세팅
            request.setAttribute(Constant.INTERCEPTOR_CHK_LOGIN, Constant.INTERCEPTOR_CHK_VAL01);
            // 아이디로 회원정보 조회
            paramVO.setUserPw("");
            UserVO check = userService.selectUserInfo(paramVO);
            
            
            // 조건 : 아이디로 검색한 사용자 정보가 존제하지 않을 경우
            if(check != null){
                int failCnt = check.getUserPwFailCnt() + 1;
                
                // 비밀번호 실패 카운트 처리
                UserVO userVO = new UserVO();
                userVO.setUserId(check.getUserId());
                userVO.setUserPwFailCnt(failCnt);
                
                // 조건 : 잘못된 비밀번호 5회 입력 시
                if(failCnt >= TCEProperties.getPwFailCnt())
                    userVO.setUserUseYn(Constant.GUBN_N);
                
                // 수정 - 카운트 업데이트
                userService.updateUserInfo(userVO);
                
                // 조건 : 잘못된 비밀번호 5회 입력 시
                if(failCnt >= TCEProperties.getPwFailCnt())
                    throw new ValidationException(UtilCommon.toLocale(request,  "msg.login.account.pwdlock"));
            }
            //TODO : MESSAGE 정리
            //throw new ValidationException(UtilCommon.toLocale(request, messageSource, "msg.login.account.incurrect"));
            throw new ValidationException(UtilCommon.toLocale(request, "msg.login.account.loginfail"));
        }
        
        ModelAndView mav = new ModelAndView(viewStr); 
        //초기비밀번호 팝업창 띄우기 여부
        mav.addObject("checkDefault", checkDefault);
        //비밀번호 유효기간 팝업창 띄우기 여부
        mav.addObject("checkExpire", checkExpire);
        return mav;
    }
    
    /**
     * logout
     *
     * @param request 요청정보
     * @param response 응답정보
     * @param paramVO UserVO
     * @return ModelAndView
     * @throws Exception Exception
     */
    @RequestMapping(value = "/com/logout.do", method=RequestMethod.POST)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, UserVO paramVO) throws Exception {
        log.debug(paramVO.getUserId() + " : " + paramVO.getUserPw());
        
        HttpSession session = request.getSession();
        
        // 세션 정보 제거
        session.removeAttribute(Constant.SESSION_ID);
        session.removeAttribute(Constant.SESSION_IP);
        session.removeAttribute(HController.LOG_KEY);
        session.invalidate();
        
        // 쿠키 정보 제거
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        
        // Logout 상태 세팅
        request.setAttribute(Constant.INTERCEPTOR_CHK_LOGOUT, Constant.INTERCEPTOR_CHK_VAL00);
        ModelAndView mav = new ModelAndView("empty/com/loginForm"); 
        return mav;
    }

    /**
     * login
     *
     * @param request 요청정보
     * @param response 응답정보
     * @param paramVO UserVO
     * @return ModelAndView
     * @throws Exception Exception
     */
    @RequestMapping(value = "/com/changeInitPwdForm.do", method=RequestMethod.GET)
    public ModelAndView changeInitPwdForm(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("rParam") UserVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("popup/com/pwUpdateForm");
        return mav;
    }
    
    /**
     * login
     *
     * @param request 요청정보
     * @param response 응답정보
     * @param paramVO UserVO
     * @return ModelAndView
     * @throws Exception Exception
     */
    @RequestMapping(value = "/com/changeInitPwd.do", method=RequestMethod.POST)
    public ModelAndView changeInitPwd(HttpServletRequest request, HttpServletResponse response, UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        String newPwd = null;
        String oldPwd = null;
        
        //ARIA 복호화
        paramVO.setUserId(new String(Base64.decodeBase64(paramVO.getUserId().getBytes("UTF-8")),"UTF-8"));
        paramVO.setUserPw(new String(Base64.decodeBase64(paramVO.getUserPw().getBytes("UTF-8")),"UTF-8"));
        paramVO.setOldPasswd(new String(Base64.decodeBase64(paramVO.getOldPasswd().getBytes("UTF-8")),"UTF-8"));
        
        //SHA-256 암호화
        paramVO.setOldPasswd(oldPwd = UtilSecurity.encryptData(paramVO.getOldPasswd(), paramVO.getUserId()));
        paramVO.setUserPw(newPwd = UtilSecurity.encryptData(paramVO.getUserPw(), paramVO.getUserId()));
        
        if(oldPwd.equals(newPwd))
            throw new ValidationException("ERROR:Input different password for new password");
                    
        paramVO.setUserPw(oldPwd);
        paramVO.setOldPasswd("");
        
        // 조회 - 로그인
        UserVO result = userService.selectUserInfo(paramVO);
        
        // 조건 : 로그인 정보가 있을 경우
        if(result != null) {
            paramVO.setUserPw(newPwd);
            paramVO.setOldPasswd(oldPwd);
            paramVO.setLastId("SADMIN");
            userService.updateUserPasswd(paramVO);
        }else{
            throw new ValidationException("ERROR:Wrong information");
        }
        
        ModelAndView mav = new ModelAndView("popup/com/pwUpdateForm");
        mav.addObject("msg" , "변경하였습니다. 다시 로그인하십시오.");
        return mav;
    }
    
    /**
     * @Description 手机号码短信验证
     **/
    private static final String MSG_INFO = "请输入手机认证号码[%s],有效时间为3分钟。";
    public static final SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    
    @RequestMapping(value="/com/sendSMS.do")
    public void stringPhoneLogin( HttpServletRequest request, HttpServletResponse resp,
                                Locale locale,
                                @ModelAttribute("UserVo") UserVO oUserVo,
                                HttpSession session,
                                Model model) throws Exception
    {  
        log.debug("----------------");
        String phone = oUserVo.getStrUserHptn();
        log.debug("-------phone---------"+phone);
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        TextSMS textSMS = new TextSMS.Builder(phone, String.format(MSG_INFO, code)).sender("CCSLOGIN").time(
                SDF_YYYYMMDDHHMMSS.format(Calendar.getInstance().getTime()))
                .build();
        Header[] headers = new TMSHeader.Builder(phone,
                "").build().toHTTPHeaders();
        try {
            SMSSender.sendSMS(IFType.CMM_04, textSMS, headers);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    //根据获取到的手机号发送验证码
    //将验证码存到session中,同时存入创建时间

    //以json存放，这里使用的是阿里的fastjson
    JSONObject json = new JSONObject();

    json.put("verifyCode", code);

    json.put("createTime", System.currentTimeMillis());

    // 将认证码存入SESSION
    request.getSession().setAttribute("verifyCode", json);
    resp.getWriter().write(json.toString());

    }
}
    
