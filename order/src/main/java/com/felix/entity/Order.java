package com.felix.entity;

import java.util.Date;
import java.util.List;

public class Order {

    private Long orderid;
    private Long userid;
    private Date createDate;
    private Date updateDate;
    private List<OrderDetails> orderDetailsList;


    public Order(Long orderid, Long userid, Date createDate, Date updateDate, List<OrderDetails> orderDetailsList) {
        this.orderid = orderid;
        this.userid = userid;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.orderDetailsList = orderDetailsList;
    }

    @Override

    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", userid=" + userid +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public Order() {

    }
}
