package netty;

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

    @Test
    public void testNetty() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream);
        pw.write("this is test");
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
