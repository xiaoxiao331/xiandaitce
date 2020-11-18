package tce.cmm.util;

import java.util.ArrayList;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : PageInfo.java
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

public class PageInfo {
    public int startPage;
    public int endPage;
    public int startRownum;
    public int endRownum;
    public int totalCount;
    public int listCount = 30;  // default value
    public int totalPage;
    public int pageCount = 5;
    public int currentPage;
    public int previousPage;
    public int nextPage;
    
    /**
     * @return the previousPage
     */
    public int getPreviousPage() {
        return previousPage;
    }
    /**
     * @param previousPage the previousPage to set
     */
    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }
    /**
     * @return the nextPage
     */
    public int getNextPage() {
        return nextPage;
    }
    /**
     * @param nextPage the nextPage to set
     */
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
    public ArrayList<Integer> pageList = new ArrayList<Integer>();
   
    /**
     * @return the pageList
     */
    public ArrayList<Integer> getPageList() {
        return pageList;
    }
    /**
     * @param pageList the pageList to set
     */
    public void setPageList(ArrayList<Integer> pageList) {
        this.pageList = pageList;
    }
    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }
    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }
    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    /**
     * @return the listCount
     */
    public int getListCount() {
        return listCount;
    }
    /**
     * @param listCount the listCount to set
     */
    public void setListCount(int listCount) {
        this.listCount = listCount;
    }
    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }
    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }
    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the startPage
     */
    public int getStartPage() {
        return startPage;
    }
    /**
     * @param startPage the startPage to set
     */
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    /**
     * @return the endPage
     */
    public int getEndPage() {
        return endPage;
    }
    /**
     * @param endPage the endPage to set
     */
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    /**
     * @return the startRownum
     */
    public int getStartRownum() {
        return startRownum;
    }
    /**
     * @param startRownum the startRownum to set
     */
    public void setStartRownum(int startRownum) {
        this.startRownum = startRownum;
    }
    /**
     * @return the endRownum
     */
    public int getEndRownum() {
        return endRownum;
    }
    /**
     * @param endRownum the endRownum to set
     */
    public void setEndRownum(int endRownum) {
        this.endRownum = endRownum;
    }
}