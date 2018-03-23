package com.epicodus.algebrasupporting;

/**
 * Created by rodnier.borrego on 3/20/18.
 */

public class Constants {

    //API Wolfram|Alpha request url configuration
    public static final String WOLFRAM_ALPHA_TOKEN = BuildConfig.WOLFRAM_ALPHA_TOKEN;
    public static final String WOLFRAM_ALPHA_BASE_URL = "https://api.wolframalpha.com/v2/query?";
    public static final String WOLFRAM_ALPHA_INPUT_QUERY_PARAMETER = "input";
    public static final String WOLFRAM_ALPHA_OUTPUT_QUERY_PARAMETER = "output";
    public static final String WOLFRAM_ALPHA_PODSTATE_QUERY_PARAMETER = "podstate";
    public static final String WOLFRAM_ALPHA_APP_ID_QUERY_PARAMETER = "appid";

    public static final String WOLFRAM_ALPHA_PODSTATE_VALUE_1 = "Step-by-step solution";
    public static final String WOLFRAM_ALPHA_OUTPUT_VALUE_1 = "JSON";
    public static final String WOLFRAM_ALPHA_OPERATION_VALUE_1 = "solve ";

    //API Wolfram|Alpha result titles possibles values
    public static final String WOLFRAM_ALPHA_INPUT_INTERPRETATION_TITLE = "Input interpretation";
    public static final String WOLFRAM_ALPHA_RESULTS_TITLE = "Results";
    public static final String WOLFRAM_ALPHA_POSSIBLE_INTERMEDIATE_STEPS_TITLE = "Possible intermediate steps";
    public static final String WOLFRAM_ALPHA_ROOT_PLOT_TITLE = "Root plot";

}
