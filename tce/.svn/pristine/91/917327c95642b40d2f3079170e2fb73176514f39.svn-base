package tce.cmm.util;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilStr.java
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

public class UtilStr {
    /**
     * 문자열 Null 체크
     * 
     * @param str 체크 문자열
     * @return String 리턴 문자열
     */
    public static String isNull(String str){
        return ((str==null || str.trim().equals("")) ? "" : str);
    }
    
    /**
     * 문자열 Null 체크
     * 
     * @param str 체크 문자열
     * @param reStr 치환 문자열
     * @return String 리턴 문자열
     */
    public static String isNull(String str, String reStr){
        return ((str==null || str.trim().equals("")) ? reStr : str);
    }
    
    /**
     * 구분자 뒤에 문자열을 제거한다.(구분자포함)
     *
     * @param text  문자열
     * @param delim 구분자
     * @return
     */
    public static String removeDelimStr(String text,String delim){
        String returnStr = text==null?"":text.trim();
        if(!returnStr.equals("") && returnStr.indexOf(delim) > -1){
            returnStr = returnStr.substring(0,returnStr.indexOf(delim));
        }
        return returnStr;
    }
}
