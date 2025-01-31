package com.hexa.muinus.store.dto;

import com.hexa.muinus.store.domain.information.dto.AnnouncementDTO;
import com.hexa.muinus.store.domain.item.dto.FliItemDTO;
import com.hexa.muinus.store.domain.item.dto.StoreItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDetailDTO {
    private StoreDTO store;
    private List<AnnouncementDTO> announcements;
    private List<StoreItemDTO> storeItems;
    private List<FliItemDTO> fliItems;
}
