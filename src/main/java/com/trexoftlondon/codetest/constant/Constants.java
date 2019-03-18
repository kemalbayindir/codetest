package com.trexoftlondon.codetest.constant;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String RGB_PATTERN      = "#%02x%02x%02x";
    public static final String JSON_KEY_FROM    = "from";

    public static final String PATTERN_PRICE_LABEL  = "Was £%s, %snow £%s";
    public static final String PATTERN_THEN_FORMAT  = "then %s, ";
    public static final String PATTERN_PERC_FORMAT  = "%.2f%% off - now £%s";
    public static Map<String, Color> colors = new HashMap<String, Color>() {{
        put("Red", Color.RED);
        put("Blue", Color.BLUE);
        put("Black", Color.BLACK);
        put("Cyan", Color.CYAN);
        put("Dark Grey", Color.DARK_GRAY);
        put("Dark Mauve", Color.ORANGE);
    }};

    public static final String productServiceByCategory = "https://jl-nonprod-syst.apigee.net/v1/categories/%s/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";

}
