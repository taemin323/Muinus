package com.hexa.muinus.common.exception.item;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import jakarta.validation.constraints.NotNull;

public class ItemNotFoundException extends MuinusException {
    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOR_SALE);
    }

    public ItemNotFoundException(int itemId) {
        super(ErrorCode.ITEM_NOT_FOR_SALE, String.format("itemId: %d", itemId));
    }

    public ItemNotFoundException(String barcode) {
        super(ErrorCode.ITEM_NOT_FOR_SALE, String.format("barcode: %s", barcode));
    }


}
