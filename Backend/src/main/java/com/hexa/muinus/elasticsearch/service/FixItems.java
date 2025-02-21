package com.hexa.muinus.elasticsearch.service;

import com.hexa.muinus.elasticsearch.domain.ESItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.hexa.muinus.store.domain.item.repository.ItemRepository;
import com.hexa.muinus.store.domain.item.Item;

@Slf4j
@Service
@RequiredArgsConstructor
public class FixItems {

    private static final Set<String> MANDARINE;
    private static final Set<String> ANYITEMS;

    private final ItemRepository itemRepository;

    static {
        Set<String> keywords = new HashSet<>();
        keywords.add("맛있");
        keywords.add("지히");
        keywords.add("지희");

        MANDARINE = Collections.unmodifiableSet(keywords);
    }

    static {
        Set<String> keywords = new HashSet<>();
        keywords.add("아무거나");
        keywords.add("랜덤");
        keywords.add("추천");

        ANYITEMS = Collections.unmodifiableSet(keywords);
    }

    // 토큰으로
    public ESItem getItemByEggKeywords(String text) {
        log.debug("getEggKeywords text: {}", text);

        int itemId = 0;
        // 생귤탱귤 귀신
        if(containsMandarine(text)) itemId = 96;
        else if(containsAny(text)) itemId = (int)(Math.random() * 205 + 1);
        else return null;

        Item item = itemRepository.findItemByItemId(itemId);
        log.info("item: {}", item);

        return convertToESItem(item);
    }

    public boolean containsMandarine(String text) {
        for (String keyword : ANYITEMS) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAny(String text) {
        for (String keyword : MANDARINE) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private ESItem convertToESItem(Item item) {
        ESItem esItem = new ESItem();
        esItem.setItemId(item.getItemId());
        esItem.setBarcode(item.getBarcode());
        esItem.setItemName(item.getItemName());
        esItem.setBrand(item.getBrand());
        esItem.setCalories(item.getCalories());
        esItem.setProtein(item.getProtein());
        esItem.setFat(item.getFat());
        esItem.setCarbohydrate(item.getCarbohydrate());
        esItem.setSugars(item.getSugars());
        esItem.setWeight(item.getWeight());
        esItem.setItemImageUrl(item.getItemImageUrl());
        esItem.setUpdatedAt(item.getUpdatedAt());
        return esItem;
    }


}
