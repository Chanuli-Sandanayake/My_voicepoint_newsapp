package com.example.voicepoint;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

//news-details
import android.content.Intent;
import android.widget.ImageButton;


public class NewsActivity extends AppCompatActivity {

    RecyclerView spotlightRecycler, allNewsRecycler;
    List<NewsItem> spotlightList = new ArrayList<>();
    List<NewsItem> allList = new ArrayList<>();
    DatabaseReference newsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        spotlightRecycler = findViewById(R.id.horizontalCarousel);
        allNewsRecycler = findViewById(R.id.verticalNewsList);

        spotlightRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        allNewsRecycler.setLayoutManager(new LinearLayoutManager(this));

        newsRef = FirebaseDatabase.getInstance().getReference("news");
        fetchNewsFromFirebase();

        //newsdetails
        ImageButton navSports = findViewById(R.id.navSports);
        ImageButton navAcademic = findViewById(R.id.navAcademic);
        ImageButton navEvents = findViewById(R.id.navEvents);
        ImageButton navMenu = findViewById(R.id.navMenu);

        navSports.setOnClickListener(v -> {
            Intent i = new Intent(NewsActivity.this, NewsDetailsActivity.class);
            i.putExtra("category", "sports");
            startActivity(i);
        });

        navAcademic.setOnClickListener(v -> {
            Intent i = new Intent(NewsActivity.this, NewsDetailsActivity.class);
            i.putExtra("category", "academic");
            startActivity(i);
        });

        navEvents.setOnClickListener(v -> {
            Intent i = new Intent(NewsActivity.this, NewsDetailsActivity.class);
            i.putExtra("category", "events");
            startActivity(i);
        });
        findViewById(R.id.navMenu).setOnClickListener(v -> {
            Intent intent = new Intent(NewsActivity.this, MenuActivity.class);
            startActivity(intent);
        });




    }



    private void fetchNewsFromFirebase() {
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                spotlightList.clear();
                allList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    NewsItem item = snap.getValue(NewsItem.class);
                    Log.d("DEBUG_FIREBASE", "Fetched: " + item.imageUrl);


                    if (item != null) {
                        allList.add(item);
                        if (spotlightList.size() < 3) {
                            spotlightList.add(item);
                        }
                        android.util.Log.d("DEBUG_IMAGE", "Loaded URL: " + item.imageUrl);
                    }
                }
                spotlightRecycler.setAdapter(new SpotlightAdapter(NewsActivity.this, spotlightList));
                allNewsRecycler.setAdapter(new AllNewsAdapter(NewsActivity.this, allList));
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }
}
