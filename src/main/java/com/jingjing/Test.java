package com.jingjing;

public class Test {
    public static void main(String[] args){
        String path = "C:\\Users\\Administrator\\Desktop\\新建文件夹\\extract.txt";
        int index = path.lastIndexOf(".");

        String tmpPath = path.substring(0,index)+"-"+100+path.substring(index);
        System.out.println(tmpPath);
    }
}
