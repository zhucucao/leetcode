package com.zym.java.io.niotest;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
    static String filePath = "/Users/xiaoming/Documents/work/学习/file/filechannel读取.txt";
    static String fileOutPath = "/Users/xiaoming/Documents/work/学习/file/copy.txt";

    public static void main(String[] args) throws Exception {

        fileChannelCopy();
    }

    /**
     * 使用FileChannel读取数据
     * @throws Exception
     */
    @Test
    public void fileChannelReadData() throws Exception {
        // http://tutorials.jenkov.com/java-nio/channels.html
        //  https://www.jianshu.com/p/8fdba75f5e34

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
    // 使用FileChannel复制文件
    public static void fileChannelCopy() throws Exception {
        // 通过inputStream 或者 outputStream获取 channel
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOutPath);

        FileChannel fileChannel = fileInputStream.getChannel();

        FileChannel outFileChannel = fileOutputStream.getChannel();
        // 创建buffer 设置一次性最大读取的长度
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // channel读取数据写入到buffer中
        while (fileChannel.read(buffer) != -1) {
        // buffer将写模式切换为读模式 准备channel从buffer中读取数据
            buffer.flip();
        // 从buffer中读取数据 写入到buffer中
            outFileChannel.write(buffer);
        // 清空buffer整个缓冲区 为下次一次读作准备
            buffer.clear();
        }

        fileInputStream.close();
        fileOutputStream.close();

    }


}
