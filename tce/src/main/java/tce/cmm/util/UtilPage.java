package tce.cmm.util;

import tce.cmm.Constant;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName : UtilPage.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * 
 *               <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.     LMC        최초 생성
 *               </pre>
 */

@SuppressWarnings("unused")
public class UtilPage {
    
    private int iPageRow         = 15; // 페이지당 행수
    private int iPagePerScreen   = 15; // 한번에 표현될 페이지수
    private int iTotalRow        = 0;  // 전체 행수
    private int iTotalPage       = 0;  // 전체 페이지수
    private int iCurrPage        = 1;  // 현재 페이지 번호
    private int iStartPage       = 1;  // 시작 페이지 번호
    private int iEndPage         = 1;  // 종료 페이지 번호
    private int iStartRow        = 1;  // 시작 행번호
    private int iEndRow          = 1;  // 종료 행번호
    private int iPrevPage        = 0;  // 이전페이지 번호
    private int iNextPage        = 0;  // 다음페이지 번호
    private int iLastPage        = 1;

    private String sPageHtml     = ""; // Page 별 기능을 처리할  HTML
    private String contextPath   = "";
                                 
    private String sPgStartStr   = "<div class='paging'>";    //페이지의 Start Div
    private String sPgEndStr     = "</div>";                  //페이지의 End Div
                                 
    private String sPrevMark     = "prevPage";                // Beforepage 의 Image 나  Text
    private String sPrevMark2    = "startPage";               // Firstpage  의 Image 나  Text
    private String sNextMark     = "nextPage";                // Nextpage   의 Image 나  Text
    private String sNextMark2    = "lastPage";                // Lastpage   의 Image 나  Text
    
    private String sPageSeperate = "";                        // 페이지 번호 사이의 구분자
    private String url           = "";  //URL
    
    /**
     * 생성자
     *
     */
    public UtilPage() {}
    
    /**
     * 생성자
     * 
     * @param pageRow 페이지당 행 수
     * @param totalRow 전체 행 수
     * @param currPage 현제페이지
     */
    public UtilPage(int pageRow, int totalRow, int currPage,int iPageRow) {
        
        this.setCurrPage(currPage);
        this.setPageRow(pageRow);
        this.setTotalRow(totalRow);
        this.iPageRow = iPageRow;
        
        //총 페이지수 계산
        iLastPage = totalRow/pageRow;
        if(iLastPage>0){
            if(totalRow%pageRow!=0) iLastPage++;
        }else{
            iLastPage++;
        }
        
        this.rowProcess();
        this.pageProcess();
    }
    
    
    /**
     * 생성자
     * 
     * @param pageRow 페이지당 행 수
     * @param totalRow 전체 행 수
     * @param currPage 현제페이지
     */
    public UtilPage(int pageRow, int totalRow, int currPage) {
        
        this.setCurrPage(currPage);
        this.setPageRow(pageRow);
        this.setTotalRow(totalRow);
        
        //총 페이지수 계산
        iLastPage = totalRow/pageRow;
        if(iLastPage>0){
            if(totalRow%pageRow!=0) iLastPage++;
        }else{
            iLastPage++;
        }
        
        this.rowProcess();
        this.pageProcess();
    }
    
    /**
     * 생성자
     * 
     * @param pageRow 페이지당 행 수
     * @param totalRow 전체 행 수
     * @param currPage 현제페이지
     * @param fnNm 스크립트 함수명
     */
    public UtilPage(int pageRow, int totalRow, int currPage, String fnNm) {
        
        this.setCurrPage(currPage);
        this.setPageRow(pageRow);
        this.setTotalRow(totalRow);
        
        //총 페이지수 계산
        iLastPage = totalRow/pageRow;
        if(iLastPage>0){
            if(totalRow%pageRow!=0) iLastPage++;
        }else{
            iLastPage++;
        }
        
        this.rowProcess();
        this.pageProcess(fnNm);
    }
    
