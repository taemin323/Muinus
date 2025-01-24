package com.hexa.muinus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fli_item")
public class FliItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fliItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 관계 설정
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @Column(nullable = false, length = 200)
    private String fliItemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int sectionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('PENDING', 'APPROVED', 'REJECTED', 'SELLING', 'SOLD') DEFAULT 'pending'")
    private FliItemStatus status;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime applicationDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime expirationDate;

}
