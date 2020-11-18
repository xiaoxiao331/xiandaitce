package tce.cmm.util;


/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : ExceptionUtil.java
 * @Description : 클래스 설명을 기술합니다.
 * @author jinsook Kim
 * @since 2018. 10. 17.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2018. 10. 17.     jinsook Kim     	최초 생성
 * </pre>
 */

public class ExceptionUtil {

    /**
     * 
     * 장애 발생했을 때 print error
     *
     * @param stackTrace
     * @return
     */
    public static String printErrorTrace(StackTraceElement[] stackTrace){
        StringBuffer errorString = new StringBuffer("");
        errorString.append("\n-----Start to print Exception-----\n");
        if(stackTrace!=null){
            for(StackTraceElement error:stackTrace){
                errorString.append(error);
                errorString.append("\n");
            }
        }
        errorString.append("\n-----End of print Exception-----\n");
        return errorString.toString();
    }
    
    /**
     * 
     * 장애 발생했을 때 print error
     *
     * @param stackTrace
     * @return
     */
    public static String printErrorTrace(String errorMessage,StackTraceElement[] stackTrace){
        StringBuffer errorString = new StringBuffer();
        errorString.append("\n-----Start to print Exception-----\n");
        errorString.append("errorMessage : ");
        errorString.append(errorMessage);
        errorString.append("\n");
        if(stackTrace!=null){
            for(StackTraceElement error:stackTrace){
                errorString.append(error);
                errorString.append("\n");
            }
        }
        errorString.append("\n-----End of print Exception-----\n");
        return errorString.toString();
    }
    
    /**
     * 
     * 장애 발생했을 때 print error
     *
     * @param e
     * @return
     */
    public static String printErrorTrace(Exception e){
        return printErrorTrace(e.getMessage(),e.getStackTrace());
    }
}
