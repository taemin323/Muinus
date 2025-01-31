package com.hexa.muinus.users.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Integer userNo;

    @Column(name = "user_name", nullable = false, length = 200)
    private String userName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 20)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, columnDefinition = "ENUM('A', 'U')")
    private UserType userType = UserType.U;

    @Column(nullable = false)
    private Integer point;

    @Column
    private String refreshToken;

    public enum UserType {
        A, U
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
