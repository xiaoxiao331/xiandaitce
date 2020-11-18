package tce.app.vo;

import tce.com.vo.MonitoringSearchEntity;

public class MonitoringResultGroup extends MonitoringSearchEntity{

	  private String rgstDtm;
	  private String scenario;
	  private String allcnt;
	  private String success;
	  private String failCnt;
	  private String contiNue;
	  private String failurerate;
	  private String userId;
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getAllcnt() {
		return allcnt;
	}
	public void setAllcnt(String allcnt) {
		this.allcnt = allcnt;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(String failCnt) {
		this.failCnt = failCnt;
	}
	public String getContiNue() {
		return contiNue;
	}
	public void setContiNue(String contiNue) {
		this.contiNue = contiNue;
	}
	public String getFailurerate() {
		return failurerate;
	}
	public void setFailurerate(String failurerate) {
		this.failurerate = failurerate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	  
}
