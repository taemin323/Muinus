package com.hexa.muinus.store.service;

import com.hexa.muinus.store.domain.Store;
import com.hexa.muinus.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store registerStore(Store store) {
        return storeRepository.save(store);
    }
}
