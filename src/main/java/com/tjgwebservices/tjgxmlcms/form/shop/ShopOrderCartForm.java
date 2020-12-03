package com.tjgwebservices.tjgxmlcms.form.shop;

public class ShopOrderCartForm {

    private Integer id;
    private Integer orderId;
    private Integer cartId;
    private Float orderPrice;
    private Float orderTax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Float getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(Float orderTax) {
        this.orderTax = orderTax;
    }
    
    
}
