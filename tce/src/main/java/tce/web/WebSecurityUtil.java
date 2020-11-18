package tce.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : WebSecurityUtil.java
 * @Description : 클래스 설명을 기술합니다.
 * @author 9312952
 * @since 2019. 11. 13.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 11. 13.     9312952     	최초 생성
 * </pre>
 */

public class WebSecurityUtil {
    public static final String UPLOAD_EXT = "uploadExt";
    public static final String UPLOAD_DETOUR = "uploadDetour";
    public static final String DOWNLOAD_EXT = "downloadExt";
    private static final String uploadRegExp = "(\\.(?i)(asp|aspx|asa|cdx|cer|htr|jsp|jspx|jsw|jsv|jspf|jar|java|war|cgi|exe|inc|phtml|php|php3|php4|php5|htm|html|hta|htx|mhtm|mhtml|mht|shtml|chm){1,})";
    private static final String uploadDetour = "(\\%00|\\%ZZ|\\%0a|\\%2E|\\%2B|\\%5C|\\%20|\\%22|\\%70|\\%3f|#|\\:\\:){1,}";
    private static final String downloadRegExp = "(\\.\\.\\\\|\\.\\.\\/|\\.\\.\\.\\.\\/\\/|\\.\\.\\.\\/\\.\\/){1,}";
    private static final String xssRegExp = "(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|script|javascript|vbscript|livescript|iframe|mocha|applet|img|embed|object|marquee|qss|body|input|form|div|style|table|isindex|meta|http-equiv|xss|href){1,}";
    private static final String sqlInjcRegExp = "(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|select|union|order by|where|join|create|drop|update|alter|from|and|or|asc|delay|return|instance|version|colnum|declare|then|if|else|end|exec|all|into|null|super|schema|case|case|desc|waitfor|table|having|banner|rownum|varchar|sleep\\(\\)|chr\\(\\)|ascii\\(\\)|substr\\(\\)|bitand\\(\\)|lower\\(\\)|concat\\(\\)|count\\(\\)|distinct\\(\\)|database\\(\\)|end\\(\\)|asciistr\\(\\)|instr\\(\\)|length\\(\\)|tochar\\(\\)){1,}";

