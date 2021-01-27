package tce.work.vo;

import java.io.Serializable;

/**
 * 工单实体类
 * @author HSW
 *
 */
public class WorkMgrVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer worId;
	
	private String worClassify;
	
	private String worExigency;
	
	private String worTitle;
	
	private String worCreauser;
	
	private String worCreateDate;
	
	private String worUpdateDate;
	
	private String worPerson;
	
	private String worResult;
	
	private String worNot;
	
	private Integer worSussess;
	
	private String worGeginDate;
	
	private String worIntheDate;
	
	private String worRate;
	
	private String worDatail;
	
	private String worExtend1;
	
	private String worExtend2;
	
	private String searchStartDate;
    
	private String searchEndDate;
	
	private String searchExigency;
	
	/** limit */
    private Integer limit;
    
    private Integer offset;
    
	/** 현재 페이지 */
    private String currPage; 

	public Integer getWorId() {
		return worId;
	}

	public void setWorId(Integer worId) {
		this.worId = worId;
	}

	public String getWorClassify() {
		return worClassify;
	}

	public void setWorClassify(String worClassify) {
		this.worClassify = worClassify;
	}

	public String getWorExigency() {
		return worExigency;
	}

	public void setWorExigency(String worExigency) {
		this.worExigency = worExigency;
	}

	public String getWorTitle() {
		return worTitle;
	}

	public void setWorTitle(String worTitle) {
		this.worTitle = worTitle;
	}

	public String getWorCreauser() {
		return worCreauser;
	}

	public void setWorCreauser(String worCreauser) {
		this.worCreauser = worCreauser;
	}

	public String getWorCreateDate() {
		return worCreateDate;
	}

	public void setWorCreateDate(String worCreateDate) {
		this.worCreateDate = worCreateDate;
	}

	public String getWorUpdateDate() {
		return worUpdateDate;
	}

	public void setWorUpdateDate(String worUpdateDate) {
		this.worUpdateDate = worUpdateDate;
	}

	public String getWorPerson() {
		return worPerson;
	}

	public void setWorPerson(String worPerson) {
		this.worPerson = worPerson;
	}

	public String getWorResult() {
		return worResult;
	}

	public void setWorResult(String worResult) {
		this.worResult = worResult;
	}

	public String getWorNot() {
		return worNot;
	}

	public void setWorNot(String worNot) {
		this.worNot = worNot;
	}

	public Integer getWorSussess() {
		return worSussess;
	}

	public void setWorSussess(Integer worSussess) {
		this.worSussess = worSussess;
	}

	public String getWorGeginDate() {
		return worGeginDate;
	}

	public void setWorGeginDate(String worGeginDate) {
		this.worGeginDate = worGeginDate;
	}

	public String getWorIntheDate() {
		return worIntheDate;
	}

	public void setWorIntheDate(String worIntheDate) {
		this.worIntheDate = worIntheDate;
	}

	public String getWorRate() {
		return worRate;
	}

	public void setWorRate(String worRate) {
		this.worRate = worRate;
	}

	public String getWorDatail() {
		return worDatail;
	}

	public void setWorDatail(String worDatail) {
		this.worDatail = worDatail;
	}

	public String getWorExtend1() {
		return worExtend1;
	}

	public void setWorExtend1(String worExtend1) {
		this.worExtend1 = worExtend1;
	}

	public String getWorExtend2() {
		return worExtend2;
	}

	public void setWorExtend2(String worExtend2) {
		this.worExtend2 = worExtend2;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public String getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSearchExigency() {
		return searchExigency;
	}

	public void setSearchExigency(String searchExigency) {
		this.searchExigency = searchExigency;
	}

}
