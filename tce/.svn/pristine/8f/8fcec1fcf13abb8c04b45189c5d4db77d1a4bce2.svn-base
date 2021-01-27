package tce.work.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import tce.work.vo.ExamineMgrVO;
import tce.work.vo.WorkMgrVO;

public interface WorkMgrService {

	/**
	 * 
	 * @param workMgrEntity
	 * @return
	 */
	List<WorkMgrVO> selectWorkMainList(WorkMgrVO workMgrVO)throws Exception;

	/**
	 * 
	 * @param workMgrEntity
	 * @return
	 */
	int selectWorkListCount(WorkMgrVO workMgrVO)throws Exception;

	/**
	 * 新增
	 * @param request
	 * @param workMgrEntity
	 * @return
	 */
	public void insertWork(WorkMgrVO workMgrVO)throws Exception;
	/**
	 * 指示(修改)
	 * @param worId
	 * @return
	 */
	WorkMgrVO selectWorkList(Integer worId) throws Exception;
	/**
	 * 
	 * @param request
	 * @param examineMgrVO
	 * @throws Exception
	 */
	void updateExamine(HttpServletRequest request, ExamineMgrVO examineMgrVO) throws Exception;
	/**
	 * 
	 * @param examineMgrVO
	 * @return
	 * @throws Exception
	 */
	List<ExamineMgrVO> selectUpdateWorkMainList(ExamineMgrVO examineMgrVO)throws Exception;
	/**
	 * 
	 * @param request
	 * @param workMgrVO
	 * @throws Exception
	 */
	void updateWork(HttpServletRequest request, WorkMgrVO workMgrVO)throws Exception;

	/**
	 * 删除
	 * @param request
	 * @param workMgrVO
	 */
	void deleteWorkMain(HttpServletRequest request, WorkMgrVO workMgrVO)throws Exception;

	/**
	 * 修改主表
	 */
	void updateWorkSave(HttpServletRequest request,WorkMgrVO workMgrVO)throws Exception;

}
