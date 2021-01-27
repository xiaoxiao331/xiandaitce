package tce.cmm.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : DKCResponseException.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 6. 10.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 6. 10.     LMC        최초 생성
 * </pre>
 */
@ControllerAdvice
public class TCEResponseException {
    /**
     * Validation Check 
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleValidException(ValidationException e) {
        ModelAndView mav = new ModelAndView("empty/com/validator");
        mav.addObject("ERROR_MSG", e.getMessage());
        return mav;
    }
}
