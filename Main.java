package com.day10;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import static com.day10.Award.ADD_LIFE;

public class Main extends JPanel{
    public static BufferedImage back;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage bee;
    public static BufferedImage airplane;
    public static BufferedImage bigplane;
    public static BufferedImage pause;
    public static BufferedImage start;
    public static BufferedImage over;

    public static BufferedImage bullet;
    static {

        try {
            back = ImageIO.read(Main.class.getResourceAsStream("shooter/background.png"));
            hero0 = ImageIO.read(Main.class.getResourceAsStream("shooter/hero0.png"));
            hero1 = ImageIO.read(Main.class.getResourceAsStream("shooter/hero1.png"));
            bee = ImageIO.read(Main.class.getResourceAsStream("shooter/bee.png"));
            airplane = ImageIO.read(Main.class.getResourceAsStream("shooter/airplane_ember0.png"));
            bigplane = ImageIO.read(Main.class.getResourceAsStream("shooter/bigplane_ember0.png"));
            bullet = ImageIO.read(Main.class.getResourceAsStream("shooter/bullet.png"));
            pause = ImageIO.read(Main.class.getResourceAsStream("shooter/pause.png"));
            start = ImageIO.read(Main.class.getResourceAsStream("shooter/start.png"));
            over = ImageIO.read(Main.class.getResourceAsStream("shooter/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  g.drawImage(img,0,0,this);
    }
//主类直接继承JPanel
    //定义英雄机、小蜜蜂、敌机、子弹的成员变量，使得其他方法也可以使用，因为局部变量只提供给局部变量所在的方法使用
    Hero hero = new Hero();
  /*  Bee b = new Bee();
    Airplane a = new Airplane();
    Bigplane big = new Bigplane();*/
    Bullet bu = new Bullet(200,100);

    @Override
    public void paint(Graphics g) {
        //清除绘画内容
        super.paint(g);//调用JPanel中的paint方法

        //画一个背景图片
        g.drawImage(back,0,0,this);
        //画一个字符串
       // g.drawString("Score",5,10);
       // g.drawString("Life",5,20);
        //设置画笔g的字体属性
        Font font = new Font("宋体",Font.BOLD,16);
        g.setFont(font);
        //测试-----
        //画一个英雄机
        //创建一个英雄机对象
      //  g.drawImage(hero.getImg(),hero.getX(),hero.getY(),this);//this就是当前对象
     //   g.drawImage(bu.getImg(),bu.getX(),bu.getY(),this);//this就是当前对象
      //  paintCreatFlyingObject(g);//画飞行物
       // paintShootAction(g);//画子弹
        //画状态
        if(state == START){
            g.drawImage(start,0,0,this);
        }else if(state == PAUSE){
            g.drawImage(pause,0,0,this);
        //    paintCreatFlyingObject(g);//画飞行物
        //    paintShootAction(g);//画子弹
        }else if(state == GAMEOVER){
            g.drawImage(over,0,0,this);
        }else if(state == RUNNING){
            g.drawImage(hero.getImg(),hero.getX(),hero.getY(),this);//this就是当前对象
            paintCreatFlyingObject(g);//画飞行物
            paintShootAction(g);//画子弹
            g.drawString("Score:" + sumScore,5,10);
            g.drawString("Life:" + hero.life,5,20);
        }
    }
    //画所有新生成的飞行物
    private void paintCreatFlyingObject(Graphics g){
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject f = flyings.get(i);
            g.drawImage(f.getImg(),f.getX(),f.getY(),this);
        }
    }
    //画所有发射的子弹
    private void paintShootAction(Graphics g){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            g.drawImage(b.getImg(),b.getX(),b.getY(),this);
        }
    }
    public static final int WIDTH = 400;
    public static final int HEIGHT = 650;
    //因为final修饰的成员变量是放在静态方法中使用的，所以定义的变量因该是静态成员变量
    public static void main(String[] args) {
        //创建一个窗口对象
        JFrame windows = new JFrame();
        //设置窗口的大小
        windows.setSize(WIDTH,HEIGHT);
        /*由于400和650是给出的一个具体的数，后面可能不知道这个数具体表现得是什么。
        所以先给定义一个变量，但是因为是具体的值，所以是final修饰的变量，即常量，*/
        //设置窗口的默认关闭选项
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //多态
        //JPanel main = new Main();
        //创建一个Main对象
        Main main = new Main();
        //将面板添加到窗口中
        windows.add(main);
        //显示窗口
        windows.setVisible(true);
        main.action();//调用定时器
    }
    //游戏开始的方法，定时且重复的代码——>定时器（Timer)，在工具包下面，
    //创建一个定时器对象
    private java.util.Timer timer = new java.util.Timer();
    public void action(){
        //TimerTask——>需要重复执行的代码——>在使用TimerTask的时候需要创建一个新的对象
        //long——>定时器什么时候开始使用，单位毫秒
        //long——>时间间隔，每隔多少时间重复执行一次代码
        //方法
        //局部内部类可以直接优化成匿名内部类
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //1~6加一个游戏的判断
                //当状态为RUNNING的时候，移动鼠标，英雄机随着鼠标移动，不断产生新的飞行物，当鼠标移出窗口的时候，状态切换为PAUSE,y
                //游戏暂停
                if(state == RUNNING) {
                    //需要重复执行的代码
                    //1.生成的飞行物
                    creatFlyingObject();
                    //hero.move();
                    // 编译错误，找不到变量hero，因为hero定义的是局部变量，只能在它本身所在的方法中使用，所以此时需要将其定义为成员变量
                    //2.飞行物的移动
                    hero.move();
                    moveFlyingObject();
                    //  b.move();
                    //   a.move();
                    //  big.move();
                    //  bu.move();
                    //3.发射子弹
                    // hero.shoot();//调用shoot方法，并作为参数传递到shootAction方法中
                    hero.shoot();
                    shootAction();
                    //4.移动所有子弹
                    moveBullets();
                    //5.判断飞行物和子弹越界
                    outOfBoundsAction();
                    //6.判断碰撞
                    Bang();
                }
                //7.英雄机移动 - move - 鼠标控制
                //8.英雄机和敌机碰撞
                //9.判断游戏是否结束
                //10.重画，重新调用paint方法
                repaint();
            }
        }, 1000, 20);
        //写一个匿名内部类来实现对鼠标的监听,MouseAdapter继承了addMouseListener、addMouseMotionListener等接口的继承
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //判断游戏状态，改变游戏状态
                if(state == START){
                    state = RUNNING;
                }else if(state == GAMEOVER){
                    state=START;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                //判断游戏状态，改变游戏状态
                if(state == PAUSE){
                    state=RUNNING;
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                //判断游戏状态，改变游戏状态
                if(state == RUNNING){
                    state = PAUSE;
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == RUNNING) {
                    //定义鼠标的x、y坐标
                    int mouse_x = e.getX();
                    int mouse_y = e.getY();
                    //改变英雄机的坐标，使得英雄机跟随着鼠标移动
                    hero.setX(mouse_x);
                    hero.setY(mouse_y);

                    //将坐标改变后的飞行物再画一遍
                    repaint();
                }
            }
        };
        //this指的是当前对象main,然后调用接口addMouseListener、addMouseMotionListener，并将匿名内部类的中的内容同adapter传递给接口。
        //最后通过action方法被主方法调用
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
    }
    private int flyingsIndex = 0;//生成新的飞行物的频率
    //定义一个ArrayList集合
    private ArrayList<FlyingObject> flyings = new ArrayList<FlyingObject>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    //固定的四种游戏状态
    private final int START =0;
    private final int PAUSE=1;
    private final int RUNNING=2;
    private final int GAMEOVER=3;

    //定义当前的游戏状态
    private int state = START;


    FlyingObject fly;
    //生成新的飞行物（小蜜蜂、敌机、大敌机）
    private void creatFlyingObject(){
        flyingsIndex ++;
        //定义一个随机数
        int ran = (int)(Math.random()*20);//产生一个0~20的随机数
        //定义一个父类类型的变量
        if(flyingsIndex % 30 == 0) {
            //给定生成飞行物的概率，并将新生成的飞行物赋值给父类类型的变量
            if (ran == 0) {
                fly = new Bee();
            } else if (ran == 2 || ran == 3) {
                fly = new Bigplane();
            } else {
                fly = new Airplane();
            }
            //将新生成的飞行物添加带ArrayList集合之中
            flyings.add(fly);
        }
    }
    //飞行物的移动
    private void moveFlyingObject(){
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject f = flyings.get(i);
            f.move();
        }
    }
    //子弹的移动
    private void moveBullets(){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bs = bullets.get(i);
            bs.move();
        }
    }
    //判断飞行物是否越界
    private void outOfBoundsAction(){
        //敌机、小蜜蜂
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject f = flyings.get(i);
            if(f.getY() > Main.HEIGHT){
                flyings.remove(i);
                i--;//在移除越界的的飞行物之后再返回原来被移除飞行物的位置
            }
        }
        //
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bs = bullets.get(i);
            if(bs.getY() <= -getHeight()){
                bullets.remove(i);
                i--;
            }

        }
    }
    private int bulletsIndex = 0;//生成新的飞行物的频率
    //发射子弹
    private void shootAction(){
        bulletsIndex ++;
        if(bulletsIndex % 10 == 0) {
            Bullet[] bs = hero.shoot();//调用shoot方法并传递返回值
            for (int i = 0; i < bs.length; i++) {
                bullets.add(bs[i]);//将发射的所有子弹添加到集合的bullets中
            }
        }
    }
    //玩家得分
    public int sumScore;
    //判断飞行物与子弹是否碰撞
    private void Bang(){
        for (int i = 0; i < bullets.size(); i++) {
            for(int j=0;j<flyings.size();j++){
                Bullet b = bullets.get(i);
                FlyingObject f = flyings.get(j);
                //获得子弹的的x和y坐标
                int x = b.getX();
                int y = b.getY();
                ////获得飞行物的的x和y坐标
                int x1 = f.getX();
                int y1 = f.getY();
                if(b.getX() >= f.getX() && x <= x1+f.width && y >= y1 && y <= y1+f.height) {
                    f.minusLife();
                    if (f.getLife() <= 0) {
                        if (f instanceof Enemy) {
                            Enemy e = (Enemy) f;
                            flyings.remove(j);
                            hero.addScore(e.getScore());
                            sumScore = hero.getScore();

                        }
                        if (f instanceof Award) {
                            Award award = (Award) f;
                            if(award.getAwardType() == Award.ADD_LIFE) {
                                hero.addLife();
                            }
                            else{
                               hero.addDoubleFire(60);
                            }
                        }
                    }
                    //flyings.remove(j);
                    bullets.remove(i);
                    i--;
                    break;
                }
            }
        }
    }
    //判断英雄机与飞行物相撞
    public void hitAction(){
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject f = flyings.get(i);
            if(hero.hit(f)){
                hero.minusLife();
                hero.addDoubleFire(0);
                flyings.remove(i);
            }

        }
    }
}
//方法
//TimerTask是一个抽象类，不能直接创建一个对象，需要用他的子类来创建一个对象并调用TimerTask
//timer.schedule(new TimerTask(),2000,1000);
//创建一个局部内部类作为子类继承TimerTask类
       /* class MyTimerTask extends TimerTask{
            @Override
            public void run() {
                //重复执行的代码
            }
        }
        timer.schedule(new MyTimerTask(),2000,1000);

    }*/
