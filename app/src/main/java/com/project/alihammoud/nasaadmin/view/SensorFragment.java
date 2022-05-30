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
import com.project.alihammoud.nasaadmin.R;
import com.project.alihammoud.nasaadmin.controller.ApiClient;
import com.project.alihammoud.nasaadmin.controller.SensorsAdapter;
import com.project.alihammoud.nasaadmin.model.SensorDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SensorFragment extends Fragment {

    RecyclerView recyclerView;
    SensorsAdapter sensorsAdapter;
    FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sensor, container, false);

        /*Bundle bundle = this.getArguments();
        String username = bundle.getString("username","None");



        if (username.equals("stanleyjobson")){
            fab.setVisibility(View.GONE);
        }*/
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new AddSensorFragment()).addToBackStack("sensors");
                ft.commit();
            }
        });

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.sensor_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        sensorsAdapter = new SensorsAdapter();

       /* Runnable update = new Runnable() {
            @Override
            public void run() {
                getSensors();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(update, 0, 3, TimeUnit.SECONDS);*/

        getSensors();
        return view;
    }

    public void getSensors(){
        Call<List<SensorDTO>> sensorsList = ApiClient.getService().getSensors();

        sensorsList.enqueue(new Callback<List<SensorDTO>>() {
            @Override
            public void onResponse(Call<List<SensorDTO>> call, Response<List<SensorDTO>> response) {
                if (response.isSuccessful()){
                    List<SensorDTO> sensorDTOS = response.body();
                    sensorsAdapter.setData(sensorDTOS);
                    recyclerView.setAdapter(sensorsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<SensorDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }


        });
    }
}