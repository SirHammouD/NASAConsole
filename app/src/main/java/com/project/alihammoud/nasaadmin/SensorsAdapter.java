package com.project.alihammoud.nasaadmin;

import android.content.Context;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensorsAdapter extends RecyclerView.Adapter<SensorsAdapter.ViewHolder> {
    private List<SensorDTO> sensorDTOS;
    private Context context;

    public SensorsAdapter() {
    }

    public void setData(List<SensorDTO> sensorDTOS){
        this.sensorDTOS = sensorDTOS;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.sensors_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SensorDTO sensorDTO = sensorDTOS.get(position);

        String hostname = sensorDTO.getHostname();
        String name = sensorDTO.getName();
        String brightness = sensorDTO.getBrightness();
        String[] color = sensorDTO.getColor();
        String id = sensorDTO.getId();

        if (brightness == null){
            holder.brightness.setText("N/A");

        }
        else {
            float i =Float.parseFloat(brightness)/1;
            holder.brightness.setText(""+ i);
        }

        if (color == null){
            holder.color.setText("N/A");

        }
        else {
            holder.color.setText(color[0]+","+color[1]+","+color[2]);
        }

        holder.hostname.setText(hostname);
        holder.name.setText(name);
        holder.sensorID.setText(id);
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context,view, Gravity.CENTER, R.attr.actionOverflowMenuStyle, 0);
                popupMenu.getMenuInflater().inflate(R.menu.edit_item,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        EditSensorFragment editSensorFragment = new EditSensorFragment();
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                                Toast.makeText(context, id,Toast.LENGTH_LONG).show();
                                deleteSensor(id);
                                activity.getSupportFragmentManager().popBackStack();

                                return true;
                            case R.id.edit:
                               // Toast.makeText(context,"EDITED",Toast.LENGTH_LONG).show();

                                //Bundle
                                Bundle bundle = new Bundle();
                                bundle.putString("ID",id);
                                bundle.putString("NAME",name);
                                bundle.putString("HOSTNAME",hostname);
                                editSensorFragment.setArguments(bundle);
                                // Open Fragment

                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, editSensorFragment).addToBackStack("sensors").commit();
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
        return sensorDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView hostname;
        TextView name;
        TextView brightness;
        TextView color;
        CardView card;
        TextView sensorID;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hostname = itemView.findViewById(R.id.hostname);
            name = itemView.findViewById(R.id.name);
            brightness = itemView.findViewById(R.id.brightness);
            color = itemView.findViewById(R.id.color);
            card = itemView.findViewById(R.id.card);
            sensorID = itemView.findViewById(R.id.sensorID);

        }

    }
    public void deleteSensor(String id){

        Call<SensorDTO> SensorList = ApiClient.getService().deleteSensors(id);

        SensorList.enqueue(new Callback<SensorDTO>() {
            @Override
            public void onResponse(Call<SensorDTO> call, Response<SensorDTO> response) {
                Log.e("Success", "It Worked!");
            }

            @Override
            public void onFailure(Call<SensorDTO> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });

    }

}
