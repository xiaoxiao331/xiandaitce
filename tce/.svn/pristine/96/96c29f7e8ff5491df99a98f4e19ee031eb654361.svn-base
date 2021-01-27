package tce.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import tce.setting.service.CodeService;
import tce.setting.service.dao.CodeDAO;
import tce.setting.vo.CodeVO;

/**
 * <pre>
 * 코드 담당 서비스 클래스
 * </pre>
 *
 * @ClassName   : CodeServiceImpl.java
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
@Service("codeService")
public class CodeServiceImpl implements CodeService{
    @Resource(name = "codeDAO")
    private CodeDAO codeDAO;
    
    /**
     * 그룹코드 리스트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public List<CodeVO> selectGroupCodeList(CodeVO paramVO) throws Exception {
        return codeDAO.selectGroupCodeList(paramVO);
    }
    
    /**
     * 그룹코드 등록 처리
     *  
     * @param codeVO
     * @throws Exception
     */
    @Override
    public void insertGroupCodeInfo(CodeVO codeVO) throws Exception {
        codeDAO.insertGroupCodeInfo(codeVO);
    }

    /**
     * 그룹코드 정보 조회
     *
     * @return
     */
    @Override
    public CodeVO selectGroupCodeInfo(CodeVO codeVO) throws Exception {
        return codeDAO.selectGroupCodeInfo(codeVO);
    }
    
    /**
     * 그룹코드 수정 처리
     *
     * @param paramVO
     */
    @Override
    public void updateGroupCodeInfo(CodeVO paramVO) throws Exception {
        codeDAO.updateGroupCodeInfo(paramVO);
    }
    
    /**
     * 상세코드 리스트 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public List<CodeVO> selectDetailCodeList(CodeVO paramVO) throws Exception {
        return codeDAO.selectDetailCodeList(paramVO);
    }
    
    /**
     * 상세코드 등록 처리
     *
     * @param paramVO
     * @return
     */
    @Override
    public void insertDetailCodeInfo(CodeVO paramVO) throws Exception {
        codeDAO.insertDetailCodeInfo(paramVO);
    }
    
    /**
     * 상세코드 정보 조회
     *
     * @param paramVO
     * @return
     */
    @Override
    public CodeVO selectDetailCodeInfo(CodeVO paramVO) throws Exception {
        return codeDAO.selectDetailCodeInfo(paramVO);
    }

    /**
     * 상세코드 수정 처리
     *
     * @param paramVO
     */
    @Override
    public void updateDetailCodeInfo(CodeVO paramVO) throws Exception {
        codeDAO.updateDetailCodeInfo(paramVO);
    }
}
