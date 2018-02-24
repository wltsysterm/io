package com.wlt;

import org.junit.Test;

import java.io.*;

/**
 * 复习对象流
 * Created by wlt on 2018/2/24.
 */
public class ReviewObject {
    @Test
    public void objectoutputstream() throws IOException{
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("objectoutputstream.txt"))
                ) {
            Seriable seriable = new Seriable("张三","黄皮肤");
            objectOutputStream.writeObject(seriable);
        }
    }
    @Test
    public void objectinputstream() throws IOException, ClassNotFoundException {
        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("objectoutputstream.txt"));
                ) {
            Seriable seriable = (Seriable) objectInputStream.readObject();
            System.out.println(seriable.toString());
        }
    }
}
class Seriable implements Serializable{
    private String name;
    private String skin;

    public Seriable(String name, String skin) {
        this.name = name;
        this.skin = skin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    @Override
    public String toString() {
        return "Seriable{" +
                "name='" + name + '\'' +
                ", skin='" + skin + '\'' +
                '}';
    }
}
