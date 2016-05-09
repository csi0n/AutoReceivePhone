package com.csi0n.autoreceivephone.event;

import com.csi0n.autoreceivephone.database.dao.PhoneData;

/**
 * Created by csi0n on 5/8/16.
 */
public class PhoneListUpdateEvent {
    public PhoneData phoneData;

    public PhoneListUpdateEvent(PhoneData phoneData) {
        this.phoneData = phoneData;
    }
}
