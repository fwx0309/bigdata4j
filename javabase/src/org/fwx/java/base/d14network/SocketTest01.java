package org.fwx.java.base.d14network;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Socket01Test
 * @Description 文本数据网络传输
 * @Author Fwx
 * @Date 2023/6/2 14:53
 * @Version 1.0
 */
public class SocketTest01 {

    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1", 9999);
            outputStream = socket.getOutputStream();

            outputStream.write("你好！我是客户端。".getBytes());
            socket.shutdownOutput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            serverSocket = new ServerSocket(9999);
            socket = serverSocket.accept();

            inputStream = socket.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] bytes = new byte[5];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes,0,length);
            }

            System.out.println("收到 '" + socket.getInetAddress() + "' 信息:" + byteArrayOutputStream.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
