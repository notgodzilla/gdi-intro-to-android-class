package com.notgodzilla.imgsearchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import models.ImageList;
import retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String searchText = getIntent().getStringExtra(MainActivity.SEARCH_TEXT_EXTRA);
        getData(searchText.replaceAll("\\s+","+"));
    }

    private void getData(String searchText) {

        Log.e("SearchResults", "searchText: " + searchText);


        Map<String, String> parameters = new HashMap<>();
        parameters.put("key","8676171-014b260fab275b497c9fd8142" );
        parameters.put("q",searchText);
        parameters.put("per_page", "30l");
        RetrofitClient
                .getPixabayService()
                .baseApiCall(parameters)
                .enqueue(new Callback<ImageList>() {

                    @Override
                    public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                       createRecyclerView(response.body());
                    }

                    @Override
                    public void onFailure(Call<ImageList> call, Throwable t) {
                        Log.e("SearchResults", t.getLocalizedMessage());

                    }
                });
    }

    private void createRecyclerView(ImageList imageList) {

        RecyclerView imageRecyclerView = (RecyclerView) findViewById(R.id.images_recycler_view);
        imageRecyclerView.setVisibility(View.VISIBLE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_spinner);
        progressBar.setVisibility(View.GONE);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        imageRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new ImageGridAdapter(imageList);
        imageRecyclerView.setAdapter(adapter);



    }

}
