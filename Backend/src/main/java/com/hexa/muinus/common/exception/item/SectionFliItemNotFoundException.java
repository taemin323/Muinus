package com.hexa.muinus.common.exception.item;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class SectionFliItemNotFoundException extends MuinusException {

    public SectionFliItemNotFoundException() {
        super(ErrorCode.SECTION_FLI_ITEM_NOT_FOUND);
    }

    public SectionFliItemNotFoundException(int sectionId) {
        super(ErrorCode.SECTION_FLI_ITEM_NOT_FOUND, String.format("sectionId: %d", sectionId));
    }

    public SectionFliItemNotFoundException(int storeNo, int sectionId) {
        super(ErrorCode.SECTION_FLI_ITEM_NOT_FOUND, String.format("storeNo: %d, sectionId: %d", storeNo, sectionId));
    }
}
