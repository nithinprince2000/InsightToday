package com.lastbyte.insighttoday.Activities;

import static com.lastbyte.insighttoday.Utils.Constants.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.lastbyte.insighttoday.AsyncRunner.Network;
import com.lastbyte.insighttoday.Listeners.NetworkListener;
import com.lastbyte.insighttoday.Listeners.WeatherChangeListener;
import com.lastbyte.insighttoday.Models.WeatherModel;
import com.lastbyte.insighttoday.R;
import com.lastbyte.insighttoday.Singletons.InsightToday;

public class SplashActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private InsightToday insightToday;

    private TextView splashMessage;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        splashMessage = findViewById(R.id.splashMessage);

        insightToday = InsightToday.getInstance();
        insightToday.setWeatherChangeListener(new WeatherChangeListener() {
            @Override
            public void onWeatherChanged(WeatherModel weatherModel) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                insightToday.removeWeatherChangeListener();
            }
        });

        ActivityCompat.requestPermissions( this,   new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        splashMessage.setText(String.format("%s", "Getting Device Location."));
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                new LocationListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        splashMessage.setText(String.format("%s", "Getting Weather Data."));
                        new Network(SplashActivity.this, String.format("%f,%f", location.getLatitude(), location.getLongitude()), new NetworkListener.Weather() {
                            @Override
                            public void onWeatherResponse(WeatherModel weatherModel) {
                                insightToday.setWeatherData(weatherModel);
                            }
                        }).execute();
                    }
                }
        );
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            turnOnGPS();
        } else {
            getLocation();
        }
    }

    private void turnOnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                SplashActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }
}