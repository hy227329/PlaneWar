package com.day10;

import java.awt.*;
import java.awt.image.BufferedImage;

//飞行物类，所有飞行物的父类
public abstract class FlyingObject {
    protected int x;
    protected int y;
    //定义图片的宽度和高度
    protected int width;
    protected int height;
    protected BufferedImage img;



    protected int life;

    // protected int life;
    //因为每个子类的移动方式都是不一样的，所以在父类中定义一个抽象的move()方法
    public abstract void move();
    //写一个生命值减小的方法
    public void minusLife(){
        this.life--;
    }

    //定义一个无参构造方法
   public FlyingObject(){

    }
    //定义一个有参构造方法
   /* public FlyingObject(int x,int y){
        this.x=x;
        this.y=y;
        img = Main.hero0;
        width = img.getWidth();
        height = img.getHeight();
    }*/
//获得get/set方法，get/srt方法用来获取私有变量的属性
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

}
