package com.hexa.muinus.store.domain.item.dto;

import com.hexa.muinus.store.domain.item.StoreItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreItemDTO {
    private int storeItemId;
    private int itemId;
    private String itemName;
    private String itemImageUrl;
    private int quantity;
    private int salePrice;
    private int discountRate;
    private int discountPrice;
}
