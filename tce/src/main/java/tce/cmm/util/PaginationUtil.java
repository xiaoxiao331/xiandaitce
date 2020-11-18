package tce.cmm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


@Service("paginationUtil")
public class PaginationUtil extends TagSupport {

	private static final long serialVersionUID = -5886841341045766972L;
	
	private final Log log = LogFactory.getLog(getClass());
	private int pageUnit = 15; 	 	//한페이지에 표시할 글수
	private int pageSize = 15;  	//한 화면에 보여질 페이지 번호 개수
	private String jsFunction = "jsPage";
	private int totalRecordCount = 0;
	private int currPage = 0;
	
    /**
     * creat
     */
    public PaginationUtil() {
        super();
    }

    public int doEndTag() throws JspException{
		
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			JspWriter out = pageContext.getOut();
			out.println(doPaging(request.getContextPath(), totalRecordCount, currPage));
		} catch (NullPointerException e) {
		    log.error("NullPointerException : " + ExceptionUtil.printErrorTrace(e));
		    throw new JspException(e);
		} catch (Exception e) {
		    log.error("Exception : " + ExceptionUtil.printErrorTrace(e));
            throw new JspException(e);
		}
		return  SKIP_BODY; 
	}
	
	public StringBuffer doPaging( String contextPath,int totalRecordCount,int page ){
		StringBuffer sb = new StringBuffer();
			
		int lastPageNo	 = (totalRecordCount - 1) / pageUnit + 1;	//	전체 페이지 개수
		int startPageNo = ((page - 1) / pageSize) * pageSize + 1;	//	화면에 보여질 시작 페이지 번호
		int endPageNo 	 = startPageNo + pageSize - 1;				//	화면에 보여질 종료 페이지 번호
		if(endPageNo > lastPageNo){
		    endPageNo = lastPageNo;					                //	종료 페이지 범위 처리
		}
		
		int prevPageNo = 1; //이전
		int nextPageNo = lastPageNo; //다음

		
		//처음 페이지로 이동
	    if(page > 1){ //페이지가 2페이지 이상
	        sb.append("<a href=\"javascript:"+jsFunction+"('1')\" class='btn_first'>처음</a>");
	    }else{
	        sb.append("<a class='btn_first'>처음</a>");
	    }
		if(startPageNo - pageSize < 1){
            prevPageNo = 1;
            sb.append("<a class='btn_prev'>이전</a>");  //10 페이지씩 건너 뛰기
        }else{  
            prevPageNo = startPageNo - pageSize;
            if(prevPageNo <= 0){
                prevPageNo = 1;
            }
            // 이전 X 페이지 이동
            sb.append("<a href=\"javascript:"+jsFunction+"('"+prevPageNo+"')\" class='btn_prev'>이전</a>");
        }
		if(page > 0){
		    sb.append("<ul>");
		}
		//페이지 번호 표시
		for (int i = startPageNo; i <= endPageNo; i++){
			if (i == page){
				sb.append("<li class='on'><a href='#'>"+i+"</a></li>" );
			}else{
			    sb.append("<li><a href=\"javascript:"+jsFunction+"('"+i+"')\">");
				sb.append( i );
				sb.append("</a></li>");
			}		
		}
        if(page > 0){
            sb.append("</ul>");
        }
		if(endPageNo + 1 > lastPageNo){
            nextPageNo = lastPageNo;
            sb.append("<a href=\"javascript:"+jsFunction+"('"+nextPageNo+"')\" class='btn_next'>다음</a>");
        }else{
            nextPageNo = endPageNo + 1;
            sb.append("<a href=\"javascript:"+jsFunction+"('"+nextPageNo+"')\" class='btn_next'>다음</a>");
        }
		
        //마지막 페이지 이동
		if(page < lastPageNo){
		    sb.append("<a href=\"javascript:"+jsFunction+"('"+lastPageNo+"')\" class='btn_last'>끝</a>");
		}else{
		    sb.append("<a href=\"javascript:"+jsFunction+"('"+lastPageNo+"')\" class='btn_last'>끝</a>");
		}
		return sb;
	}

    /**
     * @return the jsFunction
     */
    public String getJsFunction() {
        return jsFunction;
    }

    /**
     * @param jsFunction the jsFunction to set
     */
    public void setJsFunction(String jsFunction) {
        this.jsFunction = jsFunction;
    }

    /**
     * @return the totalRecordCount
     */
    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * @param totalRecordCount the totalRecordCount to set
     */
    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * @return the currPage
     */
    public int getCurrPage() {
        return currPage;
    }

    /**
     * @param currPage the currPage to set
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    
}
