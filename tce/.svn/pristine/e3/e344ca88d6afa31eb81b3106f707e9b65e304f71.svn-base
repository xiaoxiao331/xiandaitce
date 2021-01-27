package tce.com.service.dao;
import able.com.mybatis.Mapper;
import tce.com.vo.LoginManagerVO;

/**
 * <pre>
 * 로그인 관리 DAO
 * </pre>
 *
 * @ClassName   : LoginManagerDAO.java
 * @Description : 로그인 관리 DAO
 * @author JY
 * @since 2015. 12. 3.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015. 12. 3.        JY                  최초 생성
 * </pre>
 */
@Mapper("loginManagerDAO")
public interface LoginManagerDAO {
    
    /**
     * Select
     * 
     * @param loginManagerVO
     * @return LoginManagerVO
     * @throws Exception Exception
     */
    public LoginManagerVO selectSession(LoginManagerVO loginManagerVO) throws Exception;
    
    /**
     * Update
     * 
     * @param loginManagerVO
     * @throws Exception Exception
     */
    public void updateSession(LoginManagerVO loginManagerVO)throws Exception;
    
    /**
     * checkSession
     *
     * @param loginManagerVO
     * @return LoginManagerVO
     * @throws Exception Exception
     */
    public LoginManagerVO checkSession(LoginManagerVO loginManagerVO) throws Exception;
}