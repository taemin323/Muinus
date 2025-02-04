package com.hexa.muinus.store.dto.store;

import com.hexa.muinus.common.enums.YesNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreSearchDTO {
    private Integer storeNo;
    private String name;
    private Double locationX;
    private Double locationY;
    private String address;
    private String phone;
    private String itemName;
    private Integer salePrice;
    private Integer discountRate;
    private Integer discountPrice; // 할인된 금액
    private Integer finalPrice; // 최종 할인 적용된 금액
    private Integer quantity;
    private YesNo flimarketYn;
    private Double distance; // 검색 위치와의 거리(m)

    /**
     * DB 조회 결과를 바탕으로 할인된 금액, 최종 금액 계산해서 생성
     * @param storeNo
     * @param name
     * @param locationX
     * @param locationY
     * @param address
     * @param phone
     * @param itemName
     * @param salePrice
     * @param discountRate
     * @param quantity
     * @param flimarketYn
     */
    public StoreSearchDTO(Integer storeNo, String name, Double locationX, Double locationY,
                          String address, String phone, String itemName, Integer salePrice, Integer discountRate,
                          Integer quantity, Character flimarketYn, Double distance) {
        this.storeNo = storeNo;
        this.name = name;
        this.locationX = locationX;
        this.locationY = locationY;
        this.address = address;
        this.phone = phone;
        this.itemName = itemName;
        this.salePrice = salePrice;
        this.discountRate = discountRate;
        this.quantity = quantity;
        this.distance = distance;

        this.flimarketYn = flimarketYnConvertYesNo(flimarketYn);

        this.discountPrice = calculateDiscountPrice(salePrice, discountRate);
        this.finalPrice = salePrice - this.discountPrice;
    }

    /**
     * convert from Character to YesNo
     * @param flimarketYn
     * @return
     */
    private YesNo flimarketYnConvertYesNo(Character flimarketYn) {
        switch (flimarketYn) {
            case 'Y': return YesNo.Y;
            case 'N': return YesNo.N;
        }
        return YesNo.N;
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
