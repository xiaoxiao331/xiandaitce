package tce.setting.service;

import java.util.List;

import tce.setting.vo.UserVO;

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

public interface UserService {
    /**
     * 사용자 리스트 조회
     *
     * @param paramVO
     * @return Total Count
     */
    public List<UserVO> selectUserList(UserVO paramVO) throws Exception;
    
    /**
     * 사용자 리스트 조회 Count
     *
     * @param paramVO
     * @return Total Count
     */
    public int selectUserListCnt(UserVO paramVO) throws Exception;
    
    /**
     * 사용자 등록
     * 
     * @param userVO
     * @throws Exception
     */
    public void insertUserInfo(UserVO userVO) throws Exception;
    
    /**
     * 사용자 정보 조회
     * 
     * @param userVO
     * @return
     * @throws Exception
     */
    public UserVO selectUserInfo(UserVO userVO) throws Exception;
    
    /**
     * 사용자 정보 삭제 처리
     *
     * @param paramVO
     */
    public void deleteUserInfo(UserVO paramVO) throws Exception;
    
    /**
     * 사용자 정보 수정 처리
     *
     * @param paramVO
     */
    public void updateUserInfo(UserVO userVO) throws Exception;
    
    /**
     * 사용자 비밀번호 수정
     *  
     * @param userVO
     * @throws Exception
     */
    public void updateUserPasswd(UserVO userVO) throws Exception;
    
    /**
     * 사용자 세션ID 수정
     *  
     * @param userVO
     * @throws Exception
     */
    public void updateSession(UserVO userVO) throws Exception;

}
