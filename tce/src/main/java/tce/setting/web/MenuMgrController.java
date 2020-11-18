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
import tce.setting.service.MenuService;
import tce.setting.vo.MenuVO;

/**
 * <pre>
 * 메뉴 Mgr Controller
 * </pre>
 *
 * @ClassName   : MenuMgrController.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 
 * @since 2019. 4. 26.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 26.                 	최초 생성
 * </pre>
 */
@Controller
public class MenuMgrController extends HController{
    /** 메뉴 Service */
    @Resource(name = "menuService")
    private MenuService menuService;
    
    /**
     * 메뉴 관리 메인 화면
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/menuMain.do", method=RequestMethod.POST)
    public ModelAndView menuMain(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") MenuVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        List<MenuVO> menuList = null;
        UtilPage utilPage = null;
        int totCnt = 0;
        
        //메인 화면 : 최초 화면 접속시는 조회를 하지 않는다.
        if(paramVO.isSearchEmpty()){
            menuList = menuService.selectMenuList(paramVO);
            //Paging 관련
            totCnt = menuService.selectMenuListCnt(paramVO);
            utilPage = new UtilPage(request.getRequestURI(), new Integer(paramVO.getPageSize()).intValue(), totCnt, new Integer(paramVO.getPageIndex()).intValue());
        }
        
        ModelAndView mav = new ModelAndView("/setting/menuMain");
        mav.addObject("rList", menuList);
        mav.addObject("rPage", utilPage);  
        mav.addObject("rCnt" , totCnt);  
        return mav;
    }
    
    /**
     * 메뉴 관리 등록 화면
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/menuRegistForm.do", method=RequestMethod.POST)
    public ModelAndView menuRegistForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") MenuVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //메뉴 상위 리스트 조회
        List<MenuVO> upperMenuList = menuService.selectMenuUpperList(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/menuRegistForm");
        mav.addObject("upperMenuList", upperMenuList);
        return mav;
    }
    
    /**
     * 메뉴 관리 등록 처리
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/menuRegist.do", method=RequestMethod.POST)
    public ModelAndView menuRegist(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") MenuVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setFrstId(svo.getUser().getUserId());
        
        //메뉴 등록
        menuService.insertMenuInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/menuMain.do");
        return mav;
    }
    
    /**
     * 메뉴 상세 화면
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/menuDetailForm.do", method=RequestMethod.POST)
    public ModelAndView menuDetailForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") MenuVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //메뉴상세 조회
        MenuVO menuVO = menuService.selectMenuInfo(paramVO);
        
        //메뉴 상위 리스트 조회
        List<MenuVO> upperMenuList = menuService.selectMenuUpperList(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/menuDetailForm");
        mav.addObject("rData", menuVO);
        mav.addObject("upperMenuList", upperMenuList);
        
        return mav;
    }
    
    /**
     * 메뉴 관리 수정 처리
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/menuUpdate.do", method=RequestMethod.POST)
    public ModelAndView menuUpdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") MenuVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setLastId(svo.getUser().getUserId());
        
        //메뉴 수정
        menuService.updateMenuInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/menuMain.do");
        return mav;
    }
    
    /**
     * 메뉴 관리 삭제 처리
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/menuDelete.do", method=RequestMethod.POST)
    public ModelAndView menuDelete(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") MenuVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //메뉴 삭제
        menuService.deleteMenuInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/menuMain.do");
        return mav;
    }
}
