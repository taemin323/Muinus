package com.hexa.muinus.users.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "guest_user")
public class GuestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guestNo;

    @Column(nullable = false, length = 200)
    private String guestName;

}
