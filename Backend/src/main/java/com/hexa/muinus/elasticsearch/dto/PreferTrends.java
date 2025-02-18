package com.hexa.muinus.elasticsearch.dto;

import com.hexa.muinus.store.dto.store.StoreMapProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferTrends {
    int itemId;
    String itemName;
    Double trendScore;
    int count;

    public PreferTrends(PrefereTrendsProjection projection) {
        this.itemId = projection.getItemId();
        this.itemName = projection.getItemName();
        this.trendScore = projection.getTrendScore();
        this.count = projection.getCount();
    }

}
