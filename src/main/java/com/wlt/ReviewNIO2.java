package com.wlt;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * NIO2 JDK1.7及其之后才有的
 *
 新增Path接口，Paths工具类，Files工具类。 这些接口和工具类对NIO中的功能进行了高度封装，大大简化了文件系统的IO编程。
 基于异步Channel的IO
 * Created by wlt on 2018/2/24.
 */
public class ReviewNIO2 {
    @Test
    public void path(){
        //在传统java.io中， 文件和目录都被抽象成File对象， 即 File file = new File(".");
        //NIO.2中则引入接口Path代表与平台无关的路径，文件和目录都用Path对象表示
        //通过路径工具类Paths返回一个路径对象Path
        Path path = Paths.get(".");
        System.out.println("path里包含的路径数量：" + path.getNameCount());
        System.out.println("path的根路径： "+path.getRoot());
        //path的绝对路径
        //对比传统java.io, 取绝对路径 file.getAbsoluteFile()
        Path absolutePath = path.toAbsolutePath();
        System.out.println("path的绝对路径：");
        System.out.println(absolutePath);
        System.out.println("absolutePath的根路径： "+absolutePath.getRoot());
        System.out.println("absolutePath里包含的路径数量：" + absolutePath.getNameCount());
        System.out.println(absolutePath.getName(2));
        //以多个String来构建path
        Path path2 = Paths.get("g:", "publish" , "codes");
        System.out.println(path2);
    }
    @Test
    public void files() throws IOException{
        //将传统io读写文件高度封装之后，在NIO.2中拷贝文件只需要调用File工具类的copy()方法
        Files.copy(Paths.get("pom.xml"), new FileOutputStream("tmp2.txt"));
//是否为隐藏文件
        System.out.println("pom.xml是否为隐藏文件: "+ Files.isHidden(Paths.get("pom.xml")));
//一次性读取所有行 , 需要指定编码规则
        List<String> lines = Files.readAllLines(Paths.get("pom.xml"), Charset.forName("utf-8"));
        System.out.println(lines);
        for (String line:lines) {
            System.out.println(line);
        }
//文件大小
        System.out.println("tmp.txt文件大小为： "+Files.size(Paths.get("pom.xml")));
        List<String> poem = new ArrayList<>();
        poem.add("海阔凭鱼跃");
        poem.add("天高任鸟飞");
//直接将字符串数组写入文件
        Files.write(Paths.get("tmp3.txt"), poem, Charset.forName("utf-8"));
    }
}
