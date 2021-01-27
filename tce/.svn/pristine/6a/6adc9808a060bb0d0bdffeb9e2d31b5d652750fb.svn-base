package tce.cmm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.MappedInterceptor;

import tce.cmm.MgrInterceptor;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : WebMvcConfig.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 6. 10.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 6. 10.     LMC     	최초 생성
 * </pre>
 */

public class WebMvcConfig {
    @Autowired
    private MgrInterceptor mgrInterceptor;
    
    /**
     * Interceptor URL 정의
     * @return Mapping Interceptor
     */
    @Bean 
    public MappedInterceptor executeInterceptor() { 
        return new MappedInterceptor(new String[]{"/**"}, mgrInterceptor); 
    }
}
