package tce.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : HTMLTagFilterRequestWrapper.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 9312952
 * @since 2019. 11. 13.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 11. 13.     9312952     	최초 생성
 * </pre>
 */

public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {
    public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        String[] checkValues = null;
        
        if (values == null) {
            return null;
        }
        
        checkValues = (String[]) values.clone();
        checkValues = WebSecurityUtil.convertXSSParams(checkValues);

        return checkValues;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        String checkValue = null;
        
        if (value == null) {
            return null;
        }
        
        checkValue = WebSecurityUtil.convertXSSParam(value);
        
        return checkValue;
    }
}