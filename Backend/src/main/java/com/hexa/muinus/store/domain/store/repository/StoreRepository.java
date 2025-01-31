package com.hexa.muinus.store.domain.store.repository;

import com.hexa.muinus.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
