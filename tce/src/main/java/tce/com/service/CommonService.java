package tce.com.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import tce.setting.vo.CodeVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : CommonService.java
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

public interface CommonService {
    /**
     * Statements
     *
     * @param groupCode
     * @return
     */
    public String getCodeName(HttpServletRequest request, String groupCode, String code) throws Exception;

    /**
     * Statements
     *
     * @param request
     * @param groupCode
     * @return
     * @throws Exception
     */
    public List<CodeVO> getGroupCodeNameList(HttpServletRequest request, String groupCode) throws Exception;

    /**
     * Statements
     *
     * @param request
     * @param groupCode
     * @return
     * @throws Exception
     */
    Map<String, String> getGroupCodeMap(HttpServletRequest request, String groupCode) throws Exception;
    
}