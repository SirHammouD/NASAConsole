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
import com.project.alihammoud.nasaadmin.model.LightDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditLightFragment extends Fragment {

    public Button cancel, add;
    public EditText eName;
    public EditText eHostname;
    public String name, hostname, id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_light, container, false);

        name = getArguments().getString("NAME");
        hostname = getArguments().getString("HOSTNAME");
        id = getArguments().getString("ID");


        eName = view.findViewById(R.id.name);
        eName.setText(name);

        eHostname = view.findViewById(R.id.hostname);
        eHostname.setText(hostname);



        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString().trim();
                String hostname = eHostname.getText().toString().trim();
                postLights(name, hostname);
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

    public void postLights(String n, String h){

        if (!validateForm()) {
            return;
        }

        LightDTO lightDTO = new LightDTO(n,h);
        Call<List<LightDTO>> lightsList = ApiClient.getService().putLights(id, lightDTO);

        lightsList.enqueue(new Callback<List<LightDTO>>() {
            @Override
            public void onResponse(Call<List<LightDTO>> call, Response<List<LightDTO>> response) {
                Log.e("Success", "It Worked!");
                getActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onFailure(Call<List<LightDTO>> call, Throwable t) {
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