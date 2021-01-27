package tce.setting.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;

import tce.setting.vo.AuthMenuMapVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : MenuDAO.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.     LMC     	최초 생성
 * </pre>
 */
@Mapper("authMenuMapDAO")
public interface AuthMenuMapDAO {
    /**
     * 메뉴 권한 리스트 조회
     *
     * @param paramVO
     * @return
     */
    public List<AuthMenuMapVO> selectAuthMenuList(AuthMenuMapVO paramVO) throws Exception;
    
    /**
     * 메뉴 권한 리스트 카운트 조회
     *
     * @param paramVO
     * @return
     */
    public int selectAuthMenuListCnt(AuthMenuMapVO paramVO) throws Exception;
    
    /**
     * 메뉴 권한 수정 처리
     *
     * @param paramVO
     */
    public void updateAuthMenuInfo(AuthMenuMapVO paramVO) throws Exception;
    
    /**
     * 메뉴 권한 매핑 처리(From 권한)
     *  
     * @param authMenuMapVO
     * @throws Exception
     */
    public void insertAuthMenuMapFromAuth(AuthMenuMapVO authMenuMapVO) throws Exception;
    
    /**
     * 메뉴 권한 매핑 처리(From Menu)
     *  
     * @param authMenuMapVO
     * @throws Exception
     */
    public void insertAuthMenuMapFromMenu(AuthMenuMapVO authMenuMapVO) throws Exception;
}
