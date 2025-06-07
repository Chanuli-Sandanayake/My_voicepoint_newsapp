package com.example.voicepoint;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.ViewHolder> {

    private final List<NewsItem> list;
    private final OnNewsClickListener listener;

    public interface OnNewsClickListener {
        void onClick(NewsItem item);
    }

    public RelatedNewsAdapter(Context ctx, List<NewsItem> list, OnNewsClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.imageAll);
            title = v.findViewById(R.id.titleAll);
            v.setOnClickListener(view -> listener.onClick(list.get(getAdapterPosition())));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int vt) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int p) {
        NewsItem ni = list.get(p);
        h.title.setText(ni.title);
        Glide.with(h.itemView.getContext()).load(ni.imageUrl).into(h.image);
    }

    @Override public int getItemCount() { return list.size(); }
}

