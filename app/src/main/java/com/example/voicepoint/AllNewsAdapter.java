package com.example.voicepoint;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;


public class AllNewsAdapter extends RecyclerView.Adapter<AllNewsAdapter.ViewHolder> {
    List<NewsItem> list;
    Context context;

    public AllNewsAdapter(Context context, List<NewsItem> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.imageAll);
            title = v.findViewById(R.id.titleAll);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int vType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_all_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int pos) {
        NewsItem item = list.get(pos);
        h.title.setText(item.title);
        Glide.with(context)
                .load(item.imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(dpToPx(20))))
                .placeholder(R.drawable.placeholder)
                .into(h.image);

    }
    private int dpToPx(int dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }


    @Override
    public int getItemCount() { return list.size(); }
}

