package com.day10;

import javax.swing.*;

//import static javax.swing.JFrame.*;

public class JFrameDemo {
    public static void main(String[] args) {
        //无参构造方法——创建一个原始的很小的窗口
        JFrame windows = new JFrame();
        //设置窗口标题
        windows.setTitle("飞机大战");
        //设置窗口的大小
        windows.setSize(300,500);
        //设置窗口的默认关闭选项
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口位置居中
        windows.setLocationRelativeTo(null);


        //显示窗口
        windows.setVisible(true);

    }
}
