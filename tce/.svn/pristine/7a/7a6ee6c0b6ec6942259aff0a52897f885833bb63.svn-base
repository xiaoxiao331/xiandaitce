package tce.app.web;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import able.com.util.fmt.DateUtil;
import able.com.web.HController;
import tce.app.service.MonitoringResultGroupService;
import tce.app.vo.MonitoringResultGroup;
import tce.app.vo.MonitoringResultGroupBean;

@Controller
public class MonitoringResultGroupController extends HController{
	
	@Autowired
	private MonitoringResultGroupService monitoringResultGroupService;
	
	@RequestMapping(value = "/app/monitoringMain.do", method = RequestMethod.POST)
    public ModelAndView monitoringMain(HttpServletRequest request,
    	@ModelAttribute("monitoringResultGroup") MonitoringResultGroup monitoringResultGroup,
		@ModelAttribute("monitoringResultGroupBean") MonitoringResultGroupBean monitoringResultGroupBean, Model model) throws Exception {
		if(monitoringResultGroup.getMonitoringDateSearch() == null){
			String dateStr = DateUtil.toString(new Date(),"yyyy-MM-dd HH:mm:ss",Locale.CHINA);
			monitoringResultGroup.setMonitoringDateSearch(dateStr);
		}
		if(monitoringResultGroup.isSearchEmpty()){
			monitoringResultGroupBean.setSmsSendYn("Y");
			List<MonitoringResultGroupBean> svcCdCheckErrorDatas = 
					this.monitoringResultGroupService.svcCdCheckErrorData(monitoringResultGroupBean);
			List<MonitoringResultGroup> monitoringResultGroups = 
					this.monitoringResultGroupService.queryMonitoringResultGroup(monitoringResultGroup);
			// 페이징 정보
			model.addAttribute("resultListError", svcCdCheckErrorDatas);
	        model.addAttribute("resultList", monitoringResultGroups);
		}
		model.addAttribute("queryDate",monitoringResultGroup.getMonitoringDateSearch());
		ModelAndView mav = new ModelAndView("/app/monitoringMain");
		return mav;
    }

}
