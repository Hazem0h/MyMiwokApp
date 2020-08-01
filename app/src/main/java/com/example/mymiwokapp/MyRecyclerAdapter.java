package com.example.mymiwokapp;

import android.app.ActionBar;
import android.app.Activity;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    //Because each adapter will have its own data source, it's better to pass it in a constructor
    ArrayList<Word> dataSource;
    int color_id;
    boolean hasImage;

    public MyRecyclerAdapter(ArrayList<Word> data, int color_source, boolean hasImage){
        dataSource = data;
        color_id = color_source;
        this.hasImage = hasImage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), color_id));

        return new ViewHolder(view, dataSource);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!hasImage){
            holder.imageView.setVisibility(View.GONE);
        }else{
            holder.imageView.setImageResource(dataSource.get(position).getImageId());
        }
        holder.englishTextView.setText(dataSource.get(position).getEnglish());
        holder.miwokTextView.setText(dataSource.get(position).getMiwok());

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView miwokTextView;
        TextView englishTextView;
        ArrayList<Word> dataSource;
        public ViewHolder(@NonNull final View itemView, ArrayList<Word> data) {
            super(itemView);

            imageView = itemView.findViewById(R.id.label_image);
            miwokTextView = itemView.findViewById(R.id.miwok_view);
            englishTextView = itemView.findViewById(R.id.english_word);
            ///the listener
            dataSource = data;
            itemView.setOnClickListener(ViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            MainActivity hostingActivity = (MainActivity) v.getContext();
            int result = hostingActivity.getAudioManager().requestAudioFocus(hostingActivity,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                if (hostingActivity.getPlayer() != null) {
                    hostingActivity.stopPlayer();
                }
                hostingActivity.setPlayer(MediaPlayer.create(hostingActivity, dataSource.get(getAdapterPosition()).getAudioId()));
                hostingActivity.getPlayer().setOnCompletionListener(hostingActivity);
                hostingActivity.getPlayer().start();
            }
        }
    }
}
