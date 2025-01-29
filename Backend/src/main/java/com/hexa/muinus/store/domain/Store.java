package com.hexa.muinus.store.domain;

import com.hexa.muinus.users.domain.user.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no")
    private Integer storeNo;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Users user;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "location_x", nullable = false)
    private double locationX;

    @Column(name = "location_y", nullable = false)
    private double locationY;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(name = "store_image_url")
    private String storeImageUrl;

    @Column(name = "registration_no", nullable = false, length = 255)
    private String registrationNo;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "flimarket_yn", nullable = false, columnDefinition = "ENUM('Y', 'N')")
    private YesNo flimarketYn = YesNo.N;

    @Column(name = "flimarket_image_url")
    private String flimarketImageUrl;

    @Column(name = "flimarket_section_cnt", nullable = false)
    private Byte flimarketSectionCnt = 0;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public enum YesNo {
        Y, N
    }

}
