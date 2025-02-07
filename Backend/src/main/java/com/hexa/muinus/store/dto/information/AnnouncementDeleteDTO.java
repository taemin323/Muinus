package com.hexa.muinus.store.dto.information;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "유효한 email을 입력해주세요.")
    @Email
    private String userEmail;
}
