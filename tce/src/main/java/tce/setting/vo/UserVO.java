package tce.setting.vo;

import java.io.Serializable;

import tce.com.vo.CommonVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UserInfoVO.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.             최초 생성
 * </pre>
 */

public class UserVO extends CommonVO implements Serializable {
    /**
     * Statements
     * (long)serialVersionUID 
     */
    private static final long serialVersionUID = 1L;
    
    public String userId;          // 사용자 ID                           
    public String authId;          // 사용자 권한 그룹ID     
    public String userGubun;       // 사용자 구분
    public String userPw;          // 사용자 비밀번호                     
    public int userPwFailCnt;      // 사용자 비밀번호 실패 건수         
    public String userLockYn;      // 사용자 로긴 LOCK여부 (Y/N)           
    public String userNm;          // 사용자 명                            
    public String userMobile;      // 사용자 휴대폰 전화번호             
    public String userPhone;       // 사용자 전화번호                     
    public String userEmlAddr;     // 사용자 이메일 주소                    
    public String userLastLgiPot;  // 사용자 최종 로그인 시점              
    public String userUseYn;       // 사용자 사용 여부                 
    public String exprMdfyPot;     // 비밀번호 유효기간                 
    public String userSid;         // 세션 ID                                
    public String acctStartDate;   // 사용자 로긴 유효기간 시작일           
    public String acctEndDate;     // 사용자 로긴 유효기간 종료일           
    
    public String authNm;          // 그룹권한 명 
    public String oldPasswd;        // 암호 변경이 기존의 암호
    
    private String code; //短信验证新增
    private String strUserHptn;//短信验证新增

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the oldPasswd
     */
    public String getOldPasswd() {
        return oldPasswd;
    }

    /**
     * @param oldPasswd the oldPasswd to set
     */
    public void setOldPasswd(String oldPasswd) {
        this.oldPasswd = oldPasswd;
    }

    /**
     * @return the userGubun
     */
    public String getUserGubun() {
        return userGubun;
    }

    /**
     * @param userGubun the userGubun to set
     */
    public void setUserGubun(String userGubun) {
        this.userGubun = userGubun;
    }

    /**
     * @return the authNm
     */
    public String getAuthNm() {
        return authNm;
    }

    /**
     * @param authNm the authNm to set
     */
    public void setAuthNm(String authNm) {
        this.authNm = authNm;
    }

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
     * @return the authId
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * @param authId the authId to set
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * @return the userPw
     */
    public String getUserPw() {
        return userPw;
    }

    /**
     * @param userPw the userPw to set
     */
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    /**
     * @return the userPwFailCnt
     */
    public int getUserPwFailCnt() {
        return userPwFailCnt;
    }

    /**
     * @param userPwFailCnt the userPwFailCnt to set
     */
    public void setUserPwFailCnt(int userPwFailCnt) {
        this.userPwFailCnt = userPwFailCnt;
    }

    /**
     * @return the userLockYn
     */
    public String getUserLockYn() {
        return userLockYn;
    }

    /**
     * @param userLockYn the userLockYn to set
     */
    public void setUserLockYn(String userLockYn) {
        this.userLockYn = userLockYn;
    }
    
    /**
     * @return the userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * @param userNm the userNm to set
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * @return the userMobile
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * @param userMobile the userMobile to set
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * @return the userPhone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone the userPhone to set
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return the userEmlAddr
     */
    public String getUserEmlAddr() {
        return userEmlAddr;
    }

    /**
     * @param userEmlAddr the userEmlAddr to set
     */
    public void setUserEmlAddr(String userEmlAddr) {
        this.userEmlAddr = userEmlAddr;
    }

    /**
     * @return the userLastLgiPot
     */
    public String getUserLastLgiPot() {
        return userLastLgiPot;
    }

    /**
     * @param userLastLgiPot the userLastLgiPot to set
     */
    public void setUserLastLgiPot(String userLastLgiPot) {
        this.userLastLgiPot = userLastLgiPot;
    }

    /**
     * @return the userUseYn
     */
    public String getUserUseYn() {
        return userUseYn;
    }

    /**
     * @param userUseYn the userUseYn to set
     */
    public void setUserUseYn(String userUseYn) {
        this.userUseYn = userUseYn;
    }

    /**
     * @return the exprMdfyPot
     */
    public String getExprMdfyPot() {
        return exprMdfyPot;
    }

    /**
     * @param exprMdfyPot the exprMdfyPot to set
     */
    public void setExprMdfyPot(String exprMdfyPot) {
        this.exprMdfyPot = exprMdfyPot;
    }

    /**
     * @return the sessionId
     */
    public String getUserSid() {
        return userSid;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    /**
     * @return the acctStartDate
     */
    public String getAcctStartDate() {
        return acctStartDate;
    }

    /**
     * @param acctStartDate the acctStartDate to set
     */
    public void setAcctStartDate(String acctStartDate) {
        this.acctStartDate = acctStartDate;
    }

    /**
     * @return the acctEndDate
     */
    public String getAcctEndDate() {
        return acctEndDate;
    }

    /**
     * @param acctEndDate the acctEndDate to set
     */
    public void setAcctEndDate(String acctEndDate) {
        this.acctEndDate = acctEndDate;
    }

    /**
     * @return the strUserHptn
     */
    public String getStrUserHptn() {
        return strUserHptn;
    }

    /**
     * @param strUserHptn the strUserHptn to set
     */
    public void setStrUserHptn(String strUserHptn) {
        this.strUserHptn = strUserHptn;
    }
}
