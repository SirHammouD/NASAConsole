package com.project.alihammoud.nasaadmin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlantFragment extends Fragment {

    RecyclerView recyclerView;
    PlantsAdapter plantsAdapter;
    FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_plant, container, false);

       /* Bundle bundle = this.getArguments();
        String username = bundle.getString("username","None");



        if (username.equals("stanleyjobson")){
            fab.setVisibility(View.GONE);
        }*/

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new AddPlantFragment()).addToBackStack("plants");
                ft.commit();
            }
        });

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.plant_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        plantsAdapter = new PlantsAdapter();

      /*  Runnable update = new Runnable() {
            @Override
            public void run() {
                getProfiles();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(update, 0, 3, TimeUnit.SECONDS);*/
        getProfiles();

        return view;
    }

    public void getProfiles(){
        Call<List<ProfileDTO>> profilesList = ApiClient.getService().getProfiles();

        profilesList.enqueue(new Callback<List<ProfileDTO>>() {
            @Override
            public void onResponse(Call<List<ProfileDTO>> call, Response<List<ProfileDTO>> response) {
                if (response.isSuccessful()){
                    List<ProfileDTO> profileDTOS = response.body();
                    List<ProfileDTO> posts = new ArrayList<ProfileDTO>();

                    for(int i=0;i<profileDTOS.size();i++){
                        if(profileDTOS.get(i).isPlant()==true){
                            posts.add(profileDTOS.get(i));
                        }
                    }

                    plantsAdapter.setData(posts);
                    recyclerView.setAdapter(plantsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ProfileDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });
    }
}