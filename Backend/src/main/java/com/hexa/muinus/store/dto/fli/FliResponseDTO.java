package com.hexa.muinus.store.dto.fli;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FliResponseDTO {
    private int storeId;
    private int userId;

    private String userAccount;
    private String userBank;
    private String accountName;
    private String itemName;
    private int quantity;
    private int price;
    private int sectionNumber;
    private int expirationDate;
}
