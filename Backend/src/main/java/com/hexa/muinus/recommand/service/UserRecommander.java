package com.hexa.muinus.recommand.service;

import com.hexa.muinus.elasticsearch.domain.ESItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRecommander {

    public List<ESItem> getRecommendedItems(String userEmail) {
        log.debug("Get recommended items by user {}", userEmail);

        

        return null;
    }
}
