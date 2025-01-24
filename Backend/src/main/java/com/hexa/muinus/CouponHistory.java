package com.hexa.muinus;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "coupon_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponHistory {

    @EmbeddedId
    private CouponHistoryId id;

    @ManyToOne
    @JoinColumn(name = "store_no", referencedColumnName = "store_no", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "coupon_id", insertable = false, updatable = false)
    private Coupon coupon;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // 기본 생성자
    public CouponHistory() {}

    public CouponHistory(CouponHistoryId id, int count, LocalDateTime expirationDate, LocalDateTime createdAt) {
        this.id = id;
        this.count = count;
        this.expirationDate = expirationDate;
        this.createdAt = createdAt;
    }
}