    public static HashMap<String, String> uploadFileExtCheck(String fileName, String param) {
        Pattern pattern = null;
        HashMap<String, String> result = new HashMap<String, String>();
        if (param.equals("uploadExt"))
            pattern = Pattern.compile(
                    "(\\.(?i)(asp|aspx|asa|cdx|cer|htr|jsp|jspx|jsw|jsv|jspf|jar|java|war|cgi|exe|inc|phtml|php|php3|php4|php5|htm|html|hta|htx|mhtm|mhtml|mht|shtml|chm){1,})");
        else if (param.equals("uploadDetour")) {
            pattern = Pattern.compile("(\\%00|\\%ZZ|\\%0a|\\%2E|\\%2B|\\%5C|\\%20|\\%22|\\%70|\\%3f|#|\\:\\:){1,}");
        }

        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            result.put("result", "true");
            result.put("securitySort", "FileUpload");
            result.put("violationChar", matcher.group());
        }
        return result;
    }

    public static String[] convertXSSParams(String[] values) {
        StringBuffer strBuff = new StringBuffer();

        for (int i = 0; i < values.length; ++i) {
            if (values[i] != null) {
                try {
                    values[i] = values[i].replaceAll("\\+", "%2B");
                    values[i] = URLDecoder.decode(values[i], "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                for (int j = 0; j < values[i].length(); ++j) {
                    char c = values[i].charAt(j);
                    switch (c) {
                        case '\'':
                            strBuff.append("&#39;");
                            break;
                        case '"':
                            strBuff.append("&quot;");
                            break;
                        case ':':
                            strBuff.append("&#58;");
                            break;
                        case ';':
                            strBuff.append("&#59;");
                            break;
                        case '(':
                            strBuff.append("&#40;");
                            break;
                        case ')':
                            strBuff.append("&#41;");
                            break;
                        case '<':
                            strBuff.append("&lt;");
                            break;
                        case '>':
                            strBuff.append("&gt;");
                            break;
                        case '{':
                            strBuff.append("&#123;");
                            break;
                        case '}':
                            strBuff.append("&#125;");
                            break;
                        case '#':
                            strBuff.append("&#35;");
                            break;
                        case '$':
                            strBuff.append("&#36;");
                            break;
                        case '%':
                            strBuff.append("&#37;");
                            break;
                        case '&':
                            strBuff.append("&amp;");
                            break;
                        /*case '?':
                            strBuff.append("&#63;");
                            break;*/
                        case '!':
                            strBuff.append("&#33;");
                            break;
                        case '@':
                            strBuff.append("&#64;");
                            break;
                        case '*':
                            strBuff.append("&#42;");
                            break;
                        case '|':
                            strBuff.append("&#124;");
                            break;
                        default:
                            strBuff.append(c);
                    }
                }

                values[i] = strBuff.toString();
                values[i] = reConvertXssString(values[i]);
                strBuff.setLength(0);
            } else {
                values[i] = null;
            }
        }
        return values;
    }

    public static String convertXSSParam(String p_value) {
        StringBuffer strBuff = new StringBuffer();
        String value = p_value;
        
        try {
            value = value.replaceAll("\\+", "%2B");
            value = URLDecoder.decode(value, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            switch (c) {
                case '\'':
                    strBuff.append("&#39;");
                    break;
                case '"':
                    strBuff.append("&quot;");
                    break;
                case ':':
                    strBuff.append("&#58;");
                    break;
                case ';':
                    strBuff.append("&#59;");
                    break;
                case '(':
                    strBuff.append("&#40;");
                    break;
                case ')':
                    strBuff.append("&#41;");
                    break;
                case '<':
                    strBuff.append("&lt;");
                    break;
                case '>':
                    strBuff.append("&gt;");
                    break;
                case '{':
                    strBuff.append("&#123;");
                    break;
                case '}':
                    strBuff.append("&#125;");
                    break;
                case '#':
                    strBuff.append("&#35;");
                    break;
                case '$':
                    strBuff.append("&#36;");
                    break;
                case '%':
                    strBuff.append("&#37;");
                    break;
                case '&':
                    strBuff.append("&amp;");
                    break;
                /*case '?':
                    strBuff.append("&#63;");
                    break;*/
                case '!':
                    strBuff.append("&#33;");
                    break;
                case '@':
                    strBuff.append("&#64;");
                    break;
                case '*':
                    strBuff.append("&#42;");
                    break;
                case '|':
                    strBuff.append("&#124;");
                    break;
                default:
                    strBuff.append(c);
            }
        }

        value = strBuff.toString();
        value = reConvertXssString(value);
        return value;
    }

    public static String convertXSSJsonParam(String p_value) {
        StringBuffer strBuff = new StringBuffer();
        String value = p_value;
        
        try {
            value = value.replaceAll("\\+", "%2B");
            value = URLDecoder.decode(value, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            switch (c) {
                case '\'':
                    strBuff.append("&#39;");
                    break;
                case ';':
                    strBuff.append("&#59;");
                    break;
                case '(':
                    strBuff.append("&#40;");
                    break;
                case ')':
                    strBuff.append("&#41;");
                    break;
                case '<':
                    strBuff.append("&lt;");
                    break;
                case '>':
                    strBuff.append("&gt;");
                    break;
                case '#':
                    strBuff.append("&#35;");
                    break;
                case '$':
                    strBuff.append("&#36;");
                    break;
                case '%':
                    strBuff.append("&#37;");
                    break;
                case '&':
                    strBuff.append("&amp;");
                    break;
                /*case '?':
                    strBuff.append("&#63;");
                    break;*/
                case '!':
                    strBuff.append("&#33;");
                    break;
                case '@':
                    strBuff.append("&#64;");
                    break;
                case '*':
                    strBuff.append("&#42;");
                    break;
                case '|':
                    strBuff.append("&#124;");
                    break;
                default:
                    strBuff.append(c);
            }
        }

        value = strBuff.toString();
        value = reConvertXssString(value);
        return value;
    }

    public static String reConvertXssString(String checkValue) {
        String value = checkValue;

        if (value.indexOf("&amp;&#35;39&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;39&#59;", "&#39;");
        }
        if (value.indexOf("&amp;quot&#59;") > -1) {
            value = value.replaceAll("&amp;quot&#59;", "&quot;");
        }
        if (value.indexOf("&amp;&#35;58&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;58&#59;", "&#58;");
        }
        if (value.indexOf("&amp;&#35;59&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;59&#59;", "&#59;");
        }
        if (value.indexOf("&amp;&#35;40&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;40&#59;", "&#40;");
        }
        if (value.indexOf("&amp;&#35;41&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;41&#59;", "&#41;");
        }
        if (value.indexOf("&amp;lt&#59;") > -1) {
            value = value.replaceAll("&amp;lt&#59;", "&lt;");
        }
        if (value.indexOf("&amp;gt&#59;") > -1) {
            value = value.replaceAll("&amp;gt&#59;", "&gt;");
        }
        if (value.indexOf("&amp;&#35;123&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;123&#59;", "&#123;");
        }
        if (value.indexOf("&amp;&#35;125&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;125&#59;", "&#125;");
        }
        if (value.indexOf("&amp;&#35;35&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;35&#59;", "&#35;");
        }
        if (value.indexOf("&amp;&#35;36&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;36&#59;", "&#36;");
        }
        if (value.indexOf("&amp;&#35;37&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;37&#59;", "&#37;");
        }
        if (value.indexOf("&amp;amp&#59;") > -1) {
            value = value.replaceAll("&amp;amp&#59;", "&amp;");
        }
        if (value.indexOf("&amp;&#35;63&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;63&#59;", "&#63;");
        }
        if (value.indexOf("&amp;&#35;33&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;33&#59;", "&#33;");
        }
        if (value.indexOf("&amp;&#35;64&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;64&#59;", "&#64;");
        }
        if (value.indexOf("&amp;&#35;42&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;42&#59;", "&#42;");
        }
        if (value.indexOf("&amp;&#35;124&#59;") > -1) {
            value = value.replaceAll("&amp;&#35;124&#59;", "&#124;");
        }
        if (value.indexOf("alert") > -1) {
            value = value.replaceAll("alert", "");
        } 


        return value;
    }

    public static HashMap<String, String> checkXSSParams(String values) {
        HashMap<String, String> result = new HashMap<String, String>();
        Pattern pattern = Pattern.compile(
                "(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|script|javascript|vbscript|livescript|iframe|mocha|applet|img|embed|object|marquee|qss|body|input|form|div|style|table|isindex|meta|http-equiv|xss|href){1,}");

        Matcher matcher = pattern.matcher(values);

        if (matcher.find()) {
            result.put("result", "true");
            result.put("securitySort", "XSS");
            result.put("violationChar", matcher.group());
        }

        return result;
    }

    public static HashMap<String, String> checkSQLInjectionParams(String values) {
        HashMap<String, String> result = new HashMap<String, String>();
        Pattern pattern = Pattern.compile(
                "(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|select|union|order by|where|join|create|drop|update|alter|from|and|or|asc|delay|return|instance|version|colnum|declare|then|if|else|end|exec|all|into|null|super|schema|case|case|desc|waitfor|table|having|banner|rownum|varchar|sleep\\(\\)|chr\\(\\)|ascii\\(\\)|substr\\(\\)|bitand\\(\\)|lower\\(\\)|concat\\(\\)|count\\(\\)|distinct\\(\\)|database\\(\\)|end\\(\\)|asciistr\\(\\)|instr\\(\\)|length\\(\\)|tochar\\(\\)){1,}");

        Matcher matcher = pattern.matcher(values);

        if (matcher.find()) {
            result.put("result", "true");
            result.put("securitySort", "SQL Injection");
            result.put("violationChar", matcher.group());
        }

        return result;
    }

    public static HashMap<String, String> checkDownloadParams(String values) throws UnsupportedEncodingException {
        HashMap<String, String> result = new HashMap<String, String>();
        Pattern pattern = Pattern.compile("(\\.\\.\\\\|\\.\\.\\/|\\.\\.\\.\\.\\/\\/|\\.\\.\\.\\/\\.\\/){1,}");

        Matcher matcher = pattern.matcher(values);

        if (matcher.find()) {
            result.put("result", "true");
            result.put("securitySort", "File Download");
            result.put("violationChar", matcher.group());
        }

        return result;
    }
}