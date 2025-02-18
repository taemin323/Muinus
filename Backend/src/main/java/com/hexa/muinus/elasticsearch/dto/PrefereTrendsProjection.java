package com.hexa.muinus.elasticsearch.dto;

public interface PrefereTrendsProjection {
    int getItemId();
    String getItemName();
    Double getTrendScore(); // 전체 구매 개수 대비 아이템 구매 개수
    int getCount(); // 사용자의 구매 횟수
}
