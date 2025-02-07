package com.hexa.muinus.common.exception.item;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class FliItemNotFoundException extends MuinusException {
    public FliItemNotFoundException() {
        super(ErrorCode.FLI_ITEM_NOT_FOUND);
    }

    public FliItemNotFoundException(int fliItemId) {
        super(ErrorCode.SECTION_FLI_ITEM_NOT_FOUND, String.format("fliItemId: %d", fliItemId));
    }

    public FliItemNotFoundException(int storeNo, int fliItemId) {
        super(ErrorCode.SECTION_FLI_ITEM_NOT_FOUND, String.format("storeNo: %d, fliItemId: %d", storeNo, fliItemId));
    }
}
