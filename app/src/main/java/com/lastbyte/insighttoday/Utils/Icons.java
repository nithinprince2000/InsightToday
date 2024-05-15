package com.lastbyte.insighttoday.Utils;

import com.lastbyte.insighttoday.R;

import java.util.HashMap;
import java.util.Map;

public class Icons {
    private static HashMap<Integer, Integer> icons = new HashMap<Integer, Integer>() {{
        put(1000, R.drawable.test_image); put(1003, R.drawable.sunny);
        put(1006, R.drawable.sunny); put(1009, R.drawable.sunny);
        put(1030, R.drawable.sunny); put(1063, R.drawable.sunny);
        put(1066, R.drawable.sunny); put(1069, R.drawable.sunny);
        put(1072, R.drawable.sunny); put(1087, R.drawable.sunny);
        put(1114, R.drawable.sunny); put(1117, R.drawable.sunny);
        put(1135, R.drawable.sunny); put(1147, R.drawable.sunny);
        put(1150, R.drawable.sunny); put(1153, R.drawable.sunny);
        put(1168, R.drawable.sunny); put(1171, R.drawable.sunny);
        put(1180, R.drawable.sunny); put(1183, R.drawable.sunny);
        put(1186, R.drawable.sunny); put(1189, R.drawable.sunny);
        put(1192, R.drawable.sunny); put(1195, R.drawable.sunny);
        put(1198, R.drawable.sunny); put(1201, R.drawable.sunny);
        put(1204, R.drawable.sunny); put(1207, R.drawable.sunny);
        put(1210, R.drawable.sunny); put(1213, R.drawable.sunny);
        put(1216, R.drawable.sunny); put(1219, R.drawable.sunny);
        put(1222, R.drawable.sunny); put(1225, R.drawable.sunny);
        put(1237, R.drawable.sunny); put(1240, R.drawable.sunny);
        put(1243, R.drawable.sunny); put(1246, R.drawable.sunny);
        put(1249, R.drawable.sunny); put(1252, R.drawable.sunny);
        put(1255, R.drawable.sunny); put(1258, R.drawable.sunny);
        put(1261, R.drawable.sunny); put(1264, R.drawable.sunny);
        put(1273, R.drawable.sunny); put(1276, R.drawable.sunny);
        put(1279, R.drawable.sunny); put(1282, R.drawable.sunny);
    }};

    public static Integer getIcon(Integer id) {
        return icons.get(id);
    }
}
