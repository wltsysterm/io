package com.wlt;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * 复习包装类
 * Created by wlt on 2018/2/24.
 */
public class ReviewPackage {
    public static void main(String[] args){
       new ReviewPackage().scanner();
    }

    @Test
    public void printstream() throws FileNotFoundException,IOException{
        File file = new File("printstream.txt");
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                PrintStream printStream = new PrintStream(fileOutputStream);
                ) {
            printStream.write("无解字节".getBytes());
            printStream.println("无解字符串");
            printStream.println(new tmp("111","222"));
            printStream.append("无解");
        }
    }
    @Test
    public void printwriter() throws IOException {
        File file = new File("printwriter.txt");
        try (
                //文件如果不存在会自动创建
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                PrintWriter printWriter = new PrintWriter(fileOutputStream);
                ) {
            printWriter.write("无解writer\n");
            printWriter.println("无解字符串");
            printWriter.println(new tmp("111","222"));
            printWriter.append("无解");
        }
    }
    public void scanner(){
        try (
                Scanner scanner = new Scanner(System.in);
                ) {
            System.out.println(scanner.next());
        }

    }
}
class tmp{
    private String name;
    private String age;

    public tmp(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}