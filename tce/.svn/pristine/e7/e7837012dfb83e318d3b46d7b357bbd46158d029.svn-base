package tce.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tce.setting.service.AuthMenuMapService;
import tce.setting.service.dao.AuthMenuMapDAO;
import tce.setting.vo.AuthMenuMapVO;

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
@Service("authMenuMapService")
public class AuthMenuServiceImpl implements AuthMenuMapService {
    @Resource(name = "authMenuMapDAO")
    private AuthMenuMapDAO authMenuMapDAO;
    
    /**
     * 메뉴 권한 리스트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public List<AuthMenuMapVO> selectAuthMenuList(AuthMenuMapVO paramVO) throws Exception {
        return authMenuMapDAO.selectAuthMenuList(paramVO);
    }
    
    /**
     * 메뉴 권한 리스트 카운트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public int selectAuthMenuListCnt(AuthMenuMapVO paramVO) throws Exception {
        return authMenuMapDAO.selectAuthMenuListCnt(paramVO);
    }
    
    /**
     * 메뉴 권한 수정 처리
     *
     * @param paramVO
     */
    @Override
    public void updateAuthMenuInfo(AuthMenuMapVO authMenuMapVO) throws Exception {
        String[] list = authMenuMapVO.getCheckList().split(",");
        
        authMenuMapVO.setMapIdList(list);
        authMenuMapVO.setUseYn("Y");
        authMenuMapDAO.updateAuthMenuInfo(authMenuMapVO);

        list = authMenuMapVO.getUnCheckList().split(",");
        authMenuMapVO.setMapIdList(list);
        authMenuMapVO.setUseYn("N");
        authMenuMapDAO.updateAuthMenuInfo(authMenuMapVO);
    }
}
