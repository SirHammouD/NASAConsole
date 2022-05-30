package com.project.alihammoud.nasaadmin.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.alihammoud.nasaadmin.controller.LightsAdapter;
import com.project.alihammoud.nasaadmin.R;
import com.project.alihammoud.nasaadmin.controller.ApiClient;
import com.project.alihammoud.nasaadmin.model.LightDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LightFragment extends Fragment {

    RecyclerView recyclerView;
    LightsAdapter lightsAdapter;
    FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_light, container, false);

        fab = view.findViewById(R.id.fab);

        if (this.getArguments()!=null) {
            Bundle bundle = this.getArguments();
            username = bundle.getString("username");

            if (!username.equals("stanleyjobson")) {
                fab.setVisibility(View.GONE);
            }
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new AddLightFragment()).addToBackStack("lights");
                ft.commit();
            }
        });


        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.light_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        lightsAdapter = new LightsAdapter();

        /*Runnable update = new Runnable() {
            @Override
            public void run() {

            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(update, 0, 3, TimeUnit.SECONDS);*/

        getLights();
        return view;
    }

    public void getLights(){
        Call<List<LightDTO>> lightsList = ApiClient.getService().getLights();

        lightsList.enqueue(new Callback<List<LightDTO>>() {
            @Override
            public void onResponse(Call<List<LightDTO>> call, Response<List<LightDTO>> response) {
                if (response.isSuccessful()){
                   List<LightDTO> lightDTOS = response.body();
                   lightsAdapter.setData(lightDTOS);
                   recyclerView.setAdapter(lightsAdapter);

             }
            }

            @Override
            public void onFailure(Call<List<LightDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });
    }


}