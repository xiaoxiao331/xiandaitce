package tce.app.service;

import java.util.List;

import tce.app.vo.MonitoringResultGroup;
import tce.app.vo.MonitoringResultGroupBean;

public interface MonitoringResultGroupService {

	List<MonitoringResultGroup> queryMonitoringResultGroup(MonitoringResultGroup monitoringResultGroup) throws Exception;

	List<MonitoringResultGroupBean> svcCdCheckErrorData(MonitoringResultGroupBean monitoringResultGroupBean)throws Exception;


}
