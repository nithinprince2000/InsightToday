package com.lastbyte.insighttoday.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lastbyte.insighttoday.Adapters.FavouriteAdapter;
import com.lastbyte.insighttoday.AsyncRunner.Network;
import com.lastbyte.insighttoday.Database.Favourite;
import com.lastbyte.insighttoday.Listeners.FavouriteListener;
import com.lastbyte.insighttoday.Listeners.NetworkListener;
import com.lastbyte.insighttoday.Listeners.WeatherChangeListener;
import com.lastbyte.insighttoday.Models.FavouriteModel;
import com.lastbyte.insighttoday.Models.WeatherModel;
import com.lastbyte.insighttoday.R;
import com.lastbyte.insighttoday.Singletons.InsightToday;
import com.lastbyte.insighttoday.Utils.Icons;
import com.lastbyte.insighttoday.Utils.Utils;

public class MainActivity extends AppCompatActivity implements
        FavouriteListener, WeatherChangeListener,
        TextView.OnEditorActionListener, View.OnClickListener, NetworkListener.Weather {

    private InsightToday insightToday;
    
//    WEATHER_LAYOUT
    private TextView city;
    private ImageView icon;
    private TextView calendar;
    private TextView temperature;
    private TextView weather;
    private ImageButton backButton;
    private Button favActionBtn;
    
//    PROPERTY_LAYOUT
    private TextView wind ;
    private TextView rain;
    private TextView humidity;

    
//    MAIN_LAYOUT
    private EditText searchBox;
    private ImageButton clearSearchButton;
    private RecyclerView favouriteRecyclerView;
    private FavouriteAdapter favouriteAdapter;
    private FavouriteModel favouriteModel;

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

        insightToday = InsightToday.getInstance();

        city = findViewById(R.id.city);
        icon = findViewById(R.id.icon);
        weather = findViewById(R.id.weather);
        calendar = findViewById(R.id.calendar);
        backButton = findViewById(R.id.backButton);
        temperature = findViewById(R.id.temperature);
        favActionBtn = findViewById(R.id.favActionBtn);
        
        wind = findViewById(R.id.wind);
        rain = findViewById(R.id.rain);
        humidity = findViewById(R.id.humidity);
        
        searchBox = findViewById(R.id.searchBox);
        clearSearchButton = findViewById(R.id.clearSearchButton);
        favouriteRecyclerView = findViewById(R.id.favouriteRecyclerView);


        favouriteModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(FavouriteModel.class);
        favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        favouriteModel.getAllFavourites().observe(this, favourites -> {
            favouriteAdapter = new FavouriteAdapter(favourites, MainActivity.this);
            favouriteRecyclerView.setAdapter(favouriteAdapter);
        });

        setData(insightToday.getWeatherData());
        setWeatherChangeListener();
        searchBox.setOnEditorActionListener(this);
        backButton.setOnClickListener(this);
        favActionBtn.setOnClickListener(this);
        clearSearchButton.setOnClickListener(this);

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

    }

    private void setWeatherChangeListener() {
        insightToday.setWeatherChangeListener(this);
        favActionBtn.setVisibility(View.GONE);
    }

    private void changeFavButton(String btnMsg) {
        favActionBtn.setText(String.format("%s", btnMsg));
        favActionBtn.setVisibility(View.VISIBLE);
    }

    private void removeFocus() {
        Utils.hideKeyboard(this);
        searchBox.clearFocus();
    }

    private void clearSearchBox() {
        searchBox.setText("");
        removeFocus();
        clearSearchButton.setVisibility(View.GONE);
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
    public void onFavouriteItemClicked(WeatherModel weather) {
        insightToday.removeWeatherChangeListener();
        setData(weather);
        changeFavButton("Remove From Fav");
        backButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onWeatherChanged(WeatherModel weather) {
        setData(weather);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_DONE) {
            removeFocus();
            backButton.setVisibility(View.GONE);
            if (searchBox.getText().toString().isEmpty()) {
                setWeatherChangeListener();
                return true;
            }
            insightToday.removeWeatherChangeListener();
            new Network(MainActivity.this, searchBox.getText().toString(), this).execute();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == clearSearchButton.getId()) {
            clearSearchBox();
        } else if (v.getId() == favActionBtn.getId()) {
            clearSearchBox();
            if (favActionBtn.getText().toString().contains("Add"))
                favouriteModel.addToFavourites(new Favourite(city.getText().toString()));
            else if (favActionBtn.getText().toString().contains("Remove")) {
                favouriteModel.removeFavourite(new Favourite(city.getText().toString()));
            }
        }
        backButton.setVisibility(View.GONE);
        setWeatherChangeListener();
    }

    @Override
    public void onWeatherResponse(WeatherModel weatherModel) {
        changeFavButton("Add To Fav");
        setData(weatherModel);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
        super.onBackPressed();
    }
}