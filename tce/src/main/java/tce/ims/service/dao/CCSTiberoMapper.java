package tce.ims.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import able.com.mybatis.Mapper;
import tce.ims.vo.CarRetailInfoSearchVO;
import tce.ims.vo.IMSEntity;
import tce.ims.vo.IMSEntityMain;

@Mapper
public interface CCSTiberoMapper {
	
	public List<IMSEntity> getTypeP(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeD(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeY(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeU(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeA(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeT(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeN(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeS(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeC(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeZ(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntity> getTypeW(CarRetailInfoSearchVO carRetailInfoSearchVO);
	
	public List<IMSEntityMain> getAll();
	
	public void createTempTable();
	
	public int insertDataToTempTable(@Param("imsEntityList")List<IMSEntity> imsEntityList);
	
	public void dropTempTable();
	
	public void deleteTempTableData();
	
}
