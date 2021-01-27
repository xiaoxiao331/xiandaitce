package tce.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import tce.cmm.util.UtilCommon;
import tce.setting.service.MenuService;
import tce.setting.service.dao.AuthMenuMapDAO;
import tce.setting.service.dao.MenuDAO;
import tce.setting.vo.AuthMenuMapVO;
import tce.setting.vo.MenuVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : MenuServiceImpl.java
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
@Service("menuService")
public class MenuServiceImpl implements MenuService{
    @Resource(name = "menuDAO")
    private MenuDAO menuDAO;
    
    @Resource(name = "authMenuMapDAO")
    private AuthMenuMapDAO authMenuMapDAO;
    
    /**
     * 메뉴 리스트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public List<MenuVO> selectMenuList(MenuVO paramVO) throws Exception {
        return menuDAO.selectMenuList(paramVO);
    }

    /**
     * 메뉴 리스트 카운트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public int selectMenuListCnt(MenuVO paramVO) throws Exception {
        return menuDAO.selectMenuListCnt(paramVO);
    }
    
    /**
     * 메뉴 상위 리스트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public List<MenuVO> selectMenuUpperList(MenuVO paramVO) throws Exception {
        return menuDAO.selectMenuUpperList(paramVO);
    }
    
    /**
     * 메뉴 등록 처리
     *
     * @param paramVO
     */
    @Override
    public void insertMenuInfo(MenuVO paramVO) throws Exception {
        menuDAO.insertMenuInfo(paramVO);
        
        AuthMenuMapVO mapVO = new AuthMenuMapVO();
        mapVO.setUpperMenuId(paramVO.getUpperMenuId());
        mapVO.setMenuId(paramVO.getMenuId());
        mapVO.setFrstId(paramVO.getFrstId());
        
        authMenuMapDAO.insertAuthMenuMapFromMenu(mapVO);
    }
    
    /**
     * 메뉴 상세 정보 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public MenuVO selectMenuInfo(MenuVO paramVO) throws Exception {
        return menuDAO.selectMenuInfo(paramVO);
    }
    
    /**
     * 메뉴 관리 수정 처리
     *
     * @param paramVO
     */
    @Override
    public void updateMenuInfo(MenuVO paramVO) throws Exception {
        menuDAO.updateMenuInfo(paramVO);
    }

    /**
     * 메뉴 관리 삭제 처리
     *
     * @param paramVO
     */
    @Override
    public void deleteMenuInfo(MenuVO menuVO) throws Exception {
        menuDAO.deleteMenuInfo(menuVO);
    }

    /**
     * 메뉴 유저별 권한 조회
     *
     * @param menuVO
     * @return
     */
    @Override
    public List<MenuVO> selectUserAuthMenuLocale(HttpServletRequest request, MenuVO menuVO) throws Exception {
        
        List<MenuVO> list = menuDAO.selectUserAuthMenu(menuVO);
        
        for(MenuVO mvo : list) {
            String str = UtilCommon.toLocale(request, mvo.getMsgId(), mvo.getMenuNm());
            mvo.setMenuNm(str);
        }
        // 마지막 메뉴 리스트를 지정하기 위해 추가
        MenuVO vo = new MenuVO();
        vo.setUpperMenuId(-2);
        list.add(vo);
        return list;
    }

    @Override
    public List<MenuVO> selectUserAuthMenu(MenuVO menuVO) throws Exception {
        List<MenuVO> list = menuDAO.selectUserAuthMenu(menuVO);
        // 마지막 메뉴 리스트를 지정하기 위해 추가
        MenuVO vo = new MenuVO();
        vo.setUpperMenuId(-2);
        list.add(vo);
        return list;
    }

    /**
     * 등록된 메뉴 전체 목록 조회(사용가능 유무 포함)
     *
     * @param menuVO
     * @return
     */
    @Override
    public List<MenuVO> selectAuthMenuForSession(MenuVO menuVO) throws Exception {
        List<MenuVO> list = menuDAO.selectUserAuthMenu(menuVO);
        return list;
    }
}
