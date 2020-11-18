package tce.setting.service;

import java.util.List;

import tce.setting.vo.AuthVO;

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
public interface AuthService {
    
    /**
     * 권한 관리 리스트 조회
     *
     * @param paramVO
     * @return
     */
    public List<AuthVO> selectAuthList(AuthVO paramVO) throws Exception;
    
    /**
     * 권한 관리 리스트 카운트 조회
     *
     * @param paramVO
     * @return
     */
    public int selectAuthListCnt(AuthVO paramVO) throws Exception;
    
    /**
     * 권한 관리 리스트 조회(코드성)
     *
     * @param paramVO
     * @return
     */
    public List<AuthVO> selectAuthCodeList(AuthVO paramVO) throws Exception;
    
    /**
     * 권한 관리 등록 처리
     *
     * @param paramVO
     * @return
     */
    public void insertAuthInfo(AuthVO authVO) throws Exception;
    
    /**
     * 권한 상세 조회
     *  
     * @param authVO
     * @return
     * @throws Exception
     */
    public AuthVO selectAuthInfo(AuthVO authVO) throws Exception;
    
    /**
     * 권한 관리 수정 처리
     *
     * @param paramVO
     * @return
     */
    public void updateAuthInfo(AuthVO authVO) throws Exception;
    
}
