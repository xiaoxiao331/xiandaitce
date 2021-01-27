package tce.cmm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import tce.cmm.util.UtilSecurity;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : TCERequestWrapper.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 5. 27.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 27.     LMC     	최초 생성
 * </pre>
 */

public class TCERequestWrapper extends HttpServletRequestWrapper{
    
    public static TCERequestWrapper newInstance(final HttpServletRequest servletRequest){
        return new TCERequestWrapper(servletRequest);
    }
    
    /**
     * Statements
     *
     * @param request
     */
    public TCERequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    /*
     * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
     */
    @Override
    public String getHeader(String name) {
        String headerVal = super.getHeader(name);
        if(headerVal == null) 
            return null;
        else
            return UtilSecurity.replaceReverseHtmlTag(headerVal);
    }
    
    /*
     * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
     */
    @Override
    public String getParameter(String name) {
        String paramVal = super.getRequest().getParameter(name);
        if(paramVal == null)
            return null;
        else
            return UtilSecurity.replaceReverseHtmlTag(paramVal);
    }

    /*
     * @see javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] paramValArr = null;
        if(name != null && name.length() > 0){
            paramValArr = super.getRequest().getParameterValues(name);
            
            for(int i=0;i<paramValArr.length;i++)
                paramValArr[i] = UtilSecurity.replaceReverseHtmlTag(paramValArr[i]);
        }
        else
            paramValArr = super.getParameterValues(name);
        
        return paramValArr;
    }
}
