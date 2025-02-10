package com.hexa.muinus.store.dto.store;

public interface StoreMapProjection {
    int getStoreNo();
    String getName();
    double getLocationX();
    double getLocationY();
    String getAddress();
    String getPhone();
    String getItemName();
    int getSalePrice();
    int getDiscountRate();
    int getQuantity();
    Character getFlimarketYn() ;
    double getDistance();
}