/*
        //测试-----
        //画一个英雄机
        //创建一个英雄机对象
        g.drawImage(hero.getImg(),hero.getX(),hero.getY(),this);//this就是当前对象

        //创建一个小蜜蜂
        g.drawImage(b.getImg(),b.getX(),b.getY(),this);

        //创建一个小敌机
        g.drawImage(a.getImg(),a.getX(),a.getY(),this);

        //创建一个大敌机
        g.drawImage(big.getImg(),big.getX(),big.getY(),this);

        //画一个子弹
        g.drawImage(bu.getImg(),bu.getX(),bu.getY(),this);*/


//自己写的源代码，可以继续优化
/*
 //判断飞行物与子弹是否碰撞
    private void Bang(){
        for (int i = 0; i < bullets.size(); i++) {
            for(int j=0;j<flyings.size();j++){
                Bullet b = bullets.get(i);
                FlyingObject f = flyings.get(j);
                //获得子弹的的x和y坐标
                int x = b.getX();
                int y = b.getY();
                ////获得飞行物的的x和y坐标
                int x1 = f.getX();
                int y1 = f.getY();
                if(b.getX() >= f.getX() && x <= x1+f.width && y >= y1 && y <= y1+f.height){
                    if(f instanceof Airplane){
                        Airplane e= (Airplane)f;
                        e.life -=1;
                        if(e.life <= 0){
                            score += e.getScore();
                            flyings.remove(j);
                        }
                        bullets.remove(i);
                        i--;
                        break;
                    }
                    if(f instanceof Bigplane){
                        Bigplane e = (Bigplane)f;
                        e.blood -=1;
                        if(e.blood <= 0){
                            score += e.getScore();
                            flyings.remove(j);
                        }
                        bullets.remove(i);
                        i--;
                        break;
                    }
                    if(f instanceof Bee){
                        Bee e = (Bee) f;
                        e.life -=1;
                        if(e.life<= 0){
                            score += e.getScore();
                            flyings.remove(j);
                        }
                        bullets.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }
 */