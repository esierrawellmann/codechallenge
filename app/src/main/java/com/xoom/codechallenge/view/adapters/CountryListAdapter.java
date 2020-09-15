package com.xoom.codechallenge.view.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.xoom.codechallenge.R;
import com.xoom.codechallenge.databinding.CountryListItemBinding;
import com.xoom.codechallenge.model.Country;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    private List<Country> countryList;
    private ItemClickListener mClickListener;

    CountryListItemBinding countryListItemBinding;

    public CountryListAdapter(Context context, List<Country> data) {
        this.countryList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        countryListItemBinding = CountryListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(countryListItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CountryListItemBinding bindedObject;
        ViewHolder(CountryListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            bindedObject = itemBinding;
            itemView.setOnClickListener(this);
        }
        public void bind(Country country){
             bindedObject.textView.setText(country.getName());
             Picasso.get().load("https://www.countryflags.io/"+country.getCode()+"/flat/64.png").into(bindedObject.imageView);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Country getItem(int id) {
        return countryList.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
