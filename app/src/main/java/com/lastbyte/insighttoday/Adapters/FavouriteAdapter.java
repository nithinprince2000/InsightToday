package com.lastbyte.insighttoday.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lastbyte.insighttoday.AsyncRunner.Network;
import com.lastbyte.insighttoday.Database.Favourite;
import com.lastbyte.insighttoday.Listeners.FavouriteListener;
import com.lastbyte.insighttoday.Listeners.NetworkListener;
import com.lastbyte.insighttoday.Models.FavouriteModel;
import com.lastbyte.insighttoday.Models.WeatherModel;
import com.lastbyte.insighttoday.R;
import com.lastbyte.insighttoday.Utils.Icons;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private List<Favourite> favouriteList;
    private FavouriteListener favouriteListener;
    private Context context;

    public FavouriteAdapter(List<Favourite> favouriteList) {
        this.favouriteList = favouriteList;
    }

    public FavouriteAdapter(List<Favourite> favouriteList, FavouriteListener favouriteListener) {
        this.favouriteList = favouriteList;
        this.favouriteListener = favouriteListener;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {

        new Network(context, favouriteList.get(position).getCity(), new NetworkListener.Weather() {
            @Override
            public void onWeatherResponse(WeatherModel weatherModel) {
                holder.city.setText(weatherModel.getCity());
                holder.weather.setText(weatherModel.getWeather());
                holder.temperature.setText(String.format("%.02f°", weatherModel.getTemperature()));
                holder.weatherImage.setImageResource(Icons.getIcon(weatherModel.getIcon()));

                holder.itemView.setOnClickListener(v -> {
                    if (favouriteListener != null)
                        favouriteListener.onFavouriteItemClicked(weatherModel);
                });
            }
        }).execute();

    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView city, weather, temperature;
        private ImageView weatherImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.city);
            weather = itemView.findViewById(R.id.weather);
            temperature = itemView.findViewById(R.id.temperature);
            weatherImage = itemView.findViewById(R.id.weatherImage);
        }
    }
}
