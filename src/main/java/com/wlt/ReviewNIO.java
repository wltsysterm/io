package com.wlt;

import org.junit.Test;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 复习nio new io jdk1.4及其之后才有的
 * Created by wlt on 2018/2/24.
 */
public class ReviewNIO {
    @Test
    public void nio(){
        //创建Buffer
        CharBuffer charBuffer = CharBuffer.allocate(10);
        System.out.println("长度："+charBuffer.length());
        System.out.println("上限："+charBuffer.limit());
        System.out.println("容量："+charBuffer.capacity());
        System.out.println("当前位置："+charBuffer.position());
        //放入元素
        charBuffer.append("1");
        charBuffer.append("23");
        charBuffer.put("45");
        System.out.println("放入3个元素后，position: "+charBuffer.position());
        //当所有数据装载完毕之后，调用Buffer的flip()方法，会将limit设置为position位置，position则置0，这就做好了读的准备，此时position就充当数组下标的角色，而limit就是数组的上限，最多只能读到这里。
        charBuffer.flip();
        System.out.println("执行flip()后，limit: "+charBuffer.limit()+", position: "+charBuffer.position());
        //clear之前读取都是相对读取，即从当前position位置读取，读完之后，position会随之后移
        //取出第一个元素
        System.out.println("第一个元素(position=0): "+charBuffer.get());
        System.out.println("取出第一个元素后，position:" +charBuffer.position());
        //取出第二个元素
        System.out.println("第二个元素(position=1): "+charBuffer.get());
        System.out.println("取出第二个元素后，position:" +charBuffer.position());
        //clear之后读取都是绝对读取，position不会再移动了。当Buffer输出数据结束后，此时的position应该已经读到了limit位置，调用Buffer的clear()方法，会将position再次置0，同时会将limit置为capacity,这又恢复了Buffer写数据前的状态，相当于重置了Buffer，为下一次写进Buffer做好了准备。但注意的是，clear()这个过程并没有清空Buffer中的数据。
        charBuffer.clear();
        System.out.println("执行clear()后，limit: "+charBuffer.limit()+" ,position: "+charBuffer.position());
        System.out.println("执行clear()后，buffer并没有被清除。第三个元素为: "+charBuffer.get(2));
        System.out.println("执行绝对读取后， position: "+charBuffer.position());
    }
    @Test
    public  void bufferchannel() {
        File f = new File("pom.xml");
        try (
                //FileInputStream获取的channel只能读
                FileChannel inChannel = new FileInputStream(f).getChannel();
                //FileOutputStream获取的channel只能写
                FileChannel outChannel = new FileOutputStream("bufferchannel.txt").getChannel()
        ) {
            //将整个文件映射成buffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            //输出buffer里的全部数据，就直接写入了a.txt
            outChannel.write(buffer);
            //复位buffer的limit和position
            buffer.clear();
            Charset charset = Charset.forName("UTF-8");
            //解码器
            CharsetDecoder decoder = charset.newDecoder();
            //用解码器将ByteBuffer解码成CharBuffer
            CharBuffer charBuffer = decoder.decode(buffer);
            //charBuffer的toString()方法可以获取对应的字符串
            System.out.println(charBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
