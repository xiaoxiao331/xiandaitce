package tce.ims.vo;

import java.io.Serializable;

public class CarRetailInfoSearchVO  implements Serializable{
    private static final long serialVersionUID = 1L;

    private String startDate;
    private String endDate;
    private String csmcScn;
    private String tmnlType; //단말기 TYPE
    private String historyDate;
    
    public boolean isSearchEmpty() {
        if(startDate == null && endDate ==null)
            return false;
        else
            return true;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getCsmcScn() {
        return csmcScn;
    }
    public void setCsmcScn(String csmcScn) {
        this.csmcScn = csmcScn;
    }
    public String getTmnlType() {
        return tmnlType;
    }
    public void setTmnlType(String tmnlType) {
        this.tmnlType = tmnlType;
    }
	public String getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(String historyDate) {
		this.historyDate = historyDate;
	}


}
