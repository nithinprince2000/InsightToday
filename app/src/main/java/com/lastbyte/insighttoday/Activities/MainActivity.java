package com.lastbyte.insighttoday.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lastbyte.insighttoday.Adapters.FavouriteAdapter;
import com.lastbyte.insighttoday.AsyncRunner.Network;
import com.lastbyte.insighttoday.Listeners.FavouriteListener;
import com.lastbyte.insighttoday.Listeners.NetworkListener;
import com.lastbyte.insighttoday.Models.FavouriteModel;
import com.lastbyte.insighttoday.Models.WeatherModel;
import com.lastbyte.insighttoday.R;
import com.lastbyte.insighttoday.Utils.Icons;
import com.lastbyte.insighttoday.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FavouriteListener {
    
//    WEATHER_LAYOUT
    private TextView city;
    private ImageView icon;
    private TextView calendar;
    private TextView temperature;
    private TextView weather;
    private ImageButton backButton;
    
//    PROPERTY_LAYOUT
    private TextView wind ;
    private TextView rain;
    private TextView humidity;

    
//    MAIN_LAYOUT
    private EditText searchBox;
    private ImageButton clearSearchButton;
    private RecyclerView favouriteRecyclerView;
    private FavouriteAdapter favouriteAdapter;
    private List<FavouriteModel> favouriteModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        city = findViewById(R.id.city);
        icon = findViewById(R.id.icon);
        temperature = findViewById(R.id.temperature);
        weather = findViewById(R.id.weather);
        calendar = findViewById(R.id.calendar);
        backButton = findViewById(R.id.backButton);
        
        wind = findViewById(R.id.wind);
        rain = findViewById(R.id.rain);
        humidity = findViewById(R.id.humidity);
        
        searchBox = findViewById(R.id.searchBox);
        clearSearchButton = findViewById(R.id.clearSearchButton);
        favouriteRecyclerView = findViewById(R.id.favouriteRecyclerView);

        favouriteModelList = new ArrayList<>();
        favouriteAdapter = new FavouriteAdapter(favouriteModelList, this);
        favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        favouriteRecyclerView.setAdapter(favouriteAdapter);
        
//
//        for (int i=0; i<10; i++) {
//            favouriteModelList.add(new FavouriteModel("City "+i, "Weather "+i, 20.02376, 1000));
//            favouriteAdapter.notifyDataSetChanged();
//        }

        new Network(this, "Delhi", new NetworkListener.Weather() {
            @Override
            public void onWeatherResponse(WeatherModel weatherModel) {setData(weatherModel);}
        }).execute();

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearSearchButton.setVisibility(searchBox.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Utils.hideKeyboard(MainActivity.this);
                    searchBox.clearFocus();
                    return true;
                }
                return false;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        backButton.setOnClickListener(v -> {
            v.setVisibility(View.GONE);
        });
        clearSearchButton.setOnClickListener(v -> {
            searchBox.setText("");
            Utils.hideKeyboard(this);
            searchBox.clearFocus();
            v.setVisibility(View.GONE);
        });
    }

    @SuppressLint("DefaultLocale")
    public void setData(WeatherModel weatherModel) {

        city.setText(weatherModel.getCity());
        calendar.setText(Utils.getDateAndDay());
        weather.setText(weatherModel.getWeather());
        icon.setImageResource(Icons.getIcon(weatherModel.getIcon()));
        rain.setText(String.format("%.02f %%", weatherModel.getRain()));
        wind.setText(String.format("%.02f m/h", weatherModel.getWind()));
        humidity.setText(String.format("%.02f %%", weatherModel.getHumidity()));
        temperature.setText(String.format("%d°", (int) weatherModel.getTemperature()));
    }
    
    @Override
    public void onFavouriteItemClicked(int position) {
        backButton.setVisibility(View.VISIBLE);
    }
    
}