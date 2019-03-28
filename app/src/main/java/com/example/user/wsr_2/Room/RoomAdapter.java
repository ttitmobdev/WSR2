package com.example.user.wsr_2.Room;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.wsr_2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<RoomResponse> roomResponses;
    private OnItemClickListener Mlistener;

    public RoomAdapter(List<RoomResponse> roomResponses) {
        this.roomResponses = roomResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_room,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(roomResponses.get(i).getName());
        Uri uri = Uri.parse(roomResponses.get(i).getPhoto());
        Picasso.get().load(uri).into(viewHolder.photo);
    }

    @Override
    public int getItemCount() {
        if (roomResponses!=null)
            return roomResponses.size();
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Mlistener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            Mlistener.onItemClick(position);
                        }
                    }
                }
            });
            name = itemView.findViewById(R.id.roomNameId);
            photo = itemView.findViewById(R.id.roomImageId);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        Mlistener=listener;
    }

}
