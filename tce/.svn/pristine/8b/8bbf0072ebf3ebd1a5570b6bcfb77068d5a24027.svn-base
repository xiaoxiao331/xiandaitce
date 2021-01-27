package tce.cmm.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import able.com.service.msg.AbleReloadableResourceBundleMessageSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilCommon.java
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

public class UtilCommon {
    public static final String UNDEFINED = "undefined";

    private static Log log = LogFactory.getLog(UtilCommon.class);
    
    private static ApplicationContext appContext = ContextLoaderListener.getCurrentWebApplicationContext();
    private static AbleReloadableResourceBundleMessageSource messageSource = (AbleReloadableResourceBundleMessageSource) appContext.getBean("messageSource");

    public static String toLocale(HttpServletRequest request, String code) {
        try {
            return getLanguage(request, messageSource, code);
        } catch(Exception e) {
            log.warn(e);
            return UNDEFINED;
        }
    }
    
    public static String getRootPath() {
        File dir = null;
        try {
            dir = new File((new File("")).getCanonicalPath());
        } catch (IOException ioexception) {
        }
        return dir.getPath();
    }

    public static String toLocale(HttpServletRequest request, String code, String defaultValue) {
        try {
            return getLanguage(request, messageSource, code);
        } catch(Exception e) {
            log.warn(e);
            if(defaultValue == null || defaultValue.trim().length() < 1)
                return UNDEFINED;
            else
                return defaultValue;
        }
    }
    /**
     * getLanguage
     *
     * @param request
     * @param messageSource
     * @param code
     * @param args
     * @return Message String
     */
    private static String getLanguage(HttpServletRequest request, MessageSource messageSource, String code) {
        return getLanguage(request, messageSource, code, null);
    }
    
    /**
     * getLanguage
     *
     * @param request
     * @param messageSource
     * @param code
     * @param args
     * @return Message String
     */
    private static String getLanguage(HttpServletRequest request, MessageSource messageSource, String code, Object[] args) {
        return messageSource.getMessage(code, args, (Locale) request.getSession(true).getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
    }
}
