package com.epicodus.algebrasupporting.services;

/**
 * Created by rodnier.borrego on 3/20/18.
 */
import android.util.Log;

import com.epicodus.algebrasupporting.Constants;
import com.epicodus.algebrasupporting.models.SolveResult;

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
    public static final String TAG = WolframAlphaService.class.getSimpleName();
    public static void findResultStepByStepSolve(String input, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WOLFRAM_ALPHA_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WOLFRAM_ALPHA_INPUT_QUERY_PARAMETER, Constants.WOLFRAM_ALPHA_OPERATION_VALUE_1.concat(input).concat(" for x"));
        urlBuilder.addQueryParameter(Constants.WOLFRAM_ALPHA_OUTPUT_QUERY_PARAMETER, Constants.WOLFRAM_ALPHA_OUTPUT_VALUE_1);
        urlBuilder.addQueryParameter(Constants.WOLFRAM_ALPHA_PODSTATE_QUERY_PARAMETER, Constants.WOLFRAM_ALPHA_PODSTATE_VALUE_1);
        urlBuilder.addQueryParameter(Constants.WOLFRAM_ALPHA_APP_ID_QUERY_PARAMETER, Constants.WOLFRAM_ALPHA_TOKEN);
        String url = urlBuilder.build().toString();
        System.out.println(url);
        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<ArrayList<String>> processResults(Response response) {
        ArrayList<ArrayList<String>> pods = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject solveResultJSON = new JSONObject(jsonData);
            JSONArray podsJSON = solveResultJSON.getJSONObject("queryresult").getJSONArray("pods");
            for (int i = 0; i < podsJSON.length(); i++) {
                ArrayList<String> subpods = new ArrayList<>();
                JSONObject pod = podsJSON.getJSONObject(i);
                JSONArray subpodsJSON = pod.getJSONArray("subpods");
                for (int j = 0; j < subpodsJSON.length(); j++) {
                    JSONObject subpod = subpodsJSON.getJSONObject(j);
                    if(!subpod.getString("title").equals("")){
                        subpods.add(subpod.getString("title"));
                    }
                    else {
                        subpods.add(pod.getString("title"));
                    }
                    subpods.add(subpod.getJSONObject("img").getString("src"));
                    subpods.add(subpod.getString("plaintext"));
                }
                pods.add(subpods);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return pods;
    }
}
