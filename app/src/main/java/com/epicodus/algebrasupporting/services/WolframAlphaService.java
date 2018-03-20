package com.epicodus.algebrasupporting.services;

/**
 * Created by rodnier.borrego on 3/20/18.
 */
import com.epicodus.algebrasupporting.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WolframAlphaService {
    public static void findResultStepByStepSolve(String input, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WOLFRAM_ALPHA_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WOLFRAM_ALPHA_INPUT_QUERY_PARAMETER, input.concat(Constants.WOLFRAM_ALPHA_OPERATION_VALUE_1));
        urlBuilder.addQueryParameter(Constants.WOLFRAM_ALPHA_OUTPUT_QUERY_PARAMETER, Constants.WOLFRAM_ALPHA_OUTPUT_VALUE_1);
        urlBuilder.addQueryParameter(Constants.WOLFRAM_ALPHA_PODSTATE_QUERY_PARAMETER, Constants.WOLFRAM_ALPHA_PODSTATE_QUERY_PARAMETER);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.WOLFRAM_ALPHA_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
