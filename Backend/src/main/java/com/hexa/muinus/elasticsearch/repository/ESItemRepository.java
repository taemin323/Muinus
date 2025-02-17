package com.hexa.muinus.elasticsearch.repository;

import com.hexa.muinus.elasticsearch.domain.ESItem;
import com.hexa.muinus.store.domain.item.Item;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ESItemRepository extends ElasticsearchRepository<ESItem, Integer> {

    List<ESItem> findByItemNameStartingWith(String prefix);

}
