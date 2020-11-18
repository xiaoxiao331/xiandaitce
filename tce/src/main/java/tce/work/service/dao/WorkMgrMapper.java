package tce.work.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import tce.work.vo.ExamineMgrVO;
import tce.work.vo.WorkMgrVO;
/**
 * 
 * @author HSW
 *
 */
@Mapper("workMgrMapper")
public interface WorkMgrMapper {

	Integer getWorkID() throws Exception;

	List<WorkMgrVO> selectWorkMainList(WorkMgrVO workMgrVO)throws Exception;

	int selectWorkListCount(WorkMgrVO workMgrVO) throws Exception;

	void insertWork(WorkMgrVO workMgrVO)throws Exception;
	/**
	 * 详情
	 * @param worId
	 * @return
	 */
	WorkMgrVO selectWorkList(Integer worId)throws Exception;

	void updateExamine(ExamineMgrVO examineMgrVO) throws Exception;

	List<ExamineMgrVO> selectUpdateWorkMainList(ExamineMgrVO examineMgrVO)throws Exception;

	void updateWork(WorkMgrVO workMgrVO)throws Exception;

	void deleteWorkMain(WorkMgrVO workMgrVO)throws Exception;

	void updateWorkSave(WorkMgrVO workMgrVO)throws Exception;

}
