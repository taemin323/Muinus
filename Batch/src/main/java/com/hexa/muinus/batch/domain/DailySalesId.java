package com.hexa.muinus.batch.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class DailySalesId implements Serializable {
    private LocalDate saleDate;
    private Integer storeNo;
    private Integer itemId;
}
