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
 *  2019. 4. 23.                 	최초 생성
 * </pre>
 */

public class MenuVO extends CommonVO implements Serializable {
    
    /**
     * Statements
     * (long)serialVersionUID 
     */
    private static final long serialVersionUID = 1L;
    
    private int menuId;              // 메뉴 ID    
    private String menuCode;         // 메뉴 코드
    private int menuLevel;           // 메뉴 레벨    
    private int upperMenuId;         // 상위 메뉴 ID                     
    private String menuNm;           // 메뉴 명                         
    private String menuUrl;          // 메뉴 URL                           
    private int menuSortNo;          // 메뉴 정렬 번호                 
    private String useYn;            // 사용여부(Y/N)                    
    private String description;      // 설 명  
    private String msgId;            // 다국어 처리를 위한 MSG ID
    private String upperMenuNm;      // 상위 메뉴명

    private String userId;
    
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
     * @return the upperMenuNm
     */
    public String getUpperMenuNm() {
        return upperMenuNm;
    }
    /**
     * @param upperMenuNm the upperMenuNm to set
     */
    public void setUpperMenuNm(String upperMenuNm) {
        this.upperMenuNm = upperMenuNm;
    }
    /**
     * @return the upperMenuId
     */
    public int getUpperMenuId() {
        return upperMenuId;
    }
    /**
     * @param upperMenuId the upperMenuId to set
     */
    public void setUpperMenuId(int upperMenuId) {
        this.upperMenuId = upperMenuId;
    }
    /**
     * @return the msgId
     */
    public String getMsgId() {
        return msgId;
    }
    /**
     * @param msgId the msgId to set
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    /**
     * @return the menuId
     */
    public int getMenuId() {
        return menuId;
    }
    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    /**
     * @return the menuLevel
     */
    public int getMenuLevel() {
        return menuLevel;
    }
    /**
     * @param menuLevel the menuLevel to set
     */
    public void setMenuLevel(int menuLevel) {
        this.menuLevel = menuLevel;
    }
    /**
     * @return the menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }
    /**
     * @param menuNm the menuNm to set
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }
    /**
     * @return the menuUrl
     */
    public String getMenuUrl() {
        return menuUrl;
    }
    /**
     * @param menuUrl the menuUrl to set
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    /**
     * @return the menuSortNo
     */
    public int getMenuSortNo() {
        return menuSortNo;
    }
    /**
     * @param menuSortNo the menuSortNo to set
     */
    public void setMenuSortNo(int menuSortNo) {
        this.menuSortNo = menuSortNo;
    }
    /**
     * @return the useYn
     */
    public String getUseYn() {
        return useYn;
    }
    /**
     * @param useYn the useYn to set
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }
    /**
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
