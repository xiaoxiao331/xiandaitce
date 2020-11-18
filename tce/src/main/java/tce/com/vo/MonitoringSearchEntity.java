package tce.com.vo;
/**
 * 
 * @author HSW
 *
 */
public class MonitoringSearchEntity {
	
	private String monitoringFieidSearch;
    private String monitoringSortSearch;
    private String monitoringDateSearch;
    private String smsSendYn;
    
    public boolean isSearchEmpty() {
        if(monitoringFieidSearch == null && monitoringSortSearch == null)
            return false;
        else
            return true;
    }
	public String getMonitoringFieidSearch() {
		return monitoringFieidSearch;
	}
	public void setMonitoringFieidSearch(String monitoringFieidSearch) {
		this.monitoringFieidSearch = monitoringFieidSearch;
	}
	public String getMonitoringSortSearch() {
		return monitoringSortSearch;
	}
	public void setMonitoringSortSearch(String monitoringSortSearch) {
		this.monitoringSortSearch = monitoringSortSearch;
	}
	public String getMonitoringDateSearch() {
		return monitoringDateSearch;
	}
	public void setMonitoringDateSearch(String monitoringDateSearch) {
		this.monitoringDateSearch = monitoringDateSearch;
	}
	public String getSmsSendYn() {
		return smsSendYn;
	}
	public void setSmsSendYn(String smsSendYn) {
		this.smsSendYn = smsSendYn;
	}
    
}
