package com.hexa.muinus.store.domain.store.repository;

import com.hexa.muinus.common.enums.YesNo;
import com.hexa.muinus.store.domain.store.Store;
import com.hexa.muinus.store.dto.StoreDTO;
import com.hexa.muinus.users.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    Optional<Store> findByLocationXAndLocationY(Double locationX, Double locationY);
    Optional<Store> findByUserAndStoreNo(Users user, Integer storeNo);

    @Query(value = """
        SELECT s.store_no AS storeNo, s.name AS name, s.location_x AS locationX, s.location_y AS locationY, s.address AS address, s.phone AS phone, 
               i.item_name AS itemName, si.sale_price AS salePrice, si.discount_rate AS discountRate, si.quantity AS quantity, s.flimarket_yn AS flimarketYn, 
               SQRT(POW((s.location_y - :y) * 111, 2) + POW((s.location_x - :x) * 111 * COS(RADIANS(:x)), 2)) * 1000 AS distance
        FROM store s
        JOIN store_item si ON s.store_no = si.store_no
        JOIN item i ON si.item_id = i.item_id
        WHERE SQRT(POW((s.location_y - :y) * 111, 2) + POW((s.location_x - :x) * 111 * COS(RADIANS(:x)), 2)) * 1000 <= :radius
        AND si.quantity > 0
        ORDER BY POW((s.location_y - :y) * 111, 2) + POW((s.location_x - :x) * 111 * COS(RADIANS(:x)), 2)
    """, nativeQuery = true)
    List<StoreSearchProjection> findStoresByItemIdAndRadius(@Param("itemId") Integer itemId,
                                                     @Param("x") Double x,
                                                     @Param("y") Double y,
                                                     @Param("radius") int radius);
    interface StoreSearchProjection {
        int getStoreNo();
        String getName();
        double getLocationX();
        double getLocationY();
        String getAddress();
        String getPhone();
        String getItemName();
        int getSalePrice();
        int getDiscountRate();
        int getQuantity();
        Character getFlimarketYn() ;
        double getDistance();
    }

    @Query("""
        SELECT new com.hexa.muinus.store.dto.StoreDTO(
            s.storeNo, s.user.userNo, s.name, 
            s.address, s.storeImageUrl, s.phone, s.flimarketYn
        )
        FROM Store s
        WHERE s.storeNo = :storeNo
    """)
    Optional<StoreDTO> findStoreDTOById(@Param("storeNo") int storeNo);

    @Modifying
    @Query(value = "INSERT INTO store (user_no, name, location_x, location_y, address, registration_no, flimarket_yn, flimarket_section_cnt) " +
            "VALUES (:userNo, :name, :longitude, :latitude, :address, :registrationNo, :flimarketYn, :fliMarketSectionCount)", nativeQuery = true)
    int saveStore(Integer userNo, String name, Double longitude, Double latitude, String address, String registrationNo, String flimarketYn, Byte fliMarketSectionCount);
}
