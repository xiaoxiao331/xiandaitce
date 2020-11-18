package tce.ims.vo;


public class IMSEntity {
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCsmcScn() {
		return csmcScn;
	}

	public void setCsmcScn(String csmcScn) {
		this.csmcScn = csmcScn;
	}

	public String getPrdnVehlCd() {
		return prdnVehlCd;
	}

	public void setPrdnVehlCd(String prdnVehlCd) {
		this.prdnVehlCd = prdnVehlCd;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	private String type;
	private String csmcScn;
	private String prdnVehlCd;
	private Integer cnt;
	
	@Override
    public boolean equals(Object o) {
		boolean tmp = true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IMSEntity imsEntity = (IMSEntity) o;

        if (type.equals(imsEntity.type) 
        		&& csmcScn.equals(imsEntity.csmcScn) 
        		&& prdnVehlCd.equals(imsEntity.prdnVehlCd)
        		&& cnt == imsEntity.cnt){
        	tmp =  true;
        }else{
        	tmp = false;
        }
        return tmp;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (type == null ? 0 : type.hashCode());
        result = 31 * result + (csmcScn == null ? 0 : csmcScn.hashCode());
        result = 31 * result + (prdnVehlCd == null ? 0 : prdnVehlCd.hashCode());
        result = 31 * result + (cnt == null ? 0 : cnt.hashCode());
        return result;
    }

}
