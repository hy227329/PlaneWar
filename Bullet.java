package com.day10;
//继承get/set方法，不能继承那些私有变量
//子弹
public class Bullet extends FlyingObject{
    //定义一个子弹的速度
    private int speed = 4;
    @Override
    public void move() {
        setY(getY()-speed);//定义speed变量是因为如果给具体的数字可能有人不懂数字表示的是什么意思，所以给定义一个变量来表示
    }
    public Bullet(int x,int y){
        img = Main.bullet;
        width = img.getWidth();
        height = img.getHeight();
        this.x=x;
        this.y=y;
    }
}
