package com.felix.entity;

public class OrderDetails {

    private Long orderDetailsID;
    private Long orderID;
    private Goods goods;
    private Integer goodsCount;

    public Long getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(Long orderDetailsID) {
        this.orderDetailsID = orderDetailsID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public OrderDetails(Long orderDetailsID, Long orderID, Goods goods, Integer goodsCount) {

        this.orderDetailsID = orderDetailsID;
        this.orderID = orderID;
        this.goods = goods;
        this.goodsCount = goodsCount;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsID=" + orderDetailsID +
                ", orderID=" + orderID +
                ", goods=" + goods +
                ", goodsCount=" + goodsCount +
                '}';
    }

    public OrderDetails() {

    }
}
