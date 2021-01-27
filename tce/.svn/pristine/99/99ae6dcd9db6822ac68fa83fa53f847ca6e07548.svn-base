package tce.work.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tce.cmm.Constant;
import tce.com.vo.SessionVO;
import tce.setting.service.UserService;
import tce.setting.vo.UserVO;
import tce.work.service.ExamineMgrService;
import tce.work.service.WorkMgrService;
import tce.work.vo.ExamineMgrVO;
import tce.work.vo.WorkMgrVO;
import able.com.web.HController;

@Controller
public class WorkMgrController extends HController {

    @Autowired
    private WorkMgrService workMgrService;
    
    /** 사용자 Service */
    @Autowired
    private UserService userService;
    
    @Autowired
    private ExamineMgrService examineMgrService;
    
    @RequestMapping(value = "/work/workMain.do")
    public String workMain(HttpServletRequest request, @ModelAttribute("workMgrVO") WorkMgrVO workMgrVO, Model model) throws Exception {
        //세션 정보에서 현재 접속 사용자 ID를 가져온다.
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        
        if(workMgrVO.getCurrPage() == null || workMgrVO.getCurrPage().equals("")){
        	workMgrVO.setCurrPage("1");
        }           
        int currPage = Integer.parseInt(workMgrVO.getCurrPage());
        int pageUnit = propertiesService.getInt("pageUnit");
        
        int offset = (currPage - 1)*pageUnit + 1;
        int limit = currPage*pageUnit;
        
        workMgrVO.setOffset(offset); 
        workMgrVO.setLimit(limit);
        
        List<WorkMgrVO> workMainList = this.workMgrService.selectWorkMainList(workMgrVO);

       // 페이징 정보
        int listCount = workMgrService.selectWorkListCount(workMgrVO);
        model.addAttribute("resultList", workMainList);
        model.addAttribute("currPage", Integer.parseInt(workMgrVO.getCurrPage()));
        model.addAttribute("totalRecordCount",listCount);
        model.addAttribute("userId",svo.getUser().getUserId());
        return "/work/workMain";
    }

