package com.by.sourceconflict;

public class SingletonTest {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            Boss.getInstance2();
        };
        for(int i=0;i<100;i++){
            new Thread(runnable).start();
        }
    }
}
class Boss{
    private Boss(){
        System.out.println("一个Boss对象被实例化了");
    }
    private static Boss Instance = null;
    //多线程环境下会出现问题，a线程运行到new Boss时放弃时间片，此时b线程也运行到new Boss则不再单例
    public static Boss getInstance(){
        if(Instance == null){
            Instance = new Boss();
        }
        return Instance;
    }
    public static Boss getInstance1(){
        synchronized (""){
            if(Instance == null){
                Instance = new Boss();
            }
        }
        return Instance;
    }
    //类锁
    public synchronized static Boss getInstance2(){
        if(Instance == null){
            Instance = new Boss();
        }
        return Instance;
    }

}
