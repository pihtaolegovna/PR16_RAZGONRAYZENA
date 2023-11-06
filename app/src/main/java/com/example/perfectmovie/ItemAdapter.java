package com.example.perfectmovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.security.AccessControlContext;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private Context context;

    public ItemAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.titleTextView.setText(movie.getNameRu());
        holder.yearTextView.setText(String.valueOf(movie.getYear()));

        Picasso.with(holder.posterImageView.getContext())
                .load(movie.getPosterUrl())
                .into(holder.posterImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the ItemDetailActivity and pass data
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra("selected_movie_id", movie.getKinopoiskId());
                intent.putExtra("name", movie.getNameRu());
                intent.putExtra("engname", movie.getNameEn());
                intent.putExtra("year", movie.getYear());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView yearTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
        }
    }
}
