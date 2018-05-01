package com.notgodzilla.imgsearchapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.List;

/**
 * Created by fgodi on 4/26/2018.
 */

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail);

        String imgUrl = getIntent().getStringExtra("image_details_large_URL");
        int numLikes = getIntent().getIntExtra("image_likes_extra", 0);

        SimpleDraweeView largeImageView = (SimpleDraweeView) findViewById(R.id.image_detail_view);
        Uri uri = Uri.parse(imgUrl);
        largeImageView.setImageURI(uri);

        TextView likesTextView = (TextView) findViewById(R.id.image_likes_text);
        likesTextView.setText(getString(R.string.likes_text, numLikes));

        int numComments = getIntent().getIntExtra("image_details_comments_extra", 0);
        TextView comments = (TextView) findViewById(R.id.image_details_comments);
        comments.setText(getString(R.string.num_comments, numComments));






    }





}
