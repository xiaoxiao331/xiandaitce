package tce.com.service.dao;
import java.util.List;

import able.com.mybatis.Mapper;

import tce.com.vo.CommonVO;

/**
 * <pre>
 * 공통 DAO
 * </pre>
 *
 * @ClassName   : CommonDAO.java
 * @Description : 공통 DAO
 * @author 홍성욱
 * @since 2019. 5. 22.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 22.      홍성욱              최초 생성
 * </pre>
 */
@Mapper("commonDAO")
public interface CommonDAO {
    /**
     * 사용자 목록 조회 (팝업)
     * 
     * @param commonVO
     * @return CommonVO
     * @throws Exception Exception
     */
    public List<CommonVO> custSearchList(CommonVO commonVO) throws Exception;
}