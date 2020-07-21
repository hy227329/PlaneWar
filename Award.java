package com.day10;
//奖励的类型,大敌机和小蜜蜂的生命值为0之后都会获得不同的奖励，所以将这两种一同归为奖励类型
public interface Award {
    public static final int ADD_LIFE = 0;
    public static final int ADD_DOUBLEFIRE = 1;
    int getAwardType();
}
