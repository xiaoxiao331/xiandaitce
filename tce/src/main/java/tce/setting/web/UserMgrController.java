package tce.setting.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import able.com.web.HController;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tce.cmm.Constant;
import tce.cmm.TCELog;
import tce.cmm.TCEProperties;
import tce.cmm.util.UtilPage;
import tce.cmm.util.UtilSecurity;
import tce.com.vo.SessionVO;
import tce.setting.service.AuthService;
import tce.setting.service.UserService;
import tce.setting.vo.AuthVO;
import tce.setting.vo.UserVO;

/**
 * <pre>
 * User 관리 Controller
 * </pre>
 *
 * @ClassName   : UserMgrController.java
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
public class UserMgrController extends HController{
    /** 사용자 Service */
    @Resource(name = "userService")
    private UserService userService;
    
    @Resource(name = "authService")
    private AuthService authService;
    
    /**
     * 사용자 관리 메인 화면 
     * 
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userMain.do", method=RequestMethod.POST)
    public ModelAndView userMain(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        List<UserVO> userList = null;
        UtilPage utilPage = null;
        int totCnt = 0;
        
        //메인 화면 : 최초 화면 접속시는 조회를 하지 않는다.
        if(paramVO.isSearchEmpty()){
            userList = userService.selectUserList(paramVO);
            
            //Paging 관련
            totCnt = userService.selectUserListCnt(paramVO);
            utilPage = new UtilPage(request.getRequestURI(), new Integer(paramVO.getPageSize()).intValue(), totCnt, new Integer(paramVO.getPageIndex()).intValue());
        }
        
        //권한 ComboBox
        List<AuthVO> authList = authService.selectAuthCodeList(null);
        
        ModelAndView mav = new ModelAndView("/setting/userMain");
        mav.addObject("rList"   , userList);
        mav.addObject("rPage"   , utilPage);  
        mav.addObject("authList", authList);
        mav.addObject("rCnt"    , totCnt  );
        return mav;
    }
    
    /**
     * 사용자 등록 화면
     *
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userRegistForm.do", method=RequestMethod.POST)
    public ModelAndView userRegistForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //권한 ComboBox
        List<AuthVO> authList = authService.selectAuthCodeList(null);
        ModelAndView mav = new ModelAndView("/setting/userRegistForm");
        mav.addObject("authList", authList);
        return mav;
    }
    
    /**
     * 사용자 등록 처리
     *  
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userRegist.do", method=RequestMethod.POST)
    public ModelAndView userRegist(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        TCELog.voToString(paramVO);
        
        //ARIA 복호화
        paramVO.setUserId(new String(Base64.decodeBase64(paramVO.getUserId().getBytes("UTF-8")),"UTF-8"));
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setFrstId(svo.getUser().getUserId());
        
        //비밀번호 암호화(SHA-256)
        String pwd = paramVO.getUserPw();
        if(StringUtils.isEmpty(pwd))
            pwd = UtilSecurity.encryptData(TCEProperties.getDefaultPw(), paramVO.getUserId());
        else{
            paramVO.setUserPw(new String(Base64.decodeBase64(paramVO.getUserPw().getBytes("UTF-8")),"UTF-8"));
            pwd = UtilSecurity.encryptData(paramVO.getUserPw(), paramVO.getUserId());
        }
        paramVO.setUserPw(pwd);
        
        //파라미터 출력
        TCELog.voToString(paramVO);
        userService.insertUserInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/userMain.do");
        return mav;
    }
    
    /**
     * 사용자 정보 상세 
     * 
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userDetailForm.do", method=RequestMethod.POST)
    public ModelAndView userDetailForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        UserVO suser = svo.getUser();
        
        
        //사용자 정보
        UserVO userVO = userService.selectUserInfo(paramVO);
        
        //권한 ComboBox
        List<AuthVO> authList = authService.selectAuthCodeList(null);
        
        ModelAndView mav = new ModelAndView("/setting/userDetailForm");
        mav.addObject("rData", userVO);
        mav.addObject("authList", authList);
        if(userVO.getUserId().equals(suser.getUserId()))
            mav.addObject("Delete", "NO");
        else
            mav.addObject("Delete", "YES");
        return mav;
    }
    
    /**
     * 사용자 정보 삭제 처리
     *
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userDelete.do", method=RequestMethod.POST)
    public ModelAndView userDelete(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //사용자 정보 삭제
        userService.deleteUserInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/userMain.do");
        return mav;
    }
    
    /**
     * 사용자 정보 초기화 처리
     *
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userInit.do", method=RequestMethod.POST, produces="text/plain")
    public ModelAndView userInit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        
        //파라미터 세팅
        UserVO userVO = new UserVO();
        userVO.setUserId(paramVO.getUserId());
        userVO.setLastId(svo.getUser().getUserId());
        userVO.setUserPwFailCnt(0);
        userVO.setUserUseYn(Constant.GUBN_Y);
        userVO.setUserPw(UtilSecurity.encryptData(TCEProperties.getDefaultPw(), paramVO.getUserId())); //비밀번호 초기화
        
        //사용자 정보 업데이트
        userService.updateUserInfo(userVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/userMain.do");
        return mav;
    }
    
    /**
     * 사용자 정보 수정 처리
     *
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userUpdate.do", method=RequestMethod.POST)
    public ModelAndView userUpdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        paramVO.setLastId(svo.getUser().getUserId());

        String pwd = paramVO.getUserPw();
        if(StringUtils.isEmpty(pwd)){
            //화면에서 비밀번호 입력 X : 기존 비밀번호 유지
            paramVO.setUserPw(userService.selectUserInfo(paramVO).getUserPw());
        }else{
            //화면에서 비밀번호 입력 O : 입력한 값으로 암호화(SHA-256)
            paramVO.setUserPw(new String(Base64.decodeBase64(pwd.getBytes("UTF-8")),"UTF-8"));
            paramVO.setUserPw(UtilSecurity.encryptData(paramVO.getUserPw(), paramVO.getUserId()));
        }
        
        //사용자 정보 수정
        userService.updateUserInfo(paramVO);
        
        ModelAndView mav = new ModelAndView("forward:/setting/userMain.do");
        return mav;
    }
    
    /**
     * 사용자 계정 잠금 해제 처리
     * 
     * @param request
     * @param response
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting/userRelease.do", method=RequestMethod.POST)
    public ModelAndView userRelease(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("rParam") UserVO paramVO) throws Exception {
        //파라미터 출력
        TCELog.voToString(paramVO);
        
        //세션 정보에서 현재 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        
        String[] userIdList = paramVO.getUserId().split(",");
        for(String userId : userIdList){
            //파라미터 세팅
            UserVO userVO = new UserVO();
            userVO.setUserId(userId);
            userVO.setLastId(svo.getUser().getUserId());
            userVO.setUserPwFailCnt(0);
            userVO.setUserUseYn(Constant.GUBN_Y);
            userService.updateUserInfo(userVO);
        }
        
        ModelAndView mav = new ModelAndView("forward:/setting/userMain.do");
        return mav;
    }
}
