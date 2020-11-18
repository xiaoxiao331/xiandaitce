package tce.com.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;

import tce.setting.vo.AuthVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : DBSetupDAO.java
 * @Description : 테이블, Sequence 초기 생성 및 초기 데이터 저장
 * @author LMC
 * @since 2019. 5. 22.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 22.     LMC     	최초 생성
 * </pre>
 */
@Mapper("dbSetupDAO")
public interface DBSetupDAO  {
    
    /**
     * DB 생성
     *  
     * @throws Exception
     */
    public void setupDatabase(String query) throws Exception;
    
    /**
     * 초기 테이블 생성 여부를 확인하기 위한 메소드
     *
     * @throws Exception
     */
    public List<AuthVO> checkDatabase() throws Exception;
}
