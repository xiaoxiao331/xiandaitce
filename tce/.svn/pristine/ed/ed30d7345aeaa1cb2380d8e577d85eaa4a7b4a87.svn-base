package tce.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tce.app.service.NetworkResultGroupService;
import tce.app.service.dao.NetworkResultGroupMapper;
import tce.app.vo.NetworkResultGroup;
@Service("networkResultGroupService")
public class NetworkResultGroupServiceImpl implements NetworkResultGroupService {

	@Autowired
	private NetworkResultGroupMapper networkResultGroupMapper;

	@Override
	public List<NetworkResultGroup> queryNetworkResultGroup(NetworkResultGroup networkResultGroup) throws Exception {
		return this.networkResultGroupMapper.queryNetworkResultGroup(networkResultGroup);
	}
}
