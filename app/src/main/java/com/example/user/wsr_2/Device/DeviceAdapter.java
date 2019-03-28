package com.example.user.wsr_2.Device;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.wsr_2.R;
import com.example.user.wsr_2.Room.RoomAdapter;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
    private List<DeviceResponse> deviceResponses;
    private OnItemClickListener mListener;

    public DeviceAdapter(List<DeviceResponse> deviceResponses) {
        this.deviceResponses = deviceResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_device,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(deviceResponses.get(i).getName());
        viewHolder.type.setText(deviceResponses.get(i).getType_name());
        viewHolder.value.setText(deviceResponses.get(i).getValue());
    }

    @Override
    public int getItemCount() {
        if (deviceResponses!=null)
            return deviceResponses.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView value;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            mListener.onItemClick(position);
                    }
                }
            });
            name = itemView.findViewById(R.id.nameOne);
            type = itemView.findViewById(R.id.typeOne);
            value = itemView.findViewById(R.id.valueDevId);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
