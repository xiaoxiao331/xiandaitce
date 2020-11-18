package tce.setting.service;

import java.util.List;

import tce.setting.vo.CodeVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : CodeService.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 5. 22.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 22.     LMC     	최초 생성
 * </pre>
 */

public interface CodeService {
    /**
     * 그룹코드 리스트 조회
     *
     * @param paramVO
     * @return
     */
    public List<CodeVO> selectGroupCodeList(CodeVO paramVO) throws Exception;
    
    /**
     * 그룹코드 등록 처리
     *  
     * @param codeVO
     * @throws Exception
     */
    public void insertGroupCodeInfo(CodeVO paramVO) throws Exception;
    
    /**
     * 그룹코드 정보 조회
     *
     * @return
     */
    public CodeVO selectGroupCodeInfo(CodeVO paramVO) throws Exception;
    
    /**
     * 그룹코드 수정 처리
     *
     * @param paramVO
     */
    public void updateGroupCodeInfo(CodeVO paramVO) throws Exception;
    
    /**
     * 상세코드 리스트 조회
     *
     * @param paramVO
     * @return
     */
    public List<CodeVO> selectDetailCodeList(CodeVO paramVO) throws Exception;
    
    /**
     * 상세코드 등록 처리
     *
     * @param paramVO
     */
    public void insertDetailCodeInfo(CodeVO paramVO) throws Exception;
    
    /**
     * 상세코드 정보 조회
     *
     * @param paramVO
     * @return
     */
    public CodeVO selectDetailCodeInfo(CodeVO paramVO) throws Exception;
    
    /**
     * 상세코드 수정 처리
     *
     * @param paramVO
     */
    public void updateDetailCodeInfo(CodeVO paramVO) throws Exception;
}
