package com.hexa.muinus.store.repository;

import com.hexa.muinus.store.domain.Store;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Modifying
    @Query(value = "INSERT INTO store (user_no, name, location, address, registration_no, flimarket_yn, flimarket_section_cnt) " +
            "VALUES (:userNo, :name, POINT(:longitude, :latitude), :address, :registrationNo, :flimarketYn, :fliMarketSectionCount)", nativeQuery = true)
    public int saveStore(Integer userNo, String name, Double longitude, Double latitude, String address, String registrationNo, String flimarketYn, Byte fliMarketSectionCount);
}