    /**
     * Statements
     *
     * @param requestURL
     * @param intValue
     * @param totCnt
     * @param intValue2
     */
    public UtilPage(String url, int pageRow, int totalRow, int currPage) {
        this.setUrl(url);
        this.setCurrPage(currPage);
        this.setPageRow(pageRow);
        this.setTotalRow(totalRow);
        
        //총 페이지수 계산
        iLastPage = totalRow/pageRow;
        if(iLastPage>0){
            if(totalRow%pageRow!=0) iLastPage++;
        }else{
            iLastPage++;
        }
        
        this.rowProcess();
        this.pageProcess();
    }

    // Set Properties
    private void setPageRow(int iPageRow) {
        this.iPageRow = iPageRow;
    }
    private void setPagePerScreen(int iPagePerScreen) {
        this.iPagePerScreen = iPagePerScreen;
    }
    private void setTotalRow(int iTotalRow) {
        this.iTotalRow = iTotalRow;
    }
    private void setCurrPage(int iCurrPage) {
        this.iCurrPage = iCurrPage;
    }
    private void setCurrPage(String sCurrPage) {
        this.iCurrPage = Integer.parseInt(sCurrPage);
    }
    private void setPrevMark(String sPrevMark) {
        this.sPrevMark = sPrevMark;
    }
    private void setNextMark(String sNextMark) {
        this.sNextMark = sNextMark;
    }
    private void setPageSeperate(String sPageSeperate) {
        this.sPageSeperate = sPageSeperate;
    }
    private void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
    
    // Get Properties
    private int getPageRow() {
        return iPageRow;
    }
    private int getPagePerScreen() {
        return iPagePerScreen;
    }
    private int getTotalPage() {
        return iTotalPage;
    }
    private int getTotalRow() {
        return iTotalRow;
    }
    private int getCurrPage() {
        return iCurrPage;
    }
    private int getStartPage() {
        return iStartPage;
    }
    private int getEndPage() {
        return iEndPage;
    }
    private int getStartRow() {
        return iStartRow;
    }
    private int getEndRow() {
        return iEndRow;
    }
    private String getContextPath() {
        return contextPath;
    }
    
    /**
     * 페이징 HTML 문자열
     *
     * @return String 페이징 HTML 문자열
     */
    public String getPageHtml() {
        return sPageHtml;
    }
    
    /**
     * 화면에 표시되는 행의 마지막 번호
     *
     * @return int 마지막 번호
     */
    public int getRealEndRow() {
        return iTotalRow-iCurrPage*iPageRow+iPageRow;
    }
    
    /**
     * 행 계산
     *
     */
    public void rowProcess() {
        
        // Start Row 설정
        if(iTotalRow == 0) {
            iStartRow = 0;
        } else {
            iStartRow = (iCurrPage * iPageRow) - (iPageRow-1);
        }
        
        // End Row 설정
        if(iTotalRow == 0) {
            iStartRow = 0;
        } else {
            iEndRow = iCurrPage * iPageRow;
        }
    }
    
    /**
     * 페이지 문자열 생성
     *
     */
    public void pageProcess() {
        pageProcess(Constant.PAGE_FN_COMMON);
    }
    
