package tce.cmm;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : TCELog.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 5. 31.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 31.     LMC     	최초 생성
 * </pre>
 */

public class TCELog {
    /** LOG */
    private static Log log = LogFactory.getLog(TCELog.class);
    
    public static void debug(String str){
        log.debug(str);
    }
    
    public static void voToString(Object voObj){
        log.debug("\n" + ToStringBuilder.reflectionToString(voObj, ToStringStyle.MULTI_LINE_STYLE));
    }
}
