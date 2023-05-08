package com.by.projectorAndConsumer;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ProductPool {
    //存储所有的产品的集合，生产者生产产品，往这个集合中添加元素；消费者消费产品，从这个集合中取出元素。
    private List<Product> productList;
    //产品池中产品的最大数量。
    private int maxSize = 0;

    public ProductPool(int maxSize){
        //对产品进行实例化
        this.productList = new LinkedList<Product>();
        this.maxSize = maxSize;
    }
    /*
    * 生产者将生产好的产品放入产品池
    * 同步方法的锁是this
    * */
    public synchronized void push(Product product){
        //判断是否需要再生产产品
        if(this.productList.size() == maxSize){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //将产品添加到集合中
        this.productList.add(product);
        this.notifyAll();
    }
    /*
    * 消费者从产品池中取出一件产品消费
    * */
    public synchronized Product pop(){
        //判断是否还有产品再去消费
        if(this.productList.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //从商品池中取出意见产品
        Product product = this.productList.remove(0);
        //通知其他人取出了一件商品
        this.notifyAll();
        return product;
    }
}
