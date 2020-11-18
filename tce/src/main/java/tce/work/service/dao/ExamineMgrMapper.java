package tce.work.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import tce.work.vo.ExamineMgrVO;

/**
 * 
 * @author HSW
 *
 */
@Mapper("examineMgrMapper")
public interface ExamineMgrMapper {

	List<ExamineMgrVO> selectExamineList(ExamineMgrVO examineMgrVO) throws Exception;

	void deleteExamineMain(ExamineMgrVO examineMgrVO)throws Exception;

	
}
