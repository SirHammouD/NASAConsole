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

import com.project.alihammoud.nasaadmin.R;
import com.project.alihammoud.nasaadmin.model.LightDTO;
import com.project.alihammoud.nasaadmin.view.EditLightFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightsAdapter extends RecyclerView.Adapter<LightsAdapter.ViewHolder> {
    private List<LightDTO> lightDTOS;
    private Context context;

    public LightsAdapter() {
    }

    public void setData(List<LightDTO> lightDTOS){
        this.lightDTOS = lightDTOS;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.lights_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LightDTO lightDTO = lightDTOS.get(position);

        String hostname = lightDTO.getHostname();
        String name = lightDTO.getName();
        String id = lightDTO.getId();


        holder.hostname.setText(hostname);
        holder.name.setText(name);
        holder.lightID.setText(id);
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context,view, Gravity.CENTER, R.attr.actionOverflowMenuStyle, 0);
                popupMenu.getMenuInflater().inflate(R.menu.edit_item,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        EditLightFragment editLightFragment = new EditLightFragment();
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                                Toast.makeText(context, id,Toast.LENGTH_LONG).show();
                                deleteLight(id);
                                activity.getSupportFragmentManager().popBackStack();

                                return true;
                            case R.id.edit:
                                //Toast.makeText(context,"EDITED",Toast.LENGTH_LONG).show();
                                //Bundle
                                Bundle bundle = new Bundle();
                                bundle.putString("ID",id);
                                bundle.putString("NAME",name);
                                bundle.putString("HOSTNAME",hostname);
                                editLightFragment.setArguments(bundle);
                                // Open Fragment
                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, editLightFragment).addToBackStack("plants").commit();
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
        return lightDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView hostname;
       TextView name;
       CardView card;
       TextView lightID;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hostname = itemView.findViewById(R.id.hostname);
            name = itemView.findViewById(R.id.name);
            card = itemView.findViewById(R.id.card);
            lightID = itemView.findViewById(R.id.lightID);

        }
    }

    public void deleteLight(String id){

        Call<LightDTO> LightList = ApiClient.getService().deleteLights(id);

        LightList.enqueue(new Callback<LightDTO>() {
            @Override
            public void onResponse(Call<LightDTO> call, Response<LightDTO> response) {
                Log.e("Success", "It Worked!");
            }

            @Override
            public void onFailure(Call<LightDTO> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });

    }


}
