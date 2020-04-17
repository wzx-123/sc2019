package com.xzsd.pc.order.entity;

import java.util.List;

/**
 * 封装数据实体类
 * @author wzx
 * @date 2020-03-27
 */
public class OrderDetailsList {
    private List<OrderDetails> orderDeepenList;

    public List<OrderDetails> getOrderDeepenList() {
        return orderDeepenList;
    }

    public void setOrderDeepenList(List<OrderDetails> orderDeepenList) {
        this.orderDeepenList = orderDeepenList;
    }
}