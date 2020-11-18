package tce.com.vo;

import java.io.Serializable;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : LoginManagerVO.java
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

public class LoginManagerVO implements Serializable{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private String userId;      // 사용자ID     : varchar2(50)
    private String userSid;     // 사용자SID    : varchar2(100)
    private String userCurrSid; // 현재사용자SID
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the userSid
     */
    public String getUserSid() {
        return userSid;
    }
    /**
     * @param userSid the userSid to set
     */
    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }
    /**
     * @return the userCurrSid
     */
    public String getUserCurrSid() {
        return userCurrSid;
    }
    /**
     * @param userCurrSid the userCurrSid to set
     */
    public void setUserCurrSid(String userCurrSid) {
        this.userCurrSid = userCurrSid;
    }
}
