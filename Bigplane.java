package com.day10;
//继承get/set方法，不能继承那些私有变量
//大敌机
public class Bigplane extends FlyingObject implements Enemy,Award{
    public int blood = 3;
    private int score = 7;
    //定义一个速度成员变量并初始化
    private int speed = 3;
    private int awardType = (int)(Math.random()*2);
    @Override
    public void move() {
        setY(getY()+speed);
    }
    //无参构造方法
    public Bigplane(){
        img = Main.bigplane;
        width = img.getWidth();
        height = img.getHeight();
        x = (int)(Math.random()*300);
        y = -height;
        life = 4;
        awardType = getAwardType();

    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getScore() {
        return score;
    }



    @Override
    public int getAwardType() {
        if((int)(Math.random()*2) == 0){
            return ADD_LIFE;
        }
        else{
            return ADD_DOUBLEFIRE;
        }
    }
}
