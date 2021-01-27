package tce.setting.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import tce.setting.vo.UserVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UserDAO.java
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
@Mapper("userDAO")
public interface UserDAO {
    /**
     * 사용자 정보 리스트 조회
     * 
     * @param userVO
     * @return
     * @throws Exception
     */
    public List<UserVO> selectUserList(UserVO userVO) throws Exception;
    
    /**
     * 사용자 정보 리스트 Count
     *
     * @param userVO
     * @return
     */
    public int selectUserInfoCnt(UserVO userVO) throws Exception;
    
    /**
     * 사용자 등록
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
    
    public void updateUserInfo(UserVO userVO) throws Exception;
    
    public void updateUserPasswd(UserVO userVO) throws Exception;
    
    public void resetUserLock(UserVO userVO) throws Exception;
    
    public void resetUserInfo(UserVO userVO);
    
    public void resetUserList(UserVO userVO);

    public int selectUserCount();

    /**
     * 사용자 세션ID 수정
     *
     * @param userVO
     */
    public void updateSession(UserVO userVO);

}
