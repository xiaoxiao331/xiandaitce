package tce.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tce.app.service.MonitoringResultGroupService;
import tce.app.service.dao.MonitoringResultGroupMapper;
import tce.app.vo.MonitoringResultGroup;
import tce.app.vo.MonitoringResultGroupBean;

@Service("monitoringResultGroupService")
public class MonitoringResultGroupServiceImpl implements MonitoringResultGroupService{

	@Autowired
	private MonitoringResultGroupMapper monitoringResultGroupMapper;

	@Override
	public List<MonitoringResultGroup> queryMonitoringResultGroup(MonitoringResultGroup monitoringResultGroup)
			throws Exception {
		return this.monitoringResultGroupMapper.queryMonitoringResultGroup(monitoringResultGroup);
	}

	@Override
	public List<MonitoringResultGroupBean> svcCdCheckErrorData(MonitoringResultGroupBean monitoringResultGroupBean)
			throws Exception {
		return this.monitoringResultGroupMapper.svcCdCheckErrorData(monitoringResultGroupBean);
	}

}
