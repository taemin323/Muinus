package com.hexa.muinus.store.dto.store;

public interface StoreBasicProjection {
    int getStoreNo();
    String getName();
    double getLocationX();
    double getLocationY();
    String getAddress();
    String getPhone();
    Character getFlimarketYn();
}