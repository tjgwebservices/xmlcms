package com.tjgwebservices.tjgxmlcms.form.shop;

public class ShopCart {

    private Integer id;
    private Integer customerId;
    private Integer paymentId;
    private String lastModified;
    private String datePurchased;
    private Integer cardStatus;
    private String cartDateFinished;
    private String currency;
    private Float currencyValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
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

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCartDateFinished() {
        return cartDateFinished;
    }

    public void setCartDateFinished(String cartDateFinished) {
        this.cartDateFinished = cartDateFinished;
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
