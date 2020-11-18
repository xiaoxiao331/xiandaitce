package tce.cmm.util;

import com.ibm.icu.text.IDNA.Info;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : PageHandler.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 5. 27.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 27.     LMC     	최초 생성
 * </pre>
 */

public class PageHandler {

    public PageHandler() {
    }
    
    public PageHandler(int totalCount) {
        setTotalCount(totalCount);
    }

    public PageHandler(int totalCount, int pageCount) {
        setTotalCount(totalCount);
        pageInfo.pageCount = pageCount;
    }

    private PageInfo pageInfo = new PageInfo();
    
    public PageInfo getPageInfo(int page) {
        if(pageInfo.totalPage < page)
            page = pageInfo.totalPage;

        if(page <= 1)
            page = 1;
        
        pageInfo.currentPage = page;
        
//        int start = ((page - 1) / pageInfo.pageCount) * pageInfo.pageCount + 1;
//        int end = start + pageInfo.pageCount - 1;
        int toPage;
        int fromPage = page - 2;
        if(fromPage < 1) {
            fromPage = 1;
            toPage = fromPage + 4;
        } else 
            toPage = page + 2;
        
        if(toPage > pageInfo.totalPage)
            toPage = pageInfo.totalPage;
        
        pageInfo.startPage = fromPage;
        pageInfo.endPage = toPage;
        
        if(fromPage < 2) {
            pageInfo.previousPage = 0;
        } else
            pageInfo.previousPage = fromPage - 1;
        
        if(toPage >= pageInfo.totalPage)
            pageInfo.nextPage = 0;
        else
            pageInfo.nextPage = toPage + 1;
        
        int endRow = page * pageInfo.listCount;
        if(endRow > pageInfo.totalCount)
            endRow = pageInfo.totalCount;

        int startRow = endRow - pageInfo.listCount;
        if(startRow < 1)
            startRow = 1;
        
        pageInfo.startRownum = startRow;
        pageInfo.endRownum = endRow;
        
        pageInfo.pageList.clear();
        for(int i=fromPage; i<= toPage; i++)
            pageInfo.pageList.add(i);
        
        return pageInfo;
    }
    
    public void setListCount(int listCount) {
        pageInfo.listCount = listCount;
    }
    
    public void setPageCount(int pageCount) {
        pageInfo.pageCount = pageCount;
    }
    
    public void setTotalCount(int totalCount) {
        pageInfo.totalCount = totalCount;
        
        pageInfo.totalPage = totalCount / pageInfo.listCount;
        
        if(totalCount % pageInfo.listCount > 0)
            pageInfo.totalPage++;
        
    }

    public static void main(String arg[]) {
        PageHandler p = new PageHandler(3000);
        PageInfo info = p.getPageInfo(1);
        info = p.getPageInfo(1);
        info = p.getPageInfo(2);
        info = p.getPageInfo(3);
        info = p.getPageInfo(4);
        info = p.getPageInfo(5);
        info = p.getPageInfo(6);
        info = p.getPageInfo(7);
        info = p.getPageInfo(8);
        info = p.getPageInfo(9);
        info = p.getPageInfo(10);
        info = p.getPageInfo(11);
        info = p.getPageInfo(12);
    }
}
