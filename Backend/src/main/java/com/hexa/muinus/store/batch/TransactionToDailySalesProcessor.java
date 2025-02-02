package com.hexa.muinus.store.batch;

import com.hexa.muinus.store.domain.transaction.TransactionDetails;
import com.hexa.muinus.store.domain.transaction.DailySales;
import com.hexa.muinus.store.domain.transaction.DailySalesId;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionToDailySalesProcessor implements ItemProcessor<TransactionDetails, DailySales> {

    private Map<String, DailySales> dailySalesMap = new HashMap<>();

    @Override
    public DailySales process(TransactionDetails item) throws Exception {
        // storeNo와 itemId로 key를 만들어서 그룹핑
        String key = item.getStoreItem().getStore().getStoreNo() + "-" + item.getStoreItem().getItem().getItemId();

        // 이미 해당 storeNo와 itemId에 대한 DailySales 객체가 존재하는지 확인
        DailySales dailySales = dailySalesMap.getOrDefault(key, null);

        if (dailySales == null) {
            // 첫 번째 거래라면 새로운 DailySales 객체를 생성
            dailySales = DailySales.builder()
                    .id(DailySalesId.builder()
                            .saleDate(item.getTransaction().getCreatedAt().toLocalDate())  // 거래 일자를 설정
                            .storeNo(item.getStoreItem().getStore().getStoreNo())            // storeNo 설정
                            .itemId(item.getStoreItem().getItem().getItemId())               // itemId 설정
                            .build())
                    .store(item.getStoreItem().getStore())
                    .item(item.getStoreItem().getItem())
                    .totalQuantity(item.getQuantity())
                    .totalAmount(item.getQuantity() * item.getUnitPrice())
                    .build();
        } else {
            // 이미 존재하면 수량과 금액을 추가
            dailySales.setTotalQuantity(dailySales.getTotalQuantity() + item.getQuantity());
            dailySales.setTotalAmount(dailySales.getTotalAmount() + (item.getQuantity() * item.getUnitPrice()));
        }

        dailySalesMap.put(key, dailySales);

        return dailySales;
    }
}