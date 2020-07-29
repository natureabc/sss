package tk.mybatis.springboot.util;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileConverBase64 {

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @return
     * 需要注意的是，一般插件返回的base64编码的字符串都是有一个前缀的:"data:image/jpeg;base64," , 解码之前这个得去掉。
     */
    public static String getbase64Url(String path) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(path);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return "data:image/jpeg;base64,"+encoder.encode(data);
    }


}
