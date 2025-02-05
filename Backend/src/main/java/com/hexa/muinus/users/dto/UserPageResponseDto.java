package com.hexa.muinus.users.dto;

import com.hexa.muinus.users.domain.user.FliUser;
import com.hexa.muinus.users.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPageResponseDto {
    private String userTelePhone;
    private String bank;
    private String accountNumber;
    private String accountName;

    public UserPageResponseDto(Users user, FliUser fliUser){
        this.userTelePhone = user.getTelephone();

        if(fliUser != null){
            this.bank = fliUser.getBank();
            this.accountNumber = fliUser.getAccountNumber();
            this.accountName = fliUser.getAccountName();
        } else {
            this.bank = null;
            this.accountNumber = null;
            this.accountName = null;
        }
    }
}
