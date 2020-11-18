package tce.ims.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.icu.text.SimpleDateFormat;

import tce.ims.service.CustomerInformationStatisticsService;
import tce.ims.service.IMSService;
import tce.ims.thread.GetContentTask;
import tce.ims.thread.SubThread;
import tce.ims.vo.CarRetailInfoSearchVO;
import tce.ims.vo.CarRetailInfoVO;
import tce.ims.vo.IMSEntity;
import tce.ims.vo.IMSEntityMain;

@Controller
public class CustomerInformationStatisticsController {
	@Resource(name = "customerInformationStatisticsService")
	private CustomerInformationStatisticsService customerInformationStatisticsService;

	@Resource
	IMSService imsService;

	@RequestMapping({ "/legal/legalMain.do" })
	public String selectCarRetailStatisticNew(
			@ModelAttribute("carRetailInfoSearchVO") CarRetailInfoSearchVO carRetailInfoSearchVO,
			HttpServletRequest request, ModelMap model, Locale locale) throws Exception {

		
		  request.setAttribute("csmcScn", carRetailInfoSearchVO.getCsmcScn());
		  // carRetailInfoSearchVO.setCsmcScn(csmcScn);
		  request.setAttribute("tmu", carRetailInfoSearchVO.getTmnlType());
		  if(carRetailInfoSearchVO.isSearchEmpty()){ List<CarRetailInfoVO>
		  CarRetailInfoList = this.customerInformationStatisticsService.
		  selectCarRetailInfoStatisticNew(carRetailInfoSearchVO);
		  System.out.println("打印数据-------------->"+CarRetailInfoList.get(0));
		  model.addAttribute("resultList", CarRetailInfoList); }
		 
/**
		if (carRetailInfoSearchVO.isSearchEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String[] arr = { "P", "D", "Y", "U", "A", "T", "N", "S", "C", "Z", "W" };

			Map<String, List<IMSEntity>> map = new TreeMap<>();
			List<IMSEntity> newImsEntityList = new LinkedList<>();
			List<IMSEntityMain> imsEntityMainList = new ArrayList<>();
			ExecutorService executeService = Executors.newCachedThreadPool();
			List<GetContentTask> taskList = new ArrayList<GetContentTask>();

			int subThreadNum = arr.length;
			CountDownLatch countDownLatch = new CountDownLatch(subThreadNum);

			System.out.println("子线程工作开始时间 ：" + sdf.format(new Date()));
			for (int i = 0; i < subThreadNum; i++) {
				// new SubThread(arr[i], countDownLatch,imsService).start();
				taskList.add(new GetContentTask(arr[i], imsService, carRetailInfoSearchVO));
			}
			// countDownLatch.await();
			List<Future<String>> resultList = executeService.invokeAll(taskList);
			// 这里会阻塞等待resultList获取到所有异步执行的结果才会执行
			for (Future<String> future : resultList) {
				GetContentTask.imsEntityList.stream().forEach(p -> {
					if (!newImsEntityList.contains(p)) {
						newImsEntityList.add(p);
					}
				});
			}
			System.out.println("子线程工作结束时间 ：" + sdf.format(new Date()));
			// imsService.createTempTable();
			// 插入数据前，首先删除临时表中的数据
			imsService.deleteTempTableData();
			System.out.println("临时表 IMS_ENTITY 数据删除完成!! ");
			imsService.insertDataToTempTable(newImsEntityList);
			System.out.println("向临时表 IMS_ENTITY 插入数据完成!! ");
			imsEntityMainList = imsService.getAll();
			System.out.println("查询所需数据完成!!");
			// imsService.dropTempTable();
			GetContentTask.imsEntityList.clear();
			model.addAttribute("resultList", imsEntityMainList);

		}**/
		return "/ims/legalMain";
	}
}
