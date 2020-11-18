package tce.cmm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import tce.cmm.util.UtilExceptionMessage;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilDate.java
 * @Description : 클래스 설명을 기술합니다.
 * @author LMC
 * @since 2019. 4. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 4. 23.     LMC        최초 생성
 * </pre>
 */
public class UtilDate {
    public static final String FORMAT_DEFAULT      = "yyyy-MM-dd HH:mm:ss";
    /** GMT Date/Time format */
    public static final String FORMAT_HTTP_GMT     = "EEE, dd MMM yyyy HH:mm:ss zzz";
    /** Day dash format */
    public static final String FORMAT_DAY_DASH     = "yyyy-MM-dd";
    /** Day Not dash format */
    public static final String FORMAT_DAY_DASH_NOT = "yyyyMMdd";
    
    /**
     * 날짜 간격 구하기
     *
     * @param start 시작날짜
     * @param end 종료날짜
     * @return long 날짜 간격
     */
    public static long getBetweenDate(Date start, Date end){
        if(start == null)
            start = new Date();
        long diff = end.getTime() - start.getTime();
        return diff/(24*60*60*1000);
    }
    
    /**
     * 문자열 날짜 간격 구하기
     *
     * @param start 시작날짜
     * @param end 종료날짜
     * @param format 포멧
     * @return long 날짜 간격
     */
    public static long getBetweenDate(String start, String end, String format){
        
        Long result = null; 
        
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        try {
            Date d1 = sdf.parse(start);
            Date d2 = sdf.parse(end  );
            
            long gap = (d2.getTime()-d1.getTime())/1000;
            gap/=(24*60*60);
            
            result = gap;
        } catch (ParseException e) {
            System.out.println(UtilExceptionMessage.getExceptionDetailMsg(e)); //2018.10.17 Sparrow 오류 메시지 통한 정보 노출
        }
        
        return result;
    }
    
    /**
     * 현재날짜 포맷 형식에 맞게 가져오기(문자형)
     * 
     * @param format 날짜 포멧
     * @return String 날짜 포멧 형식의 현재날짜
     */
    public static String getStrDate(String format) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
        return formatter.format(new Date());
    }
    
    /**
     * 문자형 날짜 포맷 형식에 맞게 가져오기(Date형)
     *  
     * @param dateStr 변환할 문자열 날짜
     * @param format 날짜 포맷
     * @return
     */
    public static Date getDate(String dateStr, String format) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (Exception e) {}
        return date;
    }
    
    /**
     * 해당날짜 가져오기
     * 
     * @param date 날짜
     * @param format 날짜 포멧
     * @return String 날짜 포멧 형식의 현재날짜
     * @return
     */
    public static String getStrDate(Date date, String format) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
        return formatter.format(date);
    }
    
    /**
     * 일 구하기
     *
     * @param yyyymmdd 년월
     * @param day 계산 일
     * @return String 계산된 년월
     */
    @SuppressWarnings("static-access")
    public static String getCalcDay(String yyyymmdd, int day, String format) {
        
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(yyyymmdd.substring(0,4)), Integer.parseInt(yyyymmdd.substring(4,6))-1 ,Integer.parseInt(yyyymmdd.substring(6, 8)));
        cal.add(cal.DATE, day);
        
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
        
        return formatter.format(cal.getTime());
    }
    
    /**
     * 그리니치표준시를 기준으로 HttpDate 형식으로 획득
     * @return String 그리니치표준시
     * @throws Exception Exception
     */
    public static String getHttpDateGMTTime() throws Exception{
        return getGMTDate(FORMAT_HTTP_GMT);
    }
    
    /**
     * getGMTDate 현재날짜/시간 획득<br/>
     * default TimeZone : 그리니치 표준시
     * 
     * @param format date 포맷
     * @return 현재날짜/시간
     * @exception Exception Exception
     */
    public static String getGMTDate(String format) throws Exception{
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        
        // GMT 설정
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        sdf.setTimeZone(timeZone);
        
        return sdf.format(today);
    }
}