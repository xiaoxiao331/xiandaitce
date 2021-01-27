package tce.com.service.impl;

import javax.annotation.Resource;

import able.com.service.HService;
import org.springframework.stereotype.Service;
import tce.com.service.LoginManagerService;
import tce.com.service.dao.LoginManagerDAO;
import tce.com.vo.LoginManagerVO;


/**
 * <pre>
 * 로그인 관리 ServiceImpl
 * </pre>
 *
 * @ClassName   : LoginManagerServiceImpl.java
 * @Description : 로그인 관리 ServiceImpl
 * @author JY
 * @since 2015. 12. 2.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015. 12. 2.        JY                  최초 생성
 * </pre>
 */
@Service("loginManagerService")
public class LoginManagerServiceImpl extends HService implements LoginManagerService{
    
    @Resource(name = "loginManagerDAO")
    private LoginManagerDAO loginManagerDAO;
    
    /*
     * @see aes.comm.service.LoginManagerService#getSession(aes.comm.vo.LoginManagerVO)
     */
    @Override
    public LoginManagerVO getSession(LoginManagerVO loginManagerVO) throws Exception {
        return loginManagerDAO.selectSession(loginManagerVO);
    }

    /*
     * @see aes.comm.service.LoginManagerService#setSession(aes.comm.vo.LoginManagerVO)
     */
    @Override
    public void setSession(LoginManagerVO loginManagerVO) throws Exception {
        loginManagerDAO.updateSession(loginManagerVO);
    }
    
    /*
     * @see aes.comm.service.LoginManagerService#checkSession(aes.comm.vo.LoginManagerVO)
     */
    @Override
    public LoginManagerVO checkSession(LoginManagerVO loginManagerVO) throws Exception {
        return loginManagerDAO.checkSession(loginManagerVO);
    }
}