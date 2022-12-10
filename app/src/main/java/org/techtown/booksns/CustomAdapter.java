package org.techtown.booksns;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private List<Items> dataList;
    private Activity activity;

    public CustomAdapter(Activity activity, List<Items> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }



    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView bookImg;
        public CardView cardView;
        public CustomViewHolder(CardView v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.bookTitle);
            bookImg = (ImageView) v.findViewById(R.id.bookImg);
            cardView = v;
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView)LayoutInflater.from(activity).inflate(R.layout.book_item,parent,false);
        return new CustomViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText("제목 : "+ dataList.get(position).getTitle()+"\n저자 : "+dataList.get(position).getAuthor()+"\n가격 : "+dataList.get(position).getDiscount()+"원");
        Glide.with(activity)
                .load(dataList.get(position).getImage())
                .centerCrop()
                .into(holder.bookImg);

        CardView cardView = holder.cardView;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent= new Intent();
                resultIntent.putExtra("bookImage",dataList.get(position).getImage());
                resultIntent.putExtra("bookTxt",holder.txtTitle.getText());
                activity.setResult(Activity.RESULT_OK,resultIntent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}