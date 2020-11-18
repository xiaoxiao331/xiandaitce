package tce.setting.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;

import tce.setting.vo.MenuVO;

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
@Mapper("menuDAO")
public interface MenuDAO {
    /**
     * 메뉴 리스트 조회
     *
     * @param paramVO
     * @return
     */
    public List<MenuVO> selectMenuList(MenuVO paramVO) throws Exception;
    
    /**
     * 메뉴 리스트 카운트 조회
     *
     * @param paramVO
     * @return
     */
    public int selectMenuListCnt(MenuVO paramVO) throws Exception;
    
    /**
     * 메뉴 상위 리스트 조회
     *
     * @param paramVO
     * @return
     */
    public List<MenuVO> selectMenuUpperList(MenuVO paramVO) throws Exception;
    
    /**
     * 메뉴 등록 처리
     *
     * @param paramVO
     */
    public void insertMenuInfo(MenuVO paramVO) throws Exception;
    
    /**
     * 메뉴 상세 정보 조회
     *
     * @param paramVO
     * @return
     */
    public MenuVO selectMenuInfo(MenuVO paramVO) throws Exception;
    
    /**
     * 메뉴 관리 수정 처리
     *
     * @param paramVO
     */
    public void updateMenuInfo(MenuVO paramVO) throws Exception;
    
    /**
     * 메뉴 관리 삭제 처리
     *
     * @param menuVO
     */
    public void deleteMenuInfo(MenuVO menuVO) throws Exception;
    
    /**
     * 메뉴 유저 권한 조회
     *
     * @param menuVO
     * @return
     */
    public List<MenuVO> selectUserAuthMenu(MenuVO menuVO) throws Exception;

    
}
