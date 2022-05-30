package com.project.alihammoud.nasaadmin;

import android.content.Context;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.ViewHolder> {
    private List<ProfileDTO> profileDTOS;
    private Context context;

    public PlantsAdapter() {
    }

    public void setData(List<ProfileDTO> profileDTOS){
        this.profileDTOS = profileDTOS;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.profiles_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfileDTO profileDTO = profileDTOS.get(position);

        String name = profileDTO.getName();
        boolean isPlant = profileDTO.isPlant();
        String id = profileDTO.getId();

        holder.name.setText(name);
        holder.rhythm.setText(id);

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context,view, Gravity.CENTER, R.attr.actionOverflowMenuStyle, 0);
                popupMenu.getMenuInflater().inflate(R.menu.edit_item,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                                Toast.makeText(context, id,Toast.LENGTH_LONG).show();
                                deletePlant(id);
                                activity.getSupportFragmentManager().popBackStack();
                                return true;
                            case R.id.edit:
                                Toast.makeText(context,"Coming Soon",Toast.LENGTH_LONG).show();
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
        return profileDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView isPlant;
        TextView rhythm;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.name);
            card = itemView.findViewById(R.id.card);
            rhythm = itemView.findViewById(R.id.rhythmID);
        }
    }

    public void deletePlant(String id){

        Call<ProfileDTO> ProfileList = ApiClient.getService().deleteProfiles(id);

        ProfileList.enqueue(new Callback<ProfileDTO>() {
            @Override
            public void onResponse(Call<ProfileDTO> call, Response<ProfileDTO> response) {
                Log.e("Success", "It Worked!");
            }

            @Override
            public void onFailure(Call<ProfileDTO> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });

    }

}