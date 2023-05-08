package com.by.projectorAndConsumer;

public class Program {
    //生产者
    /*
    * 生产产品，通过一个生产标记，判断是否需要生产产品。
    * 如果需要生产：生产产品并通知消费者消费。
    * 如果不需要生产：等待。
    * */
    //消费者
    /*
    * 作用是消费产品。
    * 消费逻辑：判断是否有足够的产品可以消费。
    * 如果可以消费：获取产品，进行消费。
    * 如果不可以消费，等待。
    * */
    public static void main(String[] args) {
        //实例化产品池
        ProductPool productPool = new ProductPool(15);
        //实例化生产者
        new Productor(productPool).start();
        //实例化消费者
        new Consumer(productPool).start();
    }
}
