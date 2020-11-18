package tce.com;

public enum IFType {
    
	CMM_04("http://10.124.74.4:9001/autoservice/rest/cmm/text_sms");
	private final String uri;

	private IFType(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}
}
