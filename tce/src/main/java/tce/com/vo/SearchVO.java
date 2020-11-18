package tce.com.vo;

/**
 * <pre>
 * 검색 조건 공통 VO
 * </pre>
 *
 * @ClassName   : SearchVO.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 5. 29.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 5. 29.     LMC     	최초 생성
 * </pre>
 */

public class SearchVO extends PageVO{
    private String searchVal01;
    private String searchVal02;
    private String searchVal03;
    private String searchVal04;
    private String searchVal05;
    /**
     * @return the isSearchEmpty
     */
    public boolean isSearchEmpty() {
        if(searchVal01 == null && searchVal02 == null && 
                searchVal03 == null && searchVal04 == null && searchVal05 == null)
            return false;
        else
            return true;
    }
    
    /**
     * @return the searchVal01
     */
    public String getSearchVal01() {
        return searchVal01;
    }
    /**
     * @param searchVal01 the searchVal01 to set
     */
    public void setSearchVal01(String searchVal01) {
        this.searchVal01 = searchVal01;
    }
    /**
     * @return the searchVal02
     */
    public String getSearchVal02() {
        return searchVal02;
    }
    /**
     * @param searchVal02 the searchVal02 to set
     */
    public void setSearchVal02(String searchVal02) {
        this.searchVal02 = searchVal02;
    }
    /**
     * @return the searchVal03
     */
    public String getSearchVal03() {
        return searchVal03;
    }
    /**
     * @param searchVal03 the searchVal03 to set
     */
    public void setSearchVal03(String searchVal03) {
        this.searchVal03 = searchVal03;
    }

    /**
     * @return the searchVal04
     */
    public String getSearchVal04() {
        return searchVal04;
    }

    /**
     * @param searchVal04 the searchVal04 to set
     */
    public void setSearchVal04(String searchVal04) {
        this.searchVal04 = searchVal04;
    }

    /**
     * @return the searchVal05
     */
    public String getSearchVal05() {
        return searchVal05;
    }

    /**
     * @param searchVal05 the searchVal05 to set
     */
    public void setSearchVal05(String searchVal05) {
        this.searchVal05 = searchVal05;
    }

}
