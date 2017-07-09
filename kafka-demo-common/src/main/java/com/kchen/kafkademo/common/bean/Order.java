package com.kchen.kafkademo.common.bean;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 订单信息
 */
public class Order {

    // 订单号
    private String orderId = RandomStringUtils.random(18, true, true);

    // 订单创建时间
    private String createTime;

    private String price = Double.valueOf(RandomUtils.nextFloat(10, 20)).toString();

    public Order() {
        createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
