package com.wlt;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class ReviewFile
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Test
    public void file() throws IOException, InterruptedException {
        //JVM默认会将workspace作为相对路径，即user.dir系统变量所指路径, 即如果这样初始化file对象，File file = new File("."); 就是获取了user.dir路径。
        File file = new File(".");
        File file1 = new File("C:\\Program Files (x86)");
        File file2 = new File("pom.xml");
        System.out.println(file.getAbsolutePath());
        //如果file对象是一个文件，则返回文件名，如果是路径，则返回路径的最后一级
        System.out.println(file.getName());
        System.out.println(file1.getName());
        System.out.println(file2.getName());
        System.out.println(file.exists());
        //getAbsoluteFile返回的是File类型， getAbsolutePath则返回String类型  getParent返回file对象所在目录的父目录
        System.out.println(file.getAbsoluteFile().getParent());
        //是否可读
        System.out.println(file.canRead());
        System.out.println(file2.canRead());
        //是否是文件
        System.out.println(file1.isFile());
        System.out.println(file2.isFile());
        //是否是目录
        System.out.println(file1.isDirectory());
        System.out.println(file2.isDirectory());
        //最后修改时间
        long l = file2.lastModified();
        System.out.println("longToDate："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l)));
        System.out.println(file1.lastModified());
        System.out.println(file2.lastModified());
        //文件长度
        System.out.println(file2.length());
        //创建文件
        File newFile = new File(System.currentTimeMillis()+".txt");
        if (!newFile.exists()) {
            System.out.println(newFile.createNewFile());
//            Thread.sleep(4000);
            newFile.delete();
        }
        //listRoots静态方法列出所有磁盘根路径
        File[] roots = File.listRoots();
        System.out.println("====系统所有磁盘根路径如下====");
        for (File root : roots) {
            System.out.println(root);
        }
        //如果file对象是一个路径，list()将返回一个数组，如果路径下没有文件和子目录，则数组为empty
        //如果file对象是一个文件，或者file路径不存在，或者发生IO错误，则list()返回null
        String[] fileList = file.list();
        System.out.println("====当前路径下的所有文件和目录如下====");
        for (String fileName : fileList) {
            System.out.println(fileName);
        }
        //同上，只不过返回的是File类型数组
        File[] fileLists = file.listFiles();
        System.out.println("====当前路径下的所有文件和目录如下====");
        for (File filetmp : fileLists) {
            System.out.println(filetmp.getAbsolutePath());
        }
       /* 文件过滤器FilenameFilter接口
        如果FilenameFilter作为file.list()的参数传入，实现FilenameFilter接口的accept方法，可以实现文件过滤。
        accept方法有两个参数，dir和name，通常可以对name做条件过滤。*/
        String[] nameList = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt") || new File(name).isDirectory();
            }
        });
        System.out.println("====过滤条件后如下====");
        for (String filterName :  nameList) {
            System.out.println(filterName);
        }
    }
}
