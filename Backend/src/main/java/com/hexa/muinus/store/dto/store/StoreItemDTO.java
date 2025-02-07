package com.hexa.muinus.store.dto.store;

import com.hexa.muinus.store.domain.item.StoreItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    private int finalPrice;

    public StoreItemDTO(int storeItemId, int itemId, String itemName, String itemImageUrl, int quantity, int salePrice, int discountRate) {
        this.storeItemId = storeItemId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImageUrl = itemImageUrl;
        this.salePrice = salePrice;
        this.discountRate = discountRate;
        this.quantity = quantity;

        this.discountPrice = calculateDiscountPrice(salePrice, discountRate);
        this.finalPrice = salePrice - this.discountPrice;
    }

    /**
     * 할인 금액 계산
     * @param price
     * @param discountRate
     * @return
     */
    private int calculateDiscountPrice(Integer price, Integer discountRate) {
        return (price * discountRate) / 100; // 할인된 금액
    }
}
