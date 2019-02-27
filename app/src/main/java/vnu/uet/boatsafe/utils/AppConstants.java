

package vnu.uet.boatsafe.utils;

public class AppConstants {



    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public static final String OS = "0";

    public static String[] TABS_DETAIL = new String[]{
            "Chương","Giới thiệu","Có thể bạn chưa xem"
    };


    public static final String[] LOCATION_PERMISSION = new String[]{
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };

}
