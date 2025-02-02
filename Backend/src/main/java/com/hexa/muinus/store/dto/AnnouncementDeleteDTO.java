package com.hexa.muinus.store.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDeleteDTO {

    @Positive(message = "유효한 boardId 입력해주세요.")
    private Integer boardId;

    @Positive(message = "유효한 storeNo를 입력해주세요.")
    private Integer storeNo;

    @Positive(message = "유효한 userNo를 입력해주세요.")
    private Integer userNo;
}
