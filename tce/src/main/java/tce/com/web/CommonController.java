package tce.com.web;

import java.util.Locale;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import able.com.web.HController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import tce.cmm.Constant;
import tce.com.service.CommonService;
import tce.com.vo.CommonVO;
import tce.com.vo.SessionVO;

/**
 * <pre>
 * 공통 처리 Controller
 * </pre>
 *
 * @ClassName   : CommonController.java
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

@Controller
public class CommonController extends HController{
    private Log log = LogFactory.getLog(this.getClass());
    
    /** 공통 Service */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    /**
     * TCE Web frame
     *
     * @param request 요청정보
     * @param response 응답정보
     * @return ModelAndView
     * @throws Exception Exception
     */
    @RequestMapping(value = "/tce.do")
    public String tce(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
        String sessionCheck = (String) request.getAttribute("sessioncheck");
        // 세션 정보 제거
        HttpSession session = request.getSession(true);
        session.removeAttribute(Constant.SESSION_ID);
        session.removeAttribute(Constant.SESSION_IP);
        session.removeAttribute(LOG_KEY);
        session.invalidate();
        
        // 쿠키 정보 제거
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        log.info(cookie);
        model.addAttribute("SESSION_CHECK", sessionCheck == null ? Constant.GUBN_N : sessionCheck);
        return "tce";
    }
    /**
     * 
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/com/main.do", method=RequestMethod.POST)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CommonVO paramVO) throws Exception {
        ModelAndView mv = new ModelAndView("main");
        
        SessionVO svo = (SessionVO) request.getSession().getAttribute(Constant.SESSION_ID);
        String gubun = svo.getUser().getUserGubun();
        paramVO.setCorpGubun(gubun);
        
        if(gubun.equals("A") == false)
            paramVO.setSearchVal01(gubun);
        else {
            String opt = paramVO.getSearchVal01();
            if(opt == null || opt.trim().length() < 1)
                paramVO.setSearchVal01("H");
        }
        
        return mv;
    }
    
    /**
     * changeLocale
     *
     * @param locale
     * @param request 요청정보
     * @param response 응답정보
     * @return String
     * @throws Exception Exception
     */
    @RequestMapping(value = "/com/changeLocale.do", method=RequestMethod.POST)
    public String changeLocale(@RequestParam(required = false) String locale, 
            @RequestParam(required = false, defaultValue="/com/main") String url, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        HttpSession session = request.getSession();
        
        Locale locales = null;
        
        if (locale.matches("ko")) {
            locales = Locale.KOREAN;
        } else if (locale.matches("en") || locale.matches("en_US")) {
            locales = Locale.ENGLISH;
        } else if (locale.matches("zh") || locale.matches("zh_CN")) {
            locales = Locale.CHINESE;
        }
        
        // 세션에 존재하는 LOCALE 을 새로운 언어로 변경해준다.
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales);
        
        // 해당 컨트롤러에게 요청을 보낸 주소로 돌아간다.
        String forwardURL = "forward:" + url;
        
        return forwardURL;
    }
}
