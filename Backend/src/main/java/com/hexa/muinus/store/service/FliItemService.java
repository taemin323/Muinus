package com.hexa.muinus.store.service;

import com.hexa.muinus.common.exception.item.FliItemNotFoundException;
import com.hexa.muinus.common.exception.item.SectionFliItemNotFoundException;
import com.hexa.muinus.store.domain.item.FliItem;
import com.hexa.muinus.store.domain.item.repository.FliItemRepository;
import com.hexa.muinus.store.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FliItemService {

    private final FliItemRepository fliItemRepository;

    @Transactional
    public FliItem findFliItemByStoreAndSectionId(Store store, Integer sectionId) {
        return fliItemRepository.findByStoreAndSectionId(store, sectionId)
                .orElseThrow(() -> new SectionFliItemNotFoundException(store.getStoreNo(), sectionId));
    }

    @Transactional
    public FliItem findFliItemByStoreAndFliItemId(Store store, Integer fliItemId) {
        return fliItemRepository.findByStoreAndFliItemId(store, fliItemId)
                .orElseThrow(() -> new FliItemNotFoundException(store.getStoreNo(), fliItemId));
    }

}
