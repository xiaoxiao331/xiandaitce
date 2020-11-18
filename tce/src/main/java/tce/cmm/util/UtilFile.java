package tce.cmm.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UtilFile
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
public class UtilFile {
    
    /** 파일 저장 버퍼 기본 크기*/
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    
    /**
     * toByteArray
     *
     * @param is
     * @return byte[]
     * @throws IOException IOException
     */
    public static byte[] toByteArray(InputStream is) throws IOException {
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int n = 0;
        while (-1 != (n = is.read(buffer))) {
            output.write(buffer, 0, n);
        }
        try {
            if (output != null) {
                output.close();
            }
        } catch (Exception ee) {
        }
        try {
            if (is != null) {
                is.close();
            }
        } catch (Exception ee) {
        }
        
        return output.toByteArray();
    }
}