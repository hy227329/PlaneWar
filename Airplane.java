package com.day10;
//小敌机
//继承get/set方法，不能继承那些私有变量
public class Airplane extends FlyingObject implements Enemy{
    //定义一个速度成员变量并初始化
    private int speed = 3;
    private int score = 5;
    @Override
    public void move() {
        setY(getY()+speed);
    }
    public Airplane() {
        img = Main.airplane;
        width = img.getWidth();
        height = img.getHeight();
        x = (int)(Math.random()*300);
        y = -height;
        life = 1;
        }

    public int getScore() {
        return score;
    }

}
