package com.tjgwebservices.tjgxmlcms.model.shop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "ShopPaymentType")
public class ShopPaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String paymentTypeDescription;

    public ShopPaymentType(){}
    
    
    public ShopPaymentType(String paymentTypeDescription) {
        this.paymentTypeDescription = paymentTypeDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentTypeDescription() {
        return paymentTypeDescription;
    }

    public void setPaymentTypeDescription(String paymentTypeDescription) {
        this.paymentTypeDescription = paymentTypeDescription;
    }
    
}
