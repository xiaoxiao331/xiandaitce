package tce.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tce.setting.service.AuthService;
import tce.setting.service.dao.AuthDAO;
import tce.setting.service.dao.AuthMenuMapDAO;
import tce.setting.service.dao.UserDAO;
import tce.setting.vo.AuthMenuMapVO;
import tce.setting.vo.AuthVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UserServiceImpl.java
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
@Service("authService")
public class AuthServiceImpl implements AuthService{
    /** 권한메뉴 DAO */
    @Resource(name = "authMenuMapDAO")
    private AuthMenuMapDAO authMenuMapDAO;
    
    /** 권한 DAO */
    @Resource(name = "authDAO")
    private AuthDAO authDAO;
    
    /**
     * 권한 관리 리스트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public List<AuthVO> selectAuthList(AuthVO paramVO) throws Exception {
        return authDAO.selectAuthList(paramVO);
    }
    
    /**
     * 권한 관리 리스트 카운트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public int selectAuthListCnt(AuthVO paramVO) throws Exception{
        return authDAO.selectAuthListCnt(paramVO);
    }
    
    /**
     * 권한 관리 리스트 조회(코드성)
     *
     * @param paramVO
     * @return
     */
    @Override
    public List<AuthVO> selectAuthCodeList(AuthVO paramVO) throws Exception {
        return authDAO.selectAuthCodeList(paramVO);
    }
    
    /**
     * 권한 관리 등록 처리
     *
     * @param paramVO
     * @return
     */
    @Override
    public void insertAuthInfo(AuthVO authVO) throws Exception {
        authDAO.insertAuthInfo(authVO);
        
        // 그룹 권한이 추가될 때 기 저장된 메뉴를 추가된 그룹권한에 할당되도록 저장 처리 
        AuthMenuMapVO mapVO = new AuthMenuMapVO();
        mapVO.setAuthId(authVO.getAuthId());
        mapVO.setFrstId(authVO.getFrstId());
        
        authMenuMapDAO.insertAuthMenuMapFromAuth(mapVO);
    }

    /**
     * 권한 상세 조회
     *  
     * @param authVO
     * @return
     * @throws Exception
     */
    @Override
    public AuthVO selectAuthInfo(AuthVO authVO) throws Exception {
        return authDAO.selectAuthInfo(authVO);
    }

    /**
     * 권한 관리 수정 처리
     *
     * @param paramVO
     * @return
     */
    @Override
    public void updateAuthInfo(AuthVO authVO) throws Exception {
        authDAO.updateAuthInfo(authVO);
    }
}
