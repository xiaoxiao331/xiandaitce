package tce.cmm.exception;

/**
 * <pre>
 * TCE Validation Exception Class
 * </pre>
 *
 * @ClassName   : ValidationException.java
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

@SuppressWarnings("serial")
public class ValidationException extends Exception{
    /** 생성자 */
    public ValidationException() {super();}
    
    /**
     * 생성자
     *
     * @param msg 출력 메세지
     */
    public ValidationException(String msg) {
        super(msg); 
    }
}
