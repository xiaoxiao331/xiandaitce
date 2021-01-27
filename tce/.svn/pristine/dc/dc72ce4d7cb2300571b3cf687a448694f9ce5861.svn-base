package tce.cmm.util;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilExceptionMessage.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.     LMC        최초 생성
 * </pre>
 */

public class UtilExceptionMessage {
    /**
     * [예외발생 상세 메세지 조회]
     * <br/>
     * <br/>예외발생 시 상세 메세지를 문자열로 조회한다.
     * 
     * @param e Exception
     * @return 상세 에러메세지
     */
    public static String getExceptionDetailMsg(Exception e){
        
        try {
            StringBuffer sbErrMsg = new StringBuffer();
            
            StackTraceElement[] elem = e.getStackTrace();
            sbErrMsg.append("\n");
            sbErrMsg.append(e.toString());
            
            for (int i = 0; i < elem.length; i++){
                sbErrMsg.append("\n");
                sbErrMsg.append(elem[i]);
            }
            
            return sbErrMsg.toString();
            
        } catch (Exception e1) {
            System.out.println("ERROR");
        }
        
        return "";
    }
}
