package com.example.myapplication;

public class SingletonClass {
    private static SingletonClass singletonClass;

    private SingletonClass(){}

    public static SingletonClass getInstance(){
        if(singletonClass==null){
            synchronized (SingletonClass.class){
                if(singletonClass==null){
                    singletonClass=new SingletonClass();
                }
            }
        }
        return singletonClass;
    }

}
