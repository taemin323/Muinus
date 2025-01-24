package com.hexa.muinus.users.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "oauth_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OauthUser {

    @Id
    @Column(name = "user_no")
    private Integer userNo;

    // Users의 userNo와 동일. 1:1 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @MapsId
    private Users user;

    @Column(name = "user_name", nullable = false, length = 200)
    private String userName;

    @Column(nullable = false, length = 200)
    private String email;
}

