package org.fwx.java.base.d14network;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName SocketTest02
 * @Description 文件数据网络传输，服务端返回接收完成请求
 * @Author Fwx
 * @Date 2023/6/2 16:05
 * @Version 1.0
 */
public class SocketTest02 {
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            socket = new Socket("127.0.0.1", 9999);
            os = socket.getOutputStream();

            // 给服务端发数据
            bis = new BufferedInputStream(new FileInputStream("temp/Wallpaper.jpg"));
            byte[] bytes = new byte[1024];
            int length = 0;

            while ((length = bis.read(bytes)) != -1){
                os.write(bytes,0,length);
            }
            // 关闭输出流。如果不关闭，会导致服务端阻塞
            socket.shutdownOutput();

            // *** 接收服务端返回的信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            char[] chars = new char[1024];
            int lengthC = 0;
            while ((lengthC = isr.read(chars)) != -1) {
                System.out.println("收到服务端" + socket.getInetAddress() + "信息：" + new String(chars, 0, lengthC));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (isr != null) {
                    isr.close();
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
    public void server()  {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        BufferedOutputStream bos = null;
        OutputStream os = null;
        try {
            serverSocket = new ServerSocket(9999);
            socket = serverSocket.accept();

            // *** 接收客户端数据
            is = socket.getInputStream();
            bos = new BufferedOutputStream(new FileOutputStream("temp/Wallpaper02.jpg"));
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = is.read(bytes)) != -1) {
                bos.write(bytes,0,length);
            }

            // *** 返回接收完成信息
            os = socket.getOutputStream();
            os.write("完成图片接收！".getBytes());
            socket.shutdownOutput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
