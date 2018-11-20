package com.huateng.test.netty;

import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 测试netty通讯
 */
public class TestNetty {

    private String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><requestVo><appHeader><industryapp>010</industryapp><reqSys>医疗云</reqSys><scene>100</scene><useType>002</useType></appHeader><busBody><resultMap><productNo>111000111</productNo><validDate>5000</validDate><version>2.0.1</version></resultMap></busBody><sysHeader><chlMsgId>2222222</chlMsgId><chlSendTime>2018-10-10 22:10:10</chlSendTime><receiver>lisi</receiver><sendMsgId>11111</sendMsgId><sendTime>2018-10-10</sendTime><sender>zhangsan</sender><serviceCode>001</serviceCode><teller>zhangsan</teller><version>1.0.0</version></sysHeader></requestVo>";

    @Test
    public void testNetty() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream);
        pw.write(data);
        pw.flush();

        //读取字节流
        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while (-1 != (len = (inputStream.read(buff)))) {
            System.out.println(new String(buff, 0, len, CharsetUtil.UTF_8));
        }

        inputStream.close();
        pw.close();
        outputStream.close();
        socket.close();
    }
}
