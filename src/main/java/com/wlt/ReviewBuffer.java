package com.wlt;

import org.junit.Test;

import java.io.*;

/**
 * 复习buffer 缓冲流
 * @author 魏霖涛
 * @since 2018/2/24 0024
 */
public class ReviewBuffer {
    public static void main(String[] args){
        new ReviewBuffer().bufferedreader();
    }
    public  void bufferedreader(){
        try {
            //InputStreamReader是从byte转成char的桥梁  system.in键盘输入不能通过junit单元测试来调用只能通过main来调用
            InputStreamReader reader = new InputStreamReader(System.in);
            //BufferedReader(Reader in)是char类型输入的包装类
            BufferedReader br = new BufferedReader(reader);
        {
            String line = "";
            //表示一直监听键盘输入
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
            //只监听一次键盘输入
//            System.out.println(br.readLine());
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void bufferedwriter() throws Exception{
        File file = new File("bufferwriter.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file,true);
                //outputstreamwriter会把字节流转换成字符流
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                FileWriter fileWriter = new FileWriter(file,true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                ) {
                outputStreamWriter.write("转换流");
                bufferedWriter.write("缓冲流");
        }
    }

    @Test
    public void bufferedoutputstream() throws Exception{
        File file = new File("bufferoutputstream.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ) {
            bufferedOutputStream.write("哈哈".getBytes());
            bufferedOutputStream.flush();
        }
    }
    @Test
    public void bufferedinputstream() throws Exception{
        File file = new File("pom.xml");
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ) {
            byte[] buf = new byte[1024];
            int hasread = 0;
            while ((hasread = bufferedInputStream.read(buf)) > 0) {
                System.out.println(new String(buf,0,hasread));
            }
        }
    }
}
