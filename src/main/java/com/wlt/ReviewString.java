package com.wlt;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 复习String字符串流
 * Created by wlt on 2018/2/24.
 */
public class ReviewString {

    @Test
    public void stringreader() throws IOException{
        String string = "华夏腾飞\n神州异彩";
        try (
                StringReader stringReader = new StringReader(string);
                ) {
            char[] buf = new char[32];
            int hasread = 0;
            while ((hasread=stringReader.read(buf))>0) {
                System.out.println(new String(buf,0,hasread));
            }
        }
    }
    @Test
    public void stringwriter() throws IOException{
        try (
                StringWriter stringWriter = new StringWriter();
                ) {
            //中间所有的字符串的拼接都是基于stringbuffer
            stringWriter.write("华夏腾飞");
            stringWriter.append("神州异彩");
            stringWriter.write("哈哈");
            System.out.println(stringWriter.toString());
        }
    }
}
