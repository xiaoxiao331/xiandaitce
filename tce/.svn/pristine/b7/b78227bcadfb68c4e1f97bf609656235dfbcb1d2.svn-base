package tce.setting.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import able.com.web.HController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tce.cmm.Constant;
import tce.cmm.TCELog;
import tce.com.vo.SessionVO;
import tce.setting.service.CodeService;
import tce.setting.vo.CodeVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : CodeMgrController.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 5. 31.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 31.     LMC     	최초 생성
 * </pre>
 */
@Controller
public class CodeMgrController extends HController{
    /** 사용자 Service */
    @Resource(name = "codeService")
    private CodeService codeService;
    
    /**
     * 그룹코드 메인 화면 
     * 
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeMain.do", method=RequestMethod.POST)
    public ModelAndView codeMain(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //그룹코드 리스트 조회
        List<CodeVO> groupCodeList = codeService.selectGroupCodeList(paramVO);
        
        //상세 리스트 출력 Flag(상세 코드 등록 후 메인화면으로 올 때 리스트 뿌려주는 용도)
        String flag = !StringUtils.isEmpty(paramVO.getGroupCodeId()) ? "Y" : "N";
        
        ModelAndView mav = new ModelAndView("/setting/codeMain");
        mav.addObject("rList", groupCodeList);
        mav.addObject("rFlag", flag);
        return mav;
    }
    
    /**
     * 그룹코드 등록 화면
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeGroupRegistForm.do", method=RequestMethod.POST)
    public ModelAndView codeGroupRegistForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/codeGroupRegistForm");
        return mav;
    }
    
    /**
     * 그룹코드 등록 처리
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeGroupRegist.do", method=RequestMethod.POST)
    public ModelAndView codeGroupRegist(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setFrstId(svo.getUser().getUserId());
        
        //그룹코드 등록
        codeService.insertGroupCodeInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/codeMain.do");
        return mav;
    }
    
    /**
     * 그룹코드 상세 보기
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeGroupDetailForm.do", method=RequestMethod.POST)
    public ModelAndView codeGroupDetailForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //그룹코드 정보 조회
        CodeVO codeVO = codeService.selectGroupCodeInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/codeGroupDetailForm");
        mav.addObject("rData", codeVO);
        return mav;
    }
    
    /**
     * 그룹코드 수정 처리
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeGroupUpdate.do", method=RequestMethod.POST)
    public ModelAndView codeGroupUpdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setLastId(svo.getUser().getUserId());
        
        //그룹코드 수정
        codeService.updateGroupCodeInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/codeMain.do");
        return mav;
    }
    
    /**
     * 상세 코드 리스트 조회 
     * 
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeDetailList.do", method=RequestMethod.POST)
    public ModelAndView codeDetailList(HttpServletRequest request, HttpServletResponse response, CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //그룹코드 리스트 조회
        List<CodeVO> detailCodeList = codeService.selectDetailCodeList(paramVO);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("rList", detailCodeList);
        return mav;
    }
    
    /**
     * 상세코드 등록 화면
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeDetailRegistForm.do", method=RequestMethod.POST)
    public ModelAndView codeDetailRegistForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/codeDetailRegistForm");
        return mav;
    }
    
    /**
     * 상세코드 등록 처리
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeDetailRegist.do", method=RequestMethod.POST)
    public ModelAndView codeDetailRegist(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setFrstId(svo.getUser().getUserId());
        
        //상세코드 등록
        codeService.insertDetailCodeInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/codeMain.do");
        return mav;
    }
    
    /**
     * 상세코드 상세 보기
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeDetailForm.do", method=RequestMethod.POST)
    public ModelAndView codeDetailForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //상세코드 정보 조회
        CodeVO codeVO = codeService.selectDetailCodeInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("/setting/codeDetailForm");
        mav.addObject("rData", codeVO);
        return mav;
    }
    
    /**
     * 상세코드 수정 처리
     * 
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/codeDetailUpdate.do", method=RequestMethod.POST)
    public ModelAndView codeDetailUpdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") CodeVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setLastId(svo.getUser().getUserId());
        
        //상세코드 수정
        codeService.updateDetailCodeInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/codeMain.do");
        return mav;
    }
}
