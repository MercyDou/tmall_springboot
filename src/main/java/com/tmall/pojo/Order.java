package com.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tmall.service.OrderService;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private int uid;

    private User user;

    private String OrderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private Data createDate;
    private Data payDate;
    private Date confirmDate;
    private Date deliveryDate;
    private String status;

    @Transient
    private List<OrderItem> orderItems;
    @Transient
    private float total;
    @Transient
    private int totalNumber;
    @Transient
    private String statusDesc;

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc() {
        if(null!=statusDesc)
            return statusDesc;
        String desc ="未知";
        switch(status){
            case OrderService.waitPay:
                desc="待付";
                break;
            case OrderService.waitDelivery:
                desc="待发";
                break;
            case OrderService.waitConfirm:
                desc="待收";
                break;
            case OrderService.waitReview:
                desc="等评";
                break;
            case OrderService.finish:
                desc="完成";
                break;
            case OrderService.delete:
                desc="刪除";
                break;
            default:
                desc="未知";
        }
        statusDesc = desc;
        return statusDesc;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Data getCreateDate() {
        return createDate;
    }

    public Data getPayDate() {
        return payDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public int getUid() {
        return uid;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public float getTotal() {
        return total;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public String getPost() {
        return post;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getStatus() {
        return status;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public void setCreateDate(Data createDate) {
        this.createDate = createDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setPayDate(Data payDate) {
        this.payDate = payDate;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
