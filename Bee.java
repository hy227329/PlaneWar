package com.day10;
//继承get/set方法，不能继承那些私有变量
//蜜蜂
public class Bee extends FlyingObject implements Enemy,Award{
    //定义x和y的速度并初始化
    private int x_speed = 3;
    private int y_speed = 1;
    private int score = 6;
    private int awardType = (int)(Math.random()*2);
    @Override
    public void move() {
        //x坐标先变大，y坐标变大
        setX(getX()+x_speed);
        setY(getY()+y_speed);
        //当x坐标到最大值时，x减小，y继续增大
        //x坐标的最大值即窗口的宽度减去图片的宽度
        if(getX() >= Main.WIDTH - getWidth()){
            x_speed = -3;
        }else if(getX() <= 0){
            x_speed = 3;//当x减到最小值0时，x又开始增加
        }

    }
    public Bee(){
        img = Main.bee;
        width = img.getWidth();
        height = img.getHeight();
        x=(int)(Math.random()*300);
        y=-height;
        life = 2;
        awardType = getAwardType();
    }

    public int getScore() {
        return score;
    }



    public void setAwardType(int awardType) {
        this.awardType = awardType;
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
