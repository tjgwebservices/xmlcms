package com.tjgwebservices.tjgxmlcms.form.shop;

public class ShopPaymentForm {

    private Integer id;
    private Integer paymentType;
    private String ccType;
    private String ccOwner;
    private String ccNumber;
    private String ccExpires;
    private String lastModified;
    private String datePurchased;
    private Integer orderStatus;
    private String orderDateFinished;
    private String currency;
    private Float currencyValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcOwner() {
        return ccOwner;
    }

    public void setCcOwner(String ccOwner) {
        this.ccOwner = ccOwner;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpires() {
        return ccExpires;
    }

    public void setCcExpires(String ccExpires) {
        this.ccExpires = ccExpires;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDateFinished() {
        return orderDateFinished;
    }

    public void setOrderDateFinished(String orderDateFinished) {
        this.orderDateFinished = orderDateFinished;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Float currencyValue) {
        this.currencyValue = currencyValue;
    }
    
    
}
