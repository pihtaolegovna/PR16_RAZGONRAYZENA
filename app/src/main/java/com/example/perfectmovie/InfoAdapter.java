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
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.StaffViewHolder> {
    private List<Staff> staffList;
    private Context context;

    public InfoAdapter(List<Staff> staffList, Context context) {
        this.staffList = staffList;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = staffList.get(position);

        holder.nameRuTextView.setText(staff.getNameRu());
        holder.descriptionTextView.setText(staff.getDescription());

        // Load the poster image using Picasso
        Picasso.with(holder.posterImageView.getContext())
                .load(staff.getPosterUrl())
                .into(holder.posterImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start a new activity and pass data
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra("selected_staff_id", staff.getStaffId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    public static class StaffViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView nameRuTextView;
        TextView descriptionTextView;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            nameRuTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.yearTextView);
        }
    }

}
