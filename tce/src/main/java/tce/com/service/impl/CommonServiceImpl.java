package tce.com.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import able.com.service.HService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import tce.cmm.TCEProperties;
import tce.cmm.util.UtilCommon;
import tce.com.service.CommonService;
import tce.com.service.dao.CommonDAO;
import tce.com.service.dao.DBSetupDAO;
import tce.setting.service.dao.CodeDAO;
import tce.setting.vo.CodeVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : CommonServiceImpl.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.                 	최초 생성
 * </pre>
 */
@Service("commonService")
public class CommonServiceImpl extends HService implements CommonService{
    private Log log = LogFactory.getLog(this.getClass()); 
    
    @Resource(name = "commonDAO")
    private CommonDAO commonDAO;
    
    @Resource(name = "codeDAO")
    private CodeDAO codeDAO;
    
    @Resource(name = "dbSetupDAO")
    private DBSetupDAO dbSetupDAO;
        
    private void setupInitDB() throws Exception {
        String path = TCEProperties.queryPath;
        
        String data = FileUtils.readFileToString(new File(path));
        String[] list = data.split(";");
        for(String query : list) {
            dbSetupDAO.setupDatabase(query.trim());
        }
    }
    
    /*
     * @see tce.com.service.CommonService#getCodeList(javax.servlet.http.HttpServletRequest, java.lang.String)
     */
    @Override
    public String getCodeName(HttpServletRequest request, String groupCode, String code) throws Exception {
        
        try {
            CodeVO vo = new CodeVO();
            vo.setGroupCodeId(groupCode);
            vo.setCodeId(code);
            CodeVO cvo = codeDAO.selectDetailCodeInfo(vo);

            return UtilCommon.toLocale(request, code, cvo.getCodeNm());
        } catch(Exception e) {
            log.warn(e);
            return code;
        }
    }

    @Override
    public List<CodeVO> getGroupCodeNameList(HttpServletRequest request, String groupCode) throws Exception {
        CodeVO vo = new CodeVO();
        vo.setGroupCodeId(groupCode);
        List<CodeVO> list = codeDAO.selectDetailCodeList(vo);
        
        if(list != null) {
            for(CodeVO cvo : list) {
                cvo.setCodeNm(UtilCommon.toLocale(request, cvo.getMsgId(), cvo.getCodeNm()));
            }
        }
        
        return list;
    }
    
    @Override
    public Map<String, String> getGroupCodeMap(HttpServletRequest request, String groupCode) throws Exception {
        CodeVO vo = new CodeVO();
        vo.setGroupCodeId(groupCode);
        List<CodeVO> list = codeDAO.selectDetailCodeList(vo);
        
        Map<String, String> result = new HashMap<String, String>();
        if(list != null) {
            for(CodeVO cvo : list) {
                String localeNm = UtilCommon.toLocale(request, cvo.getMsgId(), cvo.getCodeNm());
                
                result.put(cvo.getCodeId(), localeNm);
            }
        }
        
        return result;
    }
}
