package tce.cmm.exception;

import javax.servlet.http.HttpServletRequest;

import able.com.service.msg.AbleReloadableResourceBundleMessageSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import tce.cmm.util.UtilCommon;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : CodeException.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 6. 11.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 6. 11.     LMC        최초 생성
 * </pre>
 */

@SuppressWarnings("serial")
public class CodeException extends Exception {

    private static ApplicationContext appContext = ContextLoaderListener.getCurrentWebApplicationContext();
    private static AbleReloadableResourceBundleMessageSource messageSource = (AbleReloadableResourceBundleMessageSource) appContext.getBean("messageSource");

    private String code;
    
    public CodeException(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    public ValidationException getValidationException(HttpServletRequest request) {
        return new ValidationException(UtilCommon.toLocale(request, code));
    }
}
