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

import com.lastbyte.insighttoday.Listeners.FavouriteListener;
import com.lastbyte.insighttoday.Models.FavouriteModel;
import com.lastbyte.insighttoday.R;
import com.lastbyte.insighttoday.Utils.Icons;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private List<FavouriteModel> favouriteModelList;
    private FavouriteListener favouriteListener;
    private Context context;

    public FavouriteAdapter(List<FavouriteModel> favouriteModelList) {
        this.favouriteModelList = favouriteModelList;
    }

    public FavouriteAdapter(List<FavouriteModel> favouriteModelList, FavouriteListener favouriteListener) {
        this.favouriteModelList = favouriteModelList;
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

        holder.city.setText(favouriteModelList.get(position).getCity());
        holder.weather.setText(favouriteModelList.get(position).getWeather());
        holder.temperature.setText(String.format("%.02f°", favouriteModelList.get(position).getTemperature()));
        holder.weatherImage.setImageResource(Icons.getIcon(favouriteModelList.get(position).getIcon()));

        holder.itemView.setOnClickListener(v -> {
            if (favouriteListener != null)
                favouriteListener.onFavouriteItemClicked(position);
        });

    }

    @Override
    public int getItemCount() {
        return favouriteModelList.size();
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
