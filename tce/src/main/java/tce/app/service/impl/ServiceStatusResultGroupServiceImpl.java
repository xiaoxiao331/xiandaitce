package tce.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tce.app.service.ServiceStatusResultGroupService;
import tce.app.service.dao.ServiceStatusResultGroupMapper;
import tce.app.vo.ServiceStatusResultRroup;
@Service("serviceStatusResultGroupService")
public class ServiceStatusResultGroupServiceImpl implements ServiceStatusResultGroupService {

	@Autowired
	private ServiceStatusResultGroupMapper serviceStatusResultGroupMapper;

	@Override
	public List<ServiceStatusResultRroup> queryServiceStatusResultGroup(ServiceStatusResultRroup serviceStatusResultRroup)
			throws Exception {
		return serviceStatusResultGroupMapper.queryServiceStatusResultGroup(serviceStatusResultRroup);
	}
}
