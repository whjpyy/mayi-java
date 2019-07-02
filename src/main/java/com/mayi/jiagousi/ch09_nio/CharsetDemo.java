package com.mayi.jiagousi.ch09_nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetDemo {
    public static void main(String[] args) throws CharacterCodingException {
        // 获取编码器
        Charset gbk = Charset.forName("GBK");
        CharsetEncoder encoder = gbk.newEncoder();
        // 获取解码器
        CharsetDecoder decoder = gbk.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("曾经有一份真挚");
        charBuffer.flip();
        // 编码
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        for (int i = 0; i < 12; i++) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.flip();
        // 解密
        CharBuffer newCharBuffer = decoder.decode(byteBuffer);
        System.out.println(newCharBuffer.toString());
    }
}
