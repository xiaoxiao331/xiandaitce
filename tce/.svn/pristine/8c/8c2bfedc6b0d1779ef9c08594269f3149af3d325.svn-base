package tce.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tce.setting.service.UserService;
import tce.setting.service.dao.UserDAO;
import tce.setting.vo.UserVO;

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
@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource(name = "userDAO")
    private UserDAO userDAO;
    
    /**
     * 사용자 리스트 조회
     *
     * @param paramVO
     * @return Total Count
     */
    @Override
    public List<UserVO> selectUserList(UserVO paramVO) throws Exception{
        return userDAO.selectUserList(paramVO);
    }
    
    /**
     * 사용자 리스트 조회 Count
     *
     * @param paramVO
     * @return Total Count
     */
    @Override
    public int selectUserListCnt(UserVO paramVO) throws Exception {
        return userDAO.selectUserInfoCnt(paramVO);
    }
    
    /**
     * 사용자 등록
     * 
     * @param userVO
     * @throws Exception
     */
    @Override
    public void insertUserInfo(UserVO paramVO) throws Exception {
        userDAO.insertUserInfo(paramVO);
    }

    /**
     * 사용자 정보 조회
     * 
     * @param userVO
     * @return
     * @throws Exception
     */
    @Override
    public UserVO selectUserInfo(UserVO paramVO) throws Exception {
        return userDAO.selectUserInfo(paramVO);
    }
    
    /**
     * 사용자 정보 삭제 처리
     * 
     * @param userVO
     * @return
     * @throws Exception
     */
    @Override
    public void deleteUserInfo(UserVO paramVO) throws Exception {
        userDAO.deleteUserInfo(paramVO);
    }
    
    /**
     * 사용자 정보 수정 처리
     *
     * @param paramVO
     */
    @Override
    public void updateUserInfo(UserVO userVO) throws Exception {
        userDAO.updateUserInfo(userVO);
    }

    /**
     * 사용자 비밀번호 수정
     *  
     * @param userVO
     * @throws Exception
     */
    @Override
    public void updateUserPasswd(UserVO userVO) throws Exception {
        userDAO.updateUserPasswd(userVO);
    }

    /**
     * 사용자 세션ID 수정
     */
    @Override
    public void updateSession(UserVO userVO) throws Exception {
        userDAO.updateSession(userVO);
    }
}
