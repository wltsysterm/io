package com.wlt;

import org.junit.Test;

import java.io.*;

/**
 * 复习推回输出流
 * Created by wlt on 2018/2/24.
 */
public class ReviewPushBack {
    @Test
    public void pushbackreader() throws IOException {
        File file = new File("pom.xml");
        try (
                FileReader fileReader = new FileReader(file);
                PushbackReader pushbackReader = new PushbackReader(fileReader,32);
                ) {
            char[] buf = new char[32];
            int hasread = 0;
            while ((hasread = pushbackReader.read(buf)) > 0) {
                String tmp = new String(buf,0,hasread);
                if (tmp.indexOf("插件")!=-1) {
                    System.out.println(tmp);
                    pushbackReader.unread(tmp.toCharArray());
                    if ((hasread = pushbackReader.read(buf))>0) {
                        System.out.println("【"+new String(buf,0,hasread)+"】");
                    }
                }
            }
        }
    }
}
