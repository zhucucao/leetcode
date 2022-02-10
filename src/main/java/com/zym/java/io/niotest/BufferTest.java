package com.zym.java.io.niotest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {

    public static void main(String[] args) {

    }

    @Test
    public void bufferBasicUsage() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("/Users/xiaoming/Documents/work/学习/file/filechannel读取.txt");
        // 从流中获取finlechannel
        FileChannel fileChannel = fileInputStream.getChannel();
        // 缓冲区分配空间的大小影响了循环的次数
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 从channel读取数据 写到buffer
        int loop = 0;
        while (fileChannel.read(buffer) != -1) {
            // 将buffer的写模式切换到模式(当需要从buffer中读取数据的时候)
            buffer.flip();
            ++loop;
            while (buffer.hasRemaining()) {
                System.out.println(loop + ":" + (char) buffer.get());
            }
            // 清空整个buffer 为下一次写入缓冲区做准备
            buffer.clear();
        }
        fileInputStream.close();
    }
}
