package com.day10;
//敌机的类型，因为大敌机和小敌机以及小蜜蜂在被子弹打到生命值为0的时候，分数都会上升
public interface Enemy {
    int getScore();
}
