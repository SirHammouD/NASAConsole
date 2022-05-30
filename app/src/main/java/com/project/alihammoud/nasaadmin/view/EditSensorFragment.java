package com.project.alihammoud.nasaadmin.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.project.alihammoud.nasaadmin.R;
import com.project.alihammoud.nasaadmin.controller.ApiClient;
import com.project.alihammoud.nasaadmin.model.SensorDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditSensorFragment extends Fragment {

    public Button cancel, add;
    public EditText eName;
    public EditText eHostname;
    public EditText eColor;
    public EditText eBrightness;
    public String name, hostname, id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_sensor, container, false);

        name = getArguments().getString("NAME");
        hostname = getArguments().getString("HOSTNAME");
        id = getArguments().getString("ID");

        eName = view.findViewById(R.id.name);
        eName.setText(name);

        eHostname = view.findViewById(R.id.hostname);
        eHostname.setText(hostname);

        eBrightness = view.findViewById(R.id.brightness);
        eColor = view.findViewById(R.id.color);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString().trim();
                String hostname = eHostname.getText().toString().trim();
                postSensors(hostname,name);
            }
        });

        cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return view;
    }

    public void postSensors(String hostname, String name){

        if (!validateForm()) {
            return;
        }

        SensorDTO sensorDTO = new SensorDTO(hostname,name);
        Call<List<SensorDTO>> sensorsList = ApiClient.getService().putSensors(id, sensorDTO);

        sensorsList.enqueue(new Callback<List<SensorDTO>>() {
            @Override
            public void onResponse(Call<List<SensorDTO>> call, Response<List<SensorDTO>> response) {
                Log.e("Success", "It Worked!");
                getActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onFailure(Call<List<SensorDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    private boolean validateForm() {
        boolean valid = true;

        if (  eName.getText().toString().trim().isEmpty()){
            eName.setError("Required.");
            valid = false;
        } else {
            eName.setError(null);
        }

        if (  eHostname.getText().toString().trim().isEmpty()){
            eHostname.setError("Required.");
            valid = false;
        } else {
            eHostname.setError(null);
        }

        return valid;
    }
}