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
import tce.setting.service.AuthService;
import tce.setting.vo.AuthVO;

/**
 * <pre>
 * 권한 관리 Controller
 * </pre>
 *
 * @ClassName   : AuthMgrController.java
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
public class AuthMgrController extends HController {
    /** 메뉴 Service */
    @Resource(name = "authService")
    private AuthService authService;
    
    /**
     * 권한 관리 메인
     * 
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/authMain.do", method=RequestMethod.POST)
    public ModelAndView authMain(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") AuthVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        List<AuthVO> authList = null;
        UtilPage utilPage = null;
        int totCnt = 0;
        
        //메인 화면 : 최초 화면 접속시는 조회를 하지 않는다.
        if(paramVO.isSearchEmpty()){
            authList = authService.selectAuthList(paramVO);
            
            //Paging
            totCnt = authService.selectAuthListCnt(paramVO);
            utilPage = new UtilPage(request.getRequestURI(), new Integer(paramVO.getPageSize()).intValue(), totCnt, new Integer(paramVO.getPageIndex()).intValue());
        }
        
        ModelAndView mav = new ModelAndView("/setting/authMain");
        mav.addObject("rList", authList);
        mav.addObject("rPage", utilPage);
        mav.addObject("rCnt" , totCnt  );
        return mav;
    }
    
    /**
     * 권한 관리 등록 화면
     *
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/authRegistForm.do", method=RequestMethod.POST)
    public ModelAndView authRegistForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") AuthVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/authRegistForm");
        return mav;
    }
    
    /**
     * 권한 관리 등록 처리
     *
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/authRegist.do", method=RequestMethod.POST)
    public ModelAndView authRegist(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") AuthVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setFrstId(svo.getUser().getUserId());
        
        //권한관리 등록
        authService.insertAuthInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/authMain.do");
        return mav;
    }
    
    /**
     * 권한 관리 상세 화면
     *
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/authDetailForm.do", method=RequestMethod.POST)
    public ModelAndView authDetailForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") AuthVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //권한 상세 조회
        AuthVO authVO = authService.selectAuthInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/authDetailForm");
        mav.addObject("rData", authVO);
        return mav;
    }
    
    /**
     * 권한 관리 수정 처리
     *
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/authUpdate.do", method=RequestMethod.POST)
    public ModelAndView authUpdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") AuthVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setLastId(svo.getUser().getUserId());
        
        //권한관리 수정
        authService.updateAuthInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/authMain.do");
        return mav;
    }
}
