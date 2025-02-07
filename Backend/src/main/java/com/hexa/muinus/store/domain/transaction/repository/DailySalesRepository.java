package com.hexa.muinus.store.domain.transaction.repository;

import com.hexa.muinus.store.domain.transaction.DailySales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailySalesRepository extends JpaRepository<DailySales, Integer> {
}
