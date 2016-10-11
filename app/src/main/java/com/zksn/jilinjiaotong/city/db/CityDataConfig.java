package com.zksn.jilinjiaotong.city.db;


import android.content.Context;

public class CityDataConfig {
    private static Context DbContext;

    public static void appStart(Context iContext) {
        DbContext = iContext;
        initialize(DbContext);

    }

    /**
     * @param dbContext2
     */
    private static void initialize(Context dbContext2) {
        CityDAO db = new CityDAO(dbContext2);
       /* db.add("辽源市", "54260", "1", "54260");
        db.add("东丰县", "54261", "0", "54261");
        db.add("寿山镇", "54260", "0", "E7013");
        db.add("工农乡", "54260", "0", "E7014");
        db.add("灯塔乡", "54260", "0", "E7015");
        db.add("足民乡", "70201", "0", "E7009");
        db.add("云顶镇", "70202", "0", "E7004");
        db.add("泉太镇", "70203", "0", "E7011");
        db.add("平岗镇", "70204", "0", "E7003");
        db.add("石驿镇", "70205", "0", "E7007");*/

    }
}
