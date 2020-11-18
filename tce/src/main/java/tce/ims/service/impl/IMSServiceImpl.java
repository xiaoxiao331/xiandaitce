package tce.ims.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tce.ims.service.IMSService;
import tce.ims.service.dao.CCSTiberoMapper;
import tce.ims.thread.SubThread;
import tce.ims.vo.CarRetailInfoSearchVO;
import tce.ims.vo.IMSEntity;
import tce.ims.vo.IMSEntityMain;

@Service
public class IMSServiceImpl implements IMSService {

	
	private static final Logger log = LoggerFactory.getLogger(IMSServiceImpl.class);

	
	@Resource
	CCSTiberoMapper ccsTiberoMapper;
	
	@Override
	public List<IMSEntity> getTypeP(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeP(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeD(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeD(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeY(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeY(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeU(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeU(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeA(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeA(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeT(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeT(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeN(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeN(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeS(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeS(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeC(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeC(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeZ(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		log.info("ccsTiberoMapper.getTypeZ start..");
		imsEntitylist = ccsTiberoMapper.getTypeZ(carRetailInfoSearchVO);
		log.info("ccsTiberoMapper.getTypeZ end..");
		return imsEntitylist;
	}

	@Override
	public List<IMSEntity> getTypeW(CarRetailInfoSearchVO carRetailInfoSearchVO) {
		List<IMSEntity> imsEntitylist;
		imsEntitylist = ccsTiberoMapper.getTypeW(carRetailInfoSearchVO);
		return imsEntitylist;
	}

	@Override
	public List<IMSEntityMain> getAll() {
		List<IMSEntityMain> imsEntityMainList;
		imsEntityMainList = ccsTiberoMapper.getAll();
		return imsEntityMainList;
	}

	@Override
	public void createTempTable() {
		ccsTiberoMapper.createTempTable();
	}

	@Override
	public void dropTempTable() {
		ccsTiberoMapper.dropTempTable();
	}

	@Override
	public int insertDataToTempTable(List<IMSEntity> imsEntityList) {
		int insertCount = ccsTiberoMapper.insertDataToTempTable(imsEntityList);
		return insertCount;
	}

	@Override
	public void deleteTempTableData() {
		ccsTiberoMapper.deleteTempTableData();
		
	}

}
