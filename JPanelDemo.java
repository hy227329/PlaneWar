package com.day10;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class JPanelDemo {
    public static void main(String[] args) {
        //创建一个JFrame对象
        JFrame windows = new JFrame();
        //创建MyJPanel对象
        MyJPanel p = new MyJPanel();
        //将面板添加到窗口中
        windows.add(p);
        //设置窗口的大小
        windows.setSize(300,400);
        //显示窗口
        windows.setVisible(true);
    }
}
//自定义一个类
class  MyJPanel extends JPanel{
    //重写自定义的绘画方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);//继承父类JPanel的paint方法
        //以下是绘画的自定义增强
        //设置g——画笔的字体属性
        //创建Font的对象
        Font font = new Font("宋体",Font.BOLD,16);
        g.setFont(font);
        //设置g——画笔的颜色
        Color color = new Color(0,0,255);//蓝色
        g.setColor(color);
      //  g.setColor(Color.green); //英文可以的颜色，在Color中直接定义好来静态常量
        //画字符串
        g.drawString("HelloWorld",20,30);

        //画图片
        //创建一个Image的对象
      //  g.drawImage()
        paintImage(g);
    }
    private void paintImage(Graphics g){
        BufferedImage img = null;
        try {
            img = ImageIO.read(MyJPanel.class.getResourceAsStream("background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img,0,0,this);
    }
}