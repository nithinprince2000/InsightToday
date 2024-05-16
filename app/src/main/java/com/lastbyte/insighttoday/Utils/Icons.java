package com.lastbyte.insighttoday.Utils;

import com.lastbyte.insighttoday.R;

import java.util.HashMap;

public class Icons {
    private static final HashMap<Integer, Integer> icons = new HashMap<Integer, Integer>() {{
        put(1000, R.drawable.sunny); put(1003, R.drawable.partialy_cloudy);
        put(1006, R.drawable.cloudy); put(1009, R.drawable.overcast);
        put(1030, R.drawable.wind); put(1063, R.drawable.patchy_rain_possible);
        put(1066, R.drawable.patchy_snow_possible); put(1069, R.drawable.patchy_sleet_possible);
        put(1072, R.drawable.freezing_drizzle); put(1087, R.drawable.thundery_outbreaks_possible);
        put(1114, R.drawable.blowing_snow); put(1117, R.drawable.blizzard);
        put(1135, R.drawable.wind); put(1147, R.drawable.freezing_fog);
        put(1150, R.drawable.patchy_light_drizzle); put(1153, R.drawable.patchy_light_drizzle);
        put(1168, R.drawable.freezing_drizzle); put(1171, R.drawable.freezing_drizzle);
        put(1180, R.drawable.patchy_rain_possible); put(1183, R.drawable.patchy_light_drizzle);
        put(1186, R.drawable.patchy_rain_possible); put(1189, R.drawable.patchy_light_drizzle);
        put(1192, R.drawable.patchy_rain_possible); put(1195, R.drawable.patchy_light_drizzle);
        put(1198, R.drawable.freezing_drizzle); put(1201, R.drawable.freezing_drizzle);
        put(1204, R.drawable.light_sleet); put(1207, R.drawable.light_sleet);
        put(1210, R.drawable.patchy_snow_possible); put(1213, R.drawable.patchy_light_snow);
        put(1216, R.drawable.patchy_snow_possible); put(1219, R.drawable.patchy_light_snow);
        put(1222, R.drawable.patchy_heavy_snow); put(1225, R.drawable.heavy_snow);
        put(1237, R.drawable.patchy_light_snow); put(1240, R.drawable.patchy_rain_possible);
        put(1243, R.drawable.patchy_rain_possible); put(1246, R.drawable.patchy_rain_possible);
        put(1249, R.drawable.light_sleet); put(1252, R.drawable.light_sleet);
        put(1255, R.drawable.patchy_snow_possible); put(1258, R.drawable.patchy_heavy_snow);
        put(1261, R.drawable.patchy_snow_possible); put(1264, R.drawable.patchy_heavy_snow);
        put(1273, R.drawable.thundery_outbreaks_possible); put(1276, R.drawable.heavy_rain_with_thunder);
        put(1279, R.drawable.light_snow_with_thunder); put(1282, R.drawable.heavy_snow_with_thunder);
    }};

    public static Integer getIcon(Integer id) {
        return icons.get(id);
    }
}
