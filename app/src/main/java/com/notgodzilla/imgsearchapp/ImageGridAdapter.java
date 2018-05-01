package com.notgodzilla.imgsearchapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;

import models.Hit;
import models.ImageList;

/**
 * Created by fgodi on 4/19/2018.
 */

class ImageGridAdapter extends RecyclerView.Adapter {

    private ImageList imageList;
    public static String IMAGE_LIKES_EXTRA = "image_likes_extra";

    public ImageGridAdapter(ImageList imageList) {
        this.imageList = imageList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_image_layout, parent, false);
        ImageViewHolder viewHolder = new ImageViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Hit currentImage = imageList.hits.get(position);

        String url = imageList.hits.get(position).previewURL;
        Uri uri = Uri.parse(url);

        ((ImageViewHolder) holder).imageView.setImageURI(uri);
        ((ImageViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent imageDetailIntent = new Intent(v.getContext(), ImageDetailActivity.class);

                imageDetailIntent.putExtra("image_likes_extra", currentImage.favorites);
                imageDetailIntent.putExtra("image_details_comments_extra", currentImage.comments);
                imageDetailIntent.putExtra("image_details_user_extra", currentImage.user);
                imageDetailIntent.putExtra("image_details_large_URL", currentImage.largeImageURL);
                v.getContext().startActivity(imageDetailIntent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return this.imageList.hits.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.fresco_view);
        }
    }
}
