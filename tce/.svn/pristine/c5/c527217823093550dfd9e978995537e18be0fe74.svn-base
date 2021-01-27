package tce.ims.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tce.ims.service.CustomerInformationStatisticsService;
import tce.ims.service.dao.CustomerInformationStatisticsDAO;
import tce.ims.vo.CarRetailInfoSearchVO;
import tce.ims.vo.CarRetailInfoVO;

@Service("customerInformationStatisticsService")
public class CustomerInformationStatisticsServiceImpl  implements CustomerInformationStatisticsService
{

	@Resource(name = "customerInformationStatisticsDAO")
    private CustomerInformationStatisticsDAO customerInformationStatisticsDAO;
    
    
	public List<CarRetailInfoVO> selectCarRetailInfoStatisticNew(
			CarRetailInfoSearchVO carRetailInfoSearchVO) {
		
		return customerInformationStatisticsDAO.selectCarRetailInfoStatisticNew(carRetailInfoSearchVO);
	}
	
	

}
