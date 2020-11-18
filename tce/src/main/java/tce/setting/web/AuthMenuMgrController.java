package tce.setting.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import able.com.web.HController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tce.cmm.Constant;
import tce.cmm.TCELog;
import tce.cmm.util.UtilPage;
import tce.com.vo.SessionVO;
import tce.setting.service.AuthMenuMapService;
import tce.setting.service.AuthService;
import tce.setting.vo.AuthMenuMapVO;
import tce.setting.vo.AuthVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : AuthMenuMgrController.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 6. 5.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 6. 5.     LMC     	최초 생성
 * </pre>
 */

@Controller
public class AuthMenuMgrController extends HController {
    /** 권한 Service */
    @Resource(name = "authService")
    private AuthService authService;
    
    /** 메뉴 Service */
    @Resource(name = "authMenuMapService")
    private AuthMenuMapService authMenuMapService;
    
    /**
     * 메뉴권한 메인 화면
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/authMenuMain.do", method=RequestMethod.POST)
    public ModelAndView getMenuMapMain(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") AuthMenuMapVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        List<AuthMenuMapVO> menuList = null;
        UtilPage utilPage = null;
        int totCnt = 0;
        
        //메인 화면 : 최초 화면 접속시는 조회를 하지 않는다.
        if(paramVO.isSearchEmpty()){
            menuList = authMenuMapService.selectAuthMenuList(paramVO);
            
            //Paging 관련
            totCnt = authMenuMapService.selectAuthMenuListCnt(paramVO);
            utilPage = new UtilPage(request.getRequestURI(), new Integer(paramVO.getPageSize()).intValue(), totCnt, new Integer(paramVO.getPageIndex()).intValue());
        }
        
        //권한 Combobox
        List<AuthVO> authList = authService.selectAuthCodeList(null);
        
        ModelAndView mav = new ModelAndView("/setting/authMenuMain");
        mav.addObject("rList"   , menuList);
        mav.addObject("rPage"   , utilPage);
        mav.addObject("rCnt"    , totCnt  ); 
        mav.addObject("authList", authList);
        return mav;
    }
    
    /**
     * 메뉴권한 수정 처리
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/authMenuUpdate.do", method=RequestMethod.POST)
    public ModelAndView authMenuUpdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") AuthMenuMapVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setLastId(svo.getUser().getUserId());
        
        //메뉴권한 수정
        authMenuMapService.updateAuthMenuInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/authMenuMain.do");
        return mav;
    }
}
