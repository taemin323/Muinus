package com.hexa.muinus.common.exception.item;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class StoreItemNotFoundException extends MuinusException {
    public StoreItemNotFoundException() {
        super(ErrorCode.STORE_ITEM_NOT_FOR_SALE);
    }

    public StoreItemNotFoundException(int itemId) {
        super(ErrorCode.STORE_ITEM_NOT_FOR_SALE, "itemId : " + itemId);
    }

    public StoreItemNotFoundException(int storeNo, int itemId) {
        super(ErrorCode.STORE_ITEM_NOT_FOR_SALE, String.format("itemId : %d, storeNo : %d", itemId, storeNo));
    }


}
