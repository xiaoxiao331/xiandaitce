package tce.app.service;

import java.util.List;

import tce.app.vo.NetworkResultGroup;

public interface NetworkResultGroupService {

	List<NetworkResultGroup> queryNetworkResultGroup(NetworkResultGroup networkResultGroup) throws Exception;

}
