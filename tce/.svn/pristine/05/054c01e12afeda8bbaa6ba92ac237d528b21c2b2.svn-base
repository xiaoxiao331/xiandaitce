package tce.com.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tce.cmm.Constant;
import tce.setting.vo.MenuVO;
import tce.setting.vo.UserVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : SessionVO.java
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

public class SessionVO implements Serializable{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** 사용자 VO */
    private UserVO user;

    /** 메뉴 리스트 VO */
    private List<MenuVO> menuList;
    
    // 사용 가능하지 않는 메뉴도 포함
    private Map<String, Boolean> authMenuUrlMap = new HashMap<String, Boolean>();
    
    private String uriRegex;

    /**
     * @return the uriRegex
     */
    public String getUriRegex() {
        return uriRegex;
    }

    /**
     * @param authMenuList the authMenuList to set
     */
    public void setAuthMenuList(List<MenuVO> authMenuList) {
        authMenuUrlMap.clear();
        List<String> codeList = new ArrayList<String>();
        
        if(authMenuList != null) {
            for(MenuVO vo : authMenuList) {
                String ynStr = vo.getUseYn();
                if(ynStr == null) ynStr = "N";
                
                boolean yn = ynStr.equals("Y");
                authMenuUrlMap.put(vo.getMenuUrl(), yn);
                
                if(yn == true) 
                    codeList.add(vo.getMenuCode());
            }
            
            String menuListStr = codeList.toString();
            menuListStr = menuListStr.substring(1, menuListStr.length()-1).replaceAll(",", "|").replaceAll(" ", "");
            uriRegex = String.format("[/a-zA-Z0-9]*(%s)[a-zA-Z]*(%s)", menuListStr, Constant.INTERCEPTOR_VALID_URLPATTERN);
        }
    }

    /**
     * @return the menuList
     */
    public List<MenuVO> getMenuList() {
        return menuList;
    }
    /**
     * @param menuList the menuList to set
     */
    public void setMenuList(List<MenuVO> menuList) {
        this.menuList = menuList;
    }
 
    public boolean containsMenuUri(String uri) {
        return authMenuUrlMap.containsKey(uri);
    }
    
    public boolean isUsableMenuUri(String uri) {
        return authMenuUrlMap.get(uri);
    }
    /**
     * @return the user
     */
    public UserVO getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(UserVO user) {
        this.user = user;
    }
}
