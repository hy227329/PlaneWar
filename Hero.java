package com.day10;

import java.awt.image.BufferedImage;

//继承get/set方法，不能继承那些私有变量
//英雄机
public class Hero extends FlyingObject{
    //定义英雄机的获得分数
    private int score;




    //如果不想用数组可以Arraylist集合
    //ArrayList<Bullet> bullet;

    //无参构造方法
    public Hero(){
        x=150;
        y=500;
        img = Main.hero0;
        width = img.getWidth();
        height = img.getHeight();
        doubleFire = 0;
        life = 4;

    }
    //有参构造方法
   /*  public Hero(){
         super(300,500);
         img = Main.hero0;
         width = img.getWidth();
         height = img.getHeight();
     }*/
    //定义一个计数器
    private int count = 0;
    //也可以定义一个数组
    private BufferedImage[] herosImage = {Main.hero0,Main.hero1};
    @Override
    public void move() {
        //切换图片
        //奇偶切换取余
        count++;
        setImg(herosImage[count % 2]);
       /* if(count++ % 2 ==0){
            setImg(Main.hero0);
        }else{
            setImg(Main.hero1);
        }*/
    }
    public void addScore(int score){
        this.setScore(this.getScore()+score);
    }
    public void addLife(){
        this.setLife(this.getLife()+1);
    }
   protected int doubleFire;
   public void addDoubleFire( int n){
        doubleFire =n;
   }

    //发射子弹,生成新的子弹并返回，因为发射的子弹可能是一个也可能是两个，所以直接定义一个数组
    public Bullet[] shoot(){
        Bullet[] bullets;
        if(doubleFire == 0) {
            bullets = new Bullet[1];//生成一个新的子弹并初始化
            //子弹的坐标，x坐标等于子弹原来的坐标+飞机图片宽度的二分之一，y坐标就是英雄机的y坐标
            bullets[0] = new Bullet(this.getX() + getWidth() / 2, this.getY());//bullets[0]表示子弹类型，将bullets[0]初始化
            return bullets;
        }else{
            bullets = new Bullet[2];
            bullets[0] = new Bullet(this.getX() + getWidth() / 4, this.getY());
            bullets[1] = new Bullet(this.getX() + getWidth() / 4*3, this.getY());
            doubleFire--;
            return bullets;
        }
       // return bullets;
    }

    public boolean hit( FlyingObject f) {
        //获得飞行物的的x和y坐标,即敌机的x、y坐标
        int f_x = f.getX();
        int f_y = f.getY();
        int f_width = f.getWidth();
        int f_height = f.getHeight();

        int x1 = f_x - this.width/2;
        int y1 = f_y - 1 / 2 * this.height;
        int x2 = f_x + f_width + 1 / 2 * this.width;
        int y2 = f_y + f_height + 1 / 2 * this.height;
        int x = this.x + 1 / 2 * this.width;
        int y = this.y + 1 / 2 * this.height;
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDoubleFire() {
        return doubleFire;
    }

    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }
}
