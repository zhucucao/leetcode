package com.zym.java.io.niotest;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {

    public static void main(String[] args) {

    }
    // 使用 SocketChannel 来建立 TCP 连接，发送并接收数据，默认使用 阻塞模式
    @Test
    public void blockModel() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        // connect会阻塞 直到连接成功为止
        socketChannel.connect(new InetSocketAddress("https://www.incopat.com", 80));
    }
}
