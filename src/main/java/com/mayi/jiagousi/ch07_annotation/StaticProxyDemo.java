package com.mayi.jiagousi.ch07_annotation;


interface Seller{
    void sell();
}
class Wo implements Seller{
    @Override
    public void sell() {
        System.out.println("我要卖房");
    }
}

class Zhongjie implements Seller{
    private Seller seller;

    public Zhongjie(Seller seller){
        this.seller = seller;
    }

    public void sell(){
        System.out.println("sell开始前");
        seller.sell();
        System.out.println("sell结束了");
    }

}

public class StaticProxyDemo {

    public static void main(String[] args) {
        new Zhongjie(new Wo()).sell();
    }
}
