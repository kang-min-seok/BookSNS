package org.techtown.booksns;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private ArrayList<String> localDataSet;
    private Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

        public CardView getTextView() {
            return cardView;
        }
    }

    public GalleryAdapter(Activity activity ,ArrayList<String> dataSet) {
        localDataSet = dataSet;
        this.activity = activity;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        CardView cardView = viewHolder.cardView;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent= new Intent();
                resultIntent.putExtra("profilePath",localDataSet.get(viewHolder.getAdapterPosition()));
                activity.setResult(Activity.RESULT_OK,resultIntent);
                activity.finish();
            }
        });

        ImageView imageView = cardView.findViewById(R.id.imageView);
        Glide.with(activity).load(localDataSet.get(position)).centerCrop().override(500).into(imageView);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}