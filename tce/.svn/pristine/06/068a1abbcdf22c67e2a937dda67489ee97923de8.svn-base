package tce.work.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import tce.work.service.ExamineMgrService;
import tce.work.service.dao.ExamineMgrMapper;
import tce.work.vo.ExamineMgrVO;

@Service("examineMgrService")
public class ExamineMgrServiceImpl implements ExamineMgrService {

	@Resource(name = "examineMgrMapper")
	private ExamineMgrMapper examineMgrMapper;

	@Override
	public List<ExamineMgrVO> selectExamineList(ExamineMgrVO examineMgrVO) throws Exception{
		return this.examineMgrMapper.selectExamineList(examineMgrVO);
	}

	@Override
	public void deleteExamineMain(HttpServletRequest request, ExamineMgrVO examineMgrVO) throws Exception {
		examineMgrMapper.deleteExamineMain(examineMgrVO);
	}
	
	

}
