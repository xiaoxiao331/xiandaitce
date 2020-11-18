package tce.work.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import tce.work.vo.ExamineMgrVO;

public interface ExamineMgrService {

	List<ExamineMgrVO> selectExamineList(ExamineMgrVO examineMgrVO) throws Exception;

	void deleteExamineMain(HttpServletRequest request, ExamineMgrVO examineMgrVO)throws Exception;

}