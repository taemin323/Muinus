package com.hexa.muinus;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReceivingId implements Serializable {
    private Integer storeNo;
    private Integer productId;
    private Integer userNo;
}

