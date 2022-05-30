package com.project.alihammoud.nasaadmin.controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.alihammoud.nasaadmin.view.EditRoomFragment;
import com.project.alihammoud.nasaadmin.R;
import com.project.alihammoud.nasaadmin.model.RoomDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    private List<RoomDTO> roomDTOS;
    private Context context;

    public RoomsAdapter() {
    }

    public void setData(List<RoomDTO> roomDTOS){
        this.roomDTOS = roomDTOS;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.rooms_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoomDTO roomDTO = roomDTOS.get(position);

        String name = roomDTO.getName();
        String lightId = roomDTO.getLightId();
        String profileId = roomDTO.getProfileId();
        String sensorId = roomDTO.getSensorId();
        String id = roomDTO.getId();


        holder.name.setText(name);
        holder.lightId.setText(lightId);
        holder.profileId.setText(profileId);
        holder.sensorId.setText(sensorId);
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context,view, Gravity.CENTER, R.attr.actionOverflowMenuStyle, 0);
                popupMenu.getMenuInflater().inflate(R.menu.edit_item,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        EditRoomFragment editRoomFragment = new EditRoomFragment();
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                                Toast.makeText(context, id,Toast.LENGTH_LONG).show();
                                deleteRoom(id);
                                activity.getSupportFragmentManager().popBackStack();
                                return true;

                            case R.id.edit:
                                //Toast.makeText(context,"EDITED",Toast.LENGTH_LONG).show();
                                //Bundle
                                Bundle bundle = new Bundle();
                                bundle.putString("ID",id);
                                bundle.putString("NAME",name);
                                bundle.putString("LIGHT",lightId);
                                bundle.putString("PROFILE",profileId);
                                bundle.putString("SENSOR",sensorId);
                                editRoomFragment.setArguments(bundle);
                                // Open Fragment

                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, editRoomFragment).addToBackStack("plants").commit();
                                return true;
                            default:
                                return false;
                        }

                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lightId;
        TextView name;
        TextView profileId;
        TextView sensorId;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lightId = itemView.findViewById(R.id.lightId);
            name = itemView.findViewById(R.id.name);
            profileId = itemView.findViewById(R.id.profileId);
            sensorId = itemView.findViewById(R.id.sensorId);
            card = itemView.findViewById(R.id.card);

        }
    }

    public void deleteRoom(String id){

        Call<RoomDTO> RoomList = ApiClient.getService().deleteRooms(id);

        RoomList.enqueue(new Callback<RoomDTO>() {
            @Override
            public void onResponse(Call<RoomDTO> call, Response<RoomDTO> response) {
                Log.e("Success", "It Worked!");

            }

            @Override
            public void onFailure(Call<RoomDTO> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });

    }


}
