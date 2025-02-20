package com.hexa.muinus.store.dto.store;


import com.hexa.muinus.store.domain.store.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreMapDTO {

    private int storeNo;
    private String storeName;
    private Double lon; // 위도
    private Double lat; // 경도
    private Double distance; // 검색 위치와의 거리(m)

    public StoreMapDTO(StoreRepository.StoreMapProjection projection) {
        this.storeNo = projection.getStoreNo();
        this.storeName = projection.getName();
        this.lon = projection.getLocationX();
        this.lat = projection.getLocationY();
        this.distance = projection.getDistance();
    }
}
