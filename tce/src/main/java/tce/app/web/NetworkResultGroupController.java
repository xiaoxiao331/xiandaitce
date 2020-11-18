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
import tce.app.service.NetworkResultGroupService;
import tce.app.vo.NetworkResultGroup;

@Controller
public class NetworkResultGroupController extends HController{

	@Autowired
	private NetworkResultGroupService networkResultGroupService;
	
	@RequestMapping(value = "/app/networkMain.do")
	public String networkMain(HttpServletRequest request,@ModelAttribute NetworkResultGroup networkResultGroup,
			Model model) throws Exception {
		if(networkResultGroup.getNetworkDateSearch() == null){
			String dateStr = DateUtil.toString(new Date(),"yyyy-MM-dd HH:mm:ss",Locale.CHINA);
			networkResultGroup.setNetworkDateSearch(dateStr);
		}
		model.addAttribute("queryDate", networkResultGroup.getNetworkDateSearch());
		return "/app/networkMain";
	}
	
	@RequestMapping(value = "/app/networkMainList.do")
	public String networkMainList(HttpServletRequest request,@ModelAttribute NetworkResultGroup networkResultGroup,
			Model model) throws Exception {
		List<NetworkResultGroup> networkResultGroups = 
				this.networkResultGroupService.queryNetworkResultGroup(networkResultGroup);
		model.addAttribute("resultListNet", networkResultGroups);
		return "/app/networkMain";
	}
}
