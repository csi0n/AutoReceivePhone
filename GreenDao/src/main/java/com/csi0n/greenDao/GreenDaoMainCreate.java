package com.csi0n.greenDao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by csi0n on 5/8/16.
 */
public class GreenDaoMainCreate {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(2, "com.csi0n.autoreceivephone.database.dao");
        addPhoneAllow(schema);
        addPhone(schema);
        //new DaoGenerator().generateAll(schema,"/Users/csi0n/Desktop/Dev/AutoReceivePhone/App/src/main/java-gen/com/csi0n/autoreceivephone/database/dao");
        new DaoGenerator().generateAll(schema,"G:/Web_Github/AutoReceivePhone/App/src/main/java-gen");
    }
    public static void addPhoneAllow(Schema schema){
        Entity phone = schema.addEntity("PhoneAllow");
        phone.addStringProperty("phoneNumber").unique();
        phone.addDateProperty("time");
    }
    private static void addPhone(Schema schema) {
        Entity phone = schema.addEntity("PhoneData");
        phone.addStringProperty("phoneNumber");
        phone.addDateProperty("time");
    }

}
