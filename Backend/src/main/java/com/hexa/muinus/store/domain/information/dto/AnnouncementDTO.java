package com.hexa.muinus.store.domain.information.dto;

import com.hexa.muinus.store.domain.information.Announcement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {
    private int boardId;
    private String title;
    private String content;
    private String boardImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
