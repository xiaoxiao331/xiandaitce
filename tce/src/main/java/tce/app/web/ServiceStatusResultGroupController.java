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

import able.com.util.fmt.DateUtil;
import able.com.web.HController;
import tce.app.service.ServiceStatusResultGroupService;
import tce.app.vo.ServiceStatusResultRroup;

@Controller
public class ServiceStatusResultGroupController extends HController{
	
	@Autowired
	private ServiceStatusResultGroupService serviceStatusResultGroupService;
	
	@RequestMapping(value = "/app/serverstatusMain.do")
	public String serviceMain(HttpServletRequest request,
			@ModelAttribute ServiceStatusResultRroup serviceStatusResultRroup, Model model) throws Exception {
		if(serviceStatusResultRroup.getServiceStatusDateSearch() == null){
			String dateStr = DateUtil.toString(new Date(),"yyyy-MM-dd HH:mm:ss",Locale.CHINA);
			serviceStatusResultRroup.setServiceStatusDateSearch(dateStr);
		}
		//serviceStatusResultRroup.setServiceStatusFieidSearch("CCW");
		if(serviceStatusResultRroup.isSearchEmpty()){
			//TODO
			List<ServiceStatusResultRroup> serverInfos = 
					this.serviceStatusResultGroupService.queryServiceStatusResultGroup(serviceStatusResultRroup);
				model.addAttribute("serverList",serverInfos);
		}
		model.addAttribute("queryDate", serviceStatusResultRroup.getServiceStatusDateSearch());
		return "/app/serverstatusMain";
	}

}
