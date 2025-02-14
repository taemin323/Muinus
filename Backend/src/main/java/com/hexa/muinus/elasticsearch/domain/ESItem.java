package com.hexa.muinus.elasticsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.time.LocalDateTime;

@Data
@Document(indexName = "items")
public class ESItem {

    @Id
    @Field(name = "item_id", type = FieldType.Long)
    private Integer itemId;

    @Field(name = "barcode", type = FieldType.Keyword)
    private String barcode;

    @Field(name = "item_name", type = FieldType.Text, analyzer = "standard")
    private String itemName;

    @Field(name = "brand", type = FieldType.Keyword)
    private String brand;

    @Field(name = "calories", type = FieldType.Integer)
    private Integer calories;

    @Field(name = "protein", type = FieldType.Integer)
    private Integer protein;

    @Field(name = "fat", type = FieldType.Integer)
    private Integer fat;

    @Field(name = "carbohydrate", type = FieldType.Integer)
    private Integer carbohydrate;

    @Field(name = "sugars", type = FieldType.Integer)
    private Integer sugars;

    @Field(name = "weight", type = FieldType.Integer)
    private Integer weight;

    @Field(name = "item_image_url", type = FieldType.Keyword)
    private String itemImageUrl;

//    @Field(name = "updated_at", type = FieldType.Date)
//    private LocalDateTime updatedAt;
//
//    @Field(name = "suggest")
//    private Object suggest;

    @Field(name = "item_keyword")
    private String itemKeyword;
}
