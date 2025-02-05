package com.hexa.muinus.store.domain.item.repository;

import com.hexa.muinus.store.domain.item.RequestReceiving;
import com.hexa.muinus.store.domain.item.RequestReceivingId;
import com.hexa.muinus.store.dto.item.ItemResponseDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestReceivingRepository extends CrudRepository<RequestReceiving, RequestReceivingId> {

    @Query("SELECT new com.hexa.muinus.store.dto.item.ItemResponseDTO(r.item.itemName, COUNT(r)) " +
            "FROM RequestReceiving r " +
            "WHERE r.store.storeNo = :storeId " +
            "GROUP BY r.item.itemName")
    List<ItemResponseDTO> findItemRequestCountsByStoreId(@Param("storeId") Long storeId);
}
