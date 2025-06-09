package com.example.voicepoint;

import android.content.Context;
import android.view.*;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;


public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.ViewHolder> {

    private final Context context;
    private final List<NewsItem> spotlightList;
    private int centerPosition = RecyclerView.NO_POSITION;

    public SpotlightAdapter(Context context, List<NewsItem> spotlightList) {
        this.context = context;
        this.spotlightList = spotlightList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imageSpotlight);

            view.setOnClickListener(v -> {
                centerPosition = getAdapterPosition();
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public SpotlightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_spotlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem item = spotlightList.get(position);
        Glide.with(context)
                .load(item.imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(dpToPx(20))))
                .placeholder(R.drawable.placeholder)
                .into(holder.image);


        ViewGroup.LayoutParams params = holder.image.getLayoutParams();

        // Make center item taller
        if (position == centerPosition) {
            params.height = dpToPx(171);
            holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            params.height = dpToPx(159);
            holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        params.width = dpToPx(213);
        holder.image.setLayoutParams(params);
    }


    private int dpToPx(int dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public int getItemCount() {
        return spotlightList.size();
    }
}
