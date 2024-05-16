package com.lastbyte.insighttoday.AsyncRunner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lastbyte.insighttoday.Listeners.NetworkListener;
import com.lastbyte.insighttoday.Models.WeatherModel;
import com.lastbyte.insighttoday.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Network extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String url;
    private NetworkListener.Weather networkListener;
    private RequestQueue queue;

    public Network(Context context, String location, NetworkListener.Weather networkListener) {
        this.context = context;
        this.networkListener = networkListener;
        this.url = String.format("%s&q=%s", context.getResources().getString(R.string.api_url), location);
        queue = Volley.newRequestQueue(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRes = new JSONObject(response);

                            WeatherModel weatherModel = new WeatherModel();
                            weatherModel.setWeather(jsonRes.getJSONObject("current").getJSONObject("condition").getString("text"));
                            weatherModel.setIcon(jsonRes.getJSONObject("current").getJSONObject("condition").getInt("code"));
                            weatherModel.setTemperature(jsonRes.getJSONObject("current").getDouble("temp_c"));
                            weatherModel.setHumidity(jsonRes.getJSONObject("current").getDouble("humidity"));
                            weatherModel.setWind(jsonRes.getJSONObject("current").getDouble("wind_mph"));
                            weatherModel.setRain(jsonRes.getJSONObject("current").getDouble("cloud"));
                            weatherModel.setCity(jsonRes.getJSONObject("location").getString("name"));

                            networkListener.onWeatherResponse(weatherModel);

                        } catch (Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body = "Unidentified Error";
                try {
                    body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject jsonRes = new JSONObject(body);
                    Toast.makeText(context, jsonRes.getJSONObject("error").getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(context, body, Toast.LENGTH_SHORT).show();
                }
            }
        });
        queue.add(stringRequest);
        return null;
    }
}
