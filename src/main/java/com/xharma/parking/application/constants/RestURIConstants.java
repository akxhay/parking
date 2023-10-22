package com.xharma.parking.application.constants;

public class RestURIConstants {
    public static final String PARKING_BASE = "parking/";

    public static final String GREET = "greet";

    //PARKING_LOT
    public static final String CREATE_DUMMY_PARKING_LOT_V1 = "lot/v1/dummy";

    public static final String CREATE_PARKING_LOT_V1 = "lot/v1/create";
    public static final String FETCH_PARKING_LOTS_V1 = "lot/v1/fetch-all";
    public static final String DELETE_PARKING_LOT_V1 = "lot/v1/delete/{parking_lot_id}";

    public static final String GET_PARKING_SLOT = "getslot/{parking_lot_id}/{size}";
    public static final String RELEASE_PARKING_SLOT = "releaseslot/{parking_lot_id}/{slot_id}";


    RestURIConstants() {

    }

}
