package com.example.voicepoint;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.*;
import java.util.*;

public class NewsDetailsActivity extends AppCompatActivity {

    TextView categoryTitle, newsTitle, newsDate, newsDescription;
    ImageView newsImage;
    RecyclerView relatedNewsRecycler;
    DatabaseReference dbRef;
    List<NewsItem> allNews = new ArrayList<>();
    String currentCategory = "events";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        categoryTitle = findViewById(R.id.categoryTitle);
        newsTitle = findViewById(R.id.newsTitle);
        newsDate = findViewById(R.id.newsDate);
        newsDescription = findViewById(R.id.newsDescription);
        newsImage = findViewById(R.id.newsImage);
        relatedNewsRecycler = findViewById(R.id.relatedNewsRecycler);

        relatedNewsRecycler.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().hasExtra("category")) {
            currentCategory = getIntent().getStringExtra("category");
        }

        setupNavBar();
        categoryTitle.setText(capitalize(currentCategory));
        dbRef = FirebaseDatabase.getInstance().getReference("news");
        loadCategoryNews();
    }

    private void setupNavBar() {
        findViewById(R.id.navSports).setOnClickListener(v -> switchCategory("sports"));
        findViewById(R.id.navAcademic).setOnClickListener(v -> switchCategory("academic"));
        findViewById(R.id.navEvents).setOnClickListener(v -> switchCategory("events"));

        //findViewById(R.id.navMenu).setOnClickListener(v -> {
            //Intent intent = new Intent(NewsDetailsActivity.this, MenuActivity.class);
            //startActivity(intent);
        //});

        highlightIcon(currentCategory);
    }

    private void switchCategory(String category) {
        currentCategory = category;
        highlightIcon(category);
        categoryTitle.setText(capitalize(category));
        loadCategoryNews();
    }

    private void highlightIcon(String cat) {
        float dim = 0.5f;  // semi-transparent
        float full = 1.0f; // fully opaque

        findViewById(R.id.navSports).setAlpha(cat.equals("sports") ? full : dim);
        findViewById(R.id.navAcademic).setAlpha(cat.equals("academic") ? full : dim);
        findViewById(R.id.navEvents).setAlpha(cat.equals("events") ? full : dim);
        findViewById(R.id.navMenu).setAlpha(cat.equals("menu") ? full : dim);
    }

    private void loadCategoryNews() {
        dbRef.orderByChild("category").equalTo(currentCategory)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        allNews.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            NewsItem ni = ds.getValue(NewsItem.class);
                            if (ni != null) allNews.add(ni);
                        }
                        if (!allNews.isEmpty()) displayMainNews(allNews.get(0));
                        relatedNewsRecycler.setAdapter(new RelatedNewsAdapter(NewsDetailsActivity.this, allNews, this::displayMainNews));
                    }

                    @Override public void onCancelled(DatabaseError e) {}
                    private void displayMainNews(NewsItem ni) {
                        newsTitle.setText(ni.title);
                        newsDate.setText(ni.date);
                        newsDescription.setText(ni.description);
                        Glide.with(NewsDetailsActivity.this).load(ni.imageUrl).into(newsImage);
                    }
                });
    }

    private String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
