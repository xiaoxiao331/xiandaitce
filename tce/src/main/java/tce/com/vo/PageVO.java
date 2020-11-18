package tce.com.vo;

/**
 * <pre>
 * PageVO 클래스(서버처리용)
 * </pre>
 *
 * @ClassName   : PageVO.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 5. 29.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 29.     LMC     	최초 생성
 * </pre>
 */

public class PageVO {
    /** 현재 페이지 */
    private String  pageIndex = "1";
    /** 한 페이지당 ROW 수  */
    private String  pageSize  = "15";
    
    /**
     * @return the pageIndex
     */
    public String getPageIndex() {
        return pageIndex;
    }
    
    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(String pageIndex) {
        if(pageIndex==null || pageIndex.equals(""))
            this.pageIndex = "1";
        this.pageIndex = pageIndex;
    }
    
    /**
     * @return the pageSize
     */
    public String getPageSize() {
        return pageSize;
    }
    
    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
