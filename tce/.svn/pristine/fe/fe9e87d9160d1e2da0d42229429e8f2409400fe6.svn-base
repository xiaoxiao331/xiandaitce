package tce.work.service.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import tce.work.service.WorkMgrService;
import tce.work.service.dao.WorkMgrMapper;
import tce.work.vo.ExamineMgrVO;
import tce.work.vo.WorkMgrVO;

@Service("workMgrService")
public class WorkMgrServiceImpl implements WorkMgrService {

	@Resource(name = "workMgrMapper")
	private WorkMgrMapper workMgrMapper;
	
	@Override
	public List<WorkMgrVO> selectWorkMainList(WorkMgrVO workMgrVO) throws Exception {
		return this.workMgrMapper.selectWorkMainList(workMgrVO);
	}

	@Override
	public int selectWorkListCount(WorkMgrVO workMgrVO) throws Exception {
		return this.workMgrMapper.selectWorkListCount(workMgrVO);
	}

	@Override
	public void insertWork(WorkMgrVO workMgrVO) throws Exception{
		workMgrMapper.insertWork(workMgrVO);
	}
	/**
	 * 指示(修改)
	 */
	@Override
	public WorkMgrVO selectWorkList(Integer worId) throws Exception{
		return workMgrMapper.selectWorkList(worId);
	}

	@Override
	public void updateExamine(HttpServletRequest request, ExamineMgrVO examineMgrVO) throws Exception {
		workMgrMapper.updateExamine(examineMgrVO);
	}

	@Override
	public List<ExamineMgrVO> selectUpdateWorkMainList(ExamineMgrVO examineMgrVO) throws Exception {
		return this.workMgrMapper.selectUpdateWorkMainList(examineMgrVO);
	}

	@Override
	public void updateWork(HttpServletRequest request, WorkMgrVO workMgrVO) throws Exception {
		workMgrMapper.updateWork(workMgrVO);
	}

	@Override
	public void deleteWorkMain(HttpServletRequest request, WorkMgrVO workMgrVO) throws Exception {
		workMgrMapper.deleteWorkMain(workMgrVO);
	}

	@Override
	public void updateWorkSave(HttpServletRequest request,WorkMgrVO workMgrVO) throws Exception{
		workMgrMapper.updateWorkSave(workMgrVO);
	}

}
