package tce.com.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : GetMessageCode.java
 * @Description : 클래스 설명을 기술합니다.
 * @author HSW
 * @since 2020年1月3日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2020年1月3日     HSW     	최초 생성
 * </pre>
 */

public class GetMessageCode {
    
    private static String accountSid = Config.ACCOUNT_SID;
    private static String rod=smsCode();   //生成一个随机验证码
    private static String smsContent = rod;
    private static String url = Config.BASE_URL;
    //创建验证码
    public static  String smsCode(){
        String random=(int)((Math.random()*9+1)*100000)+""; 
        return random;
    }
    
    //根据相应的手机号发送验证码
    public static String getCode(String phone){
//        try{
//          tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
//        }catch(Exception e){
//            e.getMessage();
//        }
        String templateid = "244549";
        String param = smsContent;
        
        String body = "accountSid=" + accountSid + "&to=" + phone + "&templateid=" + templateid + "&param=" + param
            + HttpUtil.createCommonParam();
        
        // 提交请求
        String result = HttpUtil.post(url, body);
        

        
        //字符串转json对象

        JSONObject jsonObject = JSONObject.parseObject(result); 
        String respCode = jsonObject.getString("respCode");
        
        //反馈-00000状态码标识请求成功，
        String defaultRespCode="0000";
        if(defaultRespCode.equals(respCode)){
             return rod;
        }else{
            return defaultRespCode;     
        }
    }
    
}
