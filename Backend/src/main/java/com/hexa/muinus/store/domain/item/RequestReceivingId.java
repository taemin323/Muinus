package com.hexa.muinus.store.domain.item;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReceivingId implements Serializable {
    private Integer storeNo;
    private Integer productId;
    private Integer userNo;
}