    /**
     * 페이지 문자열 생성
     *
     * @param functionNm 스크립트 함수명
     */
    public void pageProcess(String functionNm) {
        
        int i;    
        
        // 전체 페이지수 계산
        iTotalPage = (iTotalRow+(iPageRow-1)) / iPageRow;
        
        // 시작 페이지와 종료 페이지 계산
        iStartPage = ((((iCurrPage + (iPagePerScreen-1)) / iPagePerScreen) * iPagePerScreen) - (iPagePerScreen-1));
        iEndPage = ((iCurrPage + (iPagePerScreen-1)) / iPagePerScreen) * iPagePerScreen;
        
        if(iEndPage > iTotalPage) iEndPage = iTotalPage;
            
        // 이전 페이지 추가
        if(iStartPage < iPagePerScreen) {
            iPrevPage = 1;
            sPageHtml = "<a class='btn_prev' href='#'>" + sPrevMark + "</a>";
        } else {
            iPrevPage = iStartPage - 1;         
            sPageHtml = sPageHtml + "<a class='btn_prev' href=\"javascript:" + functionNm + "('" + iPrevPage + "','" + url + "');\">" + sPrevMark + "</a>";
        }
        if(iTotalRow==0) {
            sPageHtml = sPgStartStr + "<a class='btn_first' href='#'>" + sPrevMark2 + "</a>" + sPageHtml;
        }else {
            sPageHtml = sPgStartStr + "<a class='btn_first' href=\"javascript:" + functionNm + "('1','" + url + "');\">" + sPrevMark2 + "</a>" + sPageHtml;
        }       
        
        sPageHtml = sPageHtml + "<ul>";
        
        // 페이지들 추가
        if(iTotalRow==0) {
            sPageHtml = sPageHtml + sPageSeperate + "<li class='on'><a href='#'>1</a></li>";
        }else {
            for(i=iStartPage; i<=iEndPage; i++) {
                //현재 페이지 인지 확인
                if (i == iCurrPage) {
                    sPageHtml = sPageHtml + sPageSeperate + "<li class='on'><a href='#'>" + i + "</a></li>";
                } else {
                    sPageHtml = sPageHtml + sPageSeperate + "<li><a href=\"javascript:" + functionNm + "('" + i + "','" + url + "');\">" + i + "</a></li>";
                }
            }
        }
        sPageHtml = sPageHtml + "</ul>";
        
        // 다음 페이지 추가
        if(iEndPage >= iTotalPage) {
            iNextPage = 1;
            sPageHtml = sPageHtml + sPageSeperate + "<a class='btn_next' href='#'>" + sNextMark + "</a>";
        } else {
            iNextPage = iEndPage + 1;
            sPageHtml = sPageHtml + sPageSeperate + "<a class='btn_next' href=\"javascript:" + functionNm + "('" + iNextPage + "','" + url + "');\">" + sNextMark + "</a>";            
        }       
        if(iTotalRow==0) {
            sPageHtml = sPageHtml + "<a class='btn_last' href='#'>" + sNextMark2 + "</span>";
        }else {
            sPageHtml = sPageHtml + "<a class='btn_last' href=\"javascript:" + functionNm + "('" + iLastPage + "','" + url + "');\">" + sNextMark2 + "</a>";
        }
        
        sPageHtml = sPageHtml + sPgEndStr;
        
        // Start Row 설정
        if(iTotalRow == 0) {
            iStartRow = 0;
        } else {
            iStartRow = (iCurrPage * iPageRow) - (iPageRow-1);
        }
        
        // End Row 설정
        if(iTotalRow == 0) {
            iStartRow = 0;
        } else {
            iEndRow = iCurrPage * iPageRow;
        }
    }
    
    /**
     * 페이징 태그 문자열
     *
     * @param iCurrPage 현재 페이지
     * @param iTotRow 전체행수
     * @return String 페이징 태그 문자열
     */
    public String viewPage(int iCurrPage, int iTotRow) {
        
        String sReturn = "";
        
        if(iTotRow == 0) return sReturn;
        
        this.setCurrPage(iCurrPage);
        this.setTotalRow(iTotRow);
        this.pageProcess();
        
        sReturn = this.getPageHtml();
        
        return sReturn;
    }
    
    /**
     * getiTotalPage
     * 
     * @return int iTotalPage
     */
    public int getiTotalPage() {
        return iTotalPage;
    }
    
    /**
     * setiTotalPage
     * 
     * @param iTotalPage the iTotalPage to set
     */
    public void setiTotalPage(int iTotalPage) {
        this.iTotalPage = iTotalPage;
    }
    
    /**
     * getiCurrPage
     * 
     * @return int iCurrPage
     */
    public int getiCurrPage() {
        return iCurrPage;
    }
    
    /**
     * setiCurrPage
     * 
     * @param iCurrPage the iCurrPage to set
     */
    public void setiCurrPage(int iCurrPage) {
        this.iCurrPage = iCurrPage;
    }
    
    /**
     * getsPageHtml
     * 
     * @return String sPageHtml
     */
    public String getsPageHtml() {
        return sPageHtml;
    }
    
    /**
     * setsPageHtml
     * 
     * @param sPageHtml the sPageHtml to set
     */
    public void setsPageHtml(String sPageHtml) {
        this.sPageHtml = sPageHtml;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
