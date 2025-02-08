package com.hexa.muinus.batch.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Preference {
    private PreferenceId id;
    private BigDecimal score;
    private LocalDate updatedAt;

    public Long getUserNo() {
        return id != null ? id.getUserNo() : null;
    }

    public Long getItemId() {
        return id != null ? id.getItemId() : null;
    }
}
