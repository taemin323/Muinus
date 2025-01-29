package com.hexa.muinus.store.domain.transaction;

import com.hexa.muinus.store.domain.item.StoreItem;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "guest_transaction_details")
public class GuestTransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer detailId;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 관계 설정
    @JoinColumn(name = "transaction_id", nullable = false)
    private GuestTransactions transactions;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 관계 설정
    @JoinColumn(name = "store_item_id", nullable = false)
    private StoreItem storeItem;

    @Column(name = "unit_price", nullable = false)
    private int unitPrice;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "sub_total", nullable = false)
    private int subTotal;

}
