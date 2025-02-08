package com.hexa.muinus.batch.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class DailySales {
    private DailySalesId id;
    private Integer totalQuantity;
    private Integer totalAmount;

    public LocalDate getSaleDate() {
        return id != null ? id.getSaleDate() : null;
    }

    public int getStoreNo() {
        return id != null ? id.getStoreNo() : null;
    }

    public int getItemId() {
        return id != null ? id.getItemId() : null;
    }
}
