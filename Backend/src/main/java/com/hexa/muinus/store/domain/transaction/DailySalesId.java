package com.hexa.muinus.store.domain.transaction;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DailySalesId implements Serializable {

    private Integer storeNo;
    private Integer itemId;
    private LocalDate saleDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailySalesId that = (DailySalesId) o;
        return Objects.equals(storeNo, that.storeNo) && Objects.equals(itemId, that.itemId) && Objects.equals(saleDate, that.saleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeNo, itemId, saleDate);
    }


}
