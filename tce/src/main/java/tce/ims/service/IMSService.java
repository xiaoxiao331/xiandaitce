package tce.ims.service;

import java.util.List;

import tce.ims.vo.CarRetailInfoSearchVO;
import tce.ims.vo.IMSEntity;
import tce.ims.vo.IMSEntityMain;


public interface IMSService {
	
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
	
	public int insertDataToTempTable(List<IMSEntity> list);
	
	public void dropTempTable();

	public void deleteTempTableData();
}
