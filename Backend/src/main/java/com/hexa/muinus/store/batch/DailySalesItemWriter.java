package com.hexa.muinus.store.batch;

import com.hexa.muinus.store.domain.transaction.DailySales;
import com.hexa.muinus.store.domain.transaction.repository.DailySalesRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DailySalesItemWriter implements ItemWriter<DailySales> {

    @Autowired
    private DailySalesRepository dailySalesRepository;

    @Override
    public void write(Chunk<? extends DailySales> items) throws Exception {
        // 일괄 저장
        dailySalesRepository.saveAll(items);
    }
}