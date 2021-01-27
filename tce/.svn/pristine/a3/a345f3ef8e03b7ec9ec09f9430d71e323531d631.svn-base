package tce.cmm.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import tce.cmm.Constant;
import tce.cmm.exception.ValidationException;
import tce.com.vo.SessionVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilAuthority.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 6. 4.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 6. 4.     LMC     	최초 생성
 * </pre>
 */

public class URIValidator {
    
    private static Set<String> SKIP_LIST = null;
    
    static {
        if(SKIP_LIST == null) {
            SKIP_LIST = new HashSet<String>();
            for(String uri : Constant.AUTH_EXCLUDE_URL) {
                SKIP_LIST.add(uri);
            }
        }
    }
    
    public static boolean isAuthExclusiveURL(HttpServletRequest request) {
        String uri = request.getRequestURI();
        

        return SKIP_LIST.contains(uri);
    }
    
    public boolean isValidURI(HttpServletRequest request) throws Exception {
        
        String uri = request.getRequestURI();
        
        // URI 유효성 체크가 필요없는 지 여부를 확인
        if(SKIP_LIST.contains(uri))
            return true;
        
        SessionVO svo = (SessionVO)request.getSession().getAttribute(Constant.SESSION_ID);
        
        if(svo == null) return false;
        
        // 메뉴 테이블에 등록되어 있지만 사용 못하는 권한인 경우 Exception처리
        boolean exists = svo.containsMenuUri(uri);
        if(exists) {
            if(svo.isUsableMenuUri(uri) == false)
                throw new ValidationException(UtilCommon.toLocale(request, "msg.menu.auth.unable", null)); 
            else
                return true;
        }
        
        // 메뉴 테이블에 등록되어 있지 않는 Form, Save, Update 등 등록 및 수정 등과 관련된 URI 처리
        boolean ret = Pattern.matches(svo.getUriRegex(), uri);
        if(ret == false)
            throw new ValidationException(UtilCommon.toLocale(request, "msg.menu.auth.unable", null)); 
        
        return true;
    }

}
