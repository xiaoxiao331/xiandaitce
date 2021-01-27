package tce.cmm.util;

import java.util.ArrayList;
import java.util.List;
 
/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : ExcelReadOption.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.          	최초 생성
 * </pre>
 */

public class ExcelReadOption {
    /**
     * 엑셀파일의 경로
     */
    private String filePath;
    
    /**
     * 추출할 컬럼 명
     */
    private List<String> outputColumns;
    
    /**
     * 추출을 시작할 행 번호
     */
    private int startRow;
    
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public List<String> getOutputColumns() {
        
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
        
        return temp;
    }
    public void setOutputColumns(List<String> outputColumns) {
        
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
        
        this.outputColumns = temp;
    }
    
    public void setOutputColumns(String ... outputColumns) {
        
        if(this.outputColumns == null) {
            this.outputColumns = new ArrayList<String>();
        }
        
        for(String ouputColumn : outputColumns) {
            this.outputColumns.add(ouputColumn);
        }
    }
    
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
}