    /**
     * 跳转页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/pop/vehicleCodePopup.do")
    public String vehicleCodePopup(HttpServletRequest request, HttpServletResponse response
    		, @ModelAttribute("userVO") UserVO userVO, Model model) throws Exception {
    	SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
    	model.addAttribute("userNameId",svo.getUser().getUserId());
    	List<UserVO> userList = userService.selectUserList(userVO);
    	for(int i=userList.size()-1;i>=0;i--){
    		UserVO result = userList.get(i);
            if (result.getUserId().equals(svo.getUser().getUserId())){
            	userList.remove(result);
            }
        }
    	model.addAttribute("resultcdList", userList);
    	return "popup/work/vehicleCodePopup";
    } 
    /**
     * 跳页面
     * 
     */
    @RequestMapping(value="/work/workRegistForm.do", method = RequestMethod.POST)
    public String notiRegistForm(HttpServletRequest request, @ModelAttribute WorkMgrVO workMgrVO, Model model) throws Exception {  
        return "/work/workRegist";
    }
    /**
     * 查询担当者
     * @param request
     * @param response
     * @param userVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/pop/selectVehicleCdList.do", method=RequestMethod.POST)
    @ResponseBody
    public ModelAndView selectVehicleCdList(HttpServletRequest request, HttpServletResponse response
                            , @ModelAttribute("userVO") UserVO userVO, Model model) throws Exception {
    	SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
    	List<UserVO> userList = userService.selectUserList(userVO);
        Map<String, Object> retVal = new HashMap<String, Object>();
        retVal.put("resultCdList", userList);
        if(retVal.size() > 0) retVal.put("retStatus", true);
        for(int i=userList.size()-1;i>=0;i--){
    		UserVO result = userList.get(i);
            if (result.getUserId().equals(svo.getUser().getUserId())){
            	userList.remove(result);
            }
        } 
        ModelAndView modelAndView = new ModelAndView("jsonView", retVal);  
       
        return modelAndView;
    }
    /**
     * 新增
     * @param workMgrEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/workRegist.do", method = RequestMethod.POST)
    public ModelAndView workRegist(HttpServletRequest request,WorkMgrVO workMgrVO, Model model) throws Exception {
    	SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
    	workMgrVO.setWorCreauser(svo.getUser().getUserId());
        workMgrService.insertWork(workMgrVO);
        ModelAndView mav = new ModelAndView("redirect:/work/workMain.do");
        return mav;
    }  
    
    @RequestMapping(value="/work/workDetailForm.do", method = RequestMethod.POST)
    public String workDetailForm(HttpServletRequest request, @ModelAttribute WorkMgrVO workMgrVO
    		,@ModelAttribute ExamineMgrVO examineMgrVO, Model model) throws Exception {  
    	SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        Integer worId = workMgrVO.getWorId(); 
        if(worId != null){
        	model.addAttribute("workMgrVO", selectWorkList(worId));
        }
        List<ExamineMgrVO> resultExanList = this.examineMgrService.selectExamineList(examineMgrVO);
        model.addAttribute("resultExamineList", resultExanList);
        model.addAttribute("userAuthid", svo.getUser().getAuthId());
        model.addAttribute("userId", svo.getUser().userId);
        return "/work/workUpdate";  
    } 
    /**
     * 指示(修改)
     * @param worId
     * @return
     * @throws Exception
     */
    private WorkMgrVO selectWorkList(Integer worId) throws Exception {
        return workMgrService.selectWorkList(worId);
    } 
    /**
     * 修改
     * @param request
     * @param workMgrVO
     * @param examineMgrVO
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/workUpdate.do", method = RequestMethod.POST)
    public String workUpdate(HttpServletRequest request, 
    		@ModelAttribute("workMgrVO") WorkMgrVO workMgrVO,@ModelAttribute("examineMgrVO") ExamineMgrVO examineMgrVO, HttpServletResponse response, Model model) throws Exception {
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        examineMgrVO.setExaId(workMgrVO.getWorId());
        examineMgrVO.setExaTakeuser(svo.getUser().userId);
        workMgrService.updateExamine(request, examineMgrVO);
        workMgrVO.setWorResult(examineMgrVO.getExaResult());
        workMgrVO.setWorUpdateDate(examineMgrVO.getExaDisposeData());
        workMgrService.updateWork(request, workMgrVO);
        
        List<ExamineMgrVO> resultExanList = this.examineMgrService.selectExamineList(examineMgrVO);
        model.addAttribute("resultExamineList", resultExanList);
        Integer worId = workMgrVO.getWorId(); 
        if(worId != null){
        	model.addAttribute("workMgrVO", selectWorkList(worId));
        }
        return "/work/workUpdate";
    }
    /**
     * 修改后查询
     * @param request
     * @param examineMgrVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/workUpdateMain.do")
    public String workUpdateMain(HttpServletRequest request, @ModelAttribute("examineMgrVO") ExamineMgrVO examineMgrVO, Model model) throws Exception {
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        List<ExamineMgrVO> workUpdateMainList = workMgrService.selectUpdateWorkMainList(examineMgrVO);
        model.addAttribute("resultList", workUpdateMainList);
        model.addAttribute("userId",svo.getUser().getUserId());
        return "/work/workUpMain";
    }
    /**
     * 删除
     * @param request
     * @param workMgrVO
     * @param examineMgrVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/work/workDelete.do")
    public ModelAndView workDeleteMain(HttpServletRequest request, WorkMgrVO workMgrVO,@ModelAttribute("examineMgrVO") ExamineMgrVO examineMgrVO, Model model) throws Exception {
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        workMgrService.deleteWorkMain(request, workMgrVO);
        examineMgrVO.setExaId(workMgrVO.getWorId());
        examineMgrService.deleteExamineMain(request, examineMgrVO);
        ModelAndView mav = new ModelAndView("forward:/work/workMain.do");
        return mav;
    }
    /**
     * 修改主表内容
     * @param request
     * @param workMgrVO
     * @param examineMgrVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/workor/workUpdateSave.do", method = RequestMethod.POST)
    public ModelAndView workorUpdateSave(HttpServletRequest request, @ModelAttribute("workMgrVO") WorkMgrVO workMgrVO,@ModelAttribute("examineMgrVO") ExamineMgrVO examineMgrVO, Model model) throws Exception {  
    	SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
    	workMgrService.updateWorkSave(request,workMgrVO);
    	ModelAndView mav = new ModelAndView("redirect:/work/workMain.do");
        return mav; 
    } 
}
