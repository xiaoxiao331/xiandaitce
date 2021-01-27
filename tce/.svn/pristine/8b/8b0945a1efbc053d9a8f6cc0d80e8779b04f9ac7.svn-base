package tce.setting.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import tce.setting.vo.MenuVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UserService.java
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

public interface MenuService {
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
     * @param paramVO
     */
    public void deleteMenuInfo(MenuVO menuVO) throws Exception;

    /**
     * 메뉴 유저별 권한 조회
     *
     * @param menuVO
     * @return
     */
    public List<MenuVO> selectUserAuthMenu(MenuVO menuVO) throws Exception;

    /**
     * Statements
     *
     * @param menuVO
     * @return
     * @throws Exception
     */
    List<MenuVO> selectAuthMenuForSession(MenuVO menuVO) throws Exception;

    /**
     * Statements
     *
     * @param request
     * @param menuVO
     * @return
     * @throws Exception
     */
    List<MenuVO> selectUserAuthMenuLocale(HttpServletRequest request, MenuVO menuVO) throws Exception;
}
