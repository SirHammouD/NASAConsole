package com.project.alihammoud.nasaadmin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditRoomFragment extends Fragment {

    public Button cancel, add;
    public EditText eName;
    public EditText eProfileId;
    public EditText eSensorId;
    public EditText eLightId;
    public String id;
    public String profileID, sensorID, lightID, name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_room, container, false);

        name = getArguments().getString("NAME");
        profileID = getArguments().getString("PROFILE");
        lightID = getArguments().getString("LIGHT");
        sensorID = getArguments().getString("SENSOR");
        id = getArguments().getString("ID");

        eName = view.findViewById(R.id.name);
        eName.setText(name);

        eProfileId = view.findViewById(R.id.profileId);
        eProfileId.setText(profileID);

        eSensorId = view.findViewById(R.id.sensorId);
        eSensorId.setText(sensorID);

        eLightId = view.findViewById(R.id.lightId);
        eLightId.setText(lightID);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString().trim();
                String profileId = eProfileId.getText().toString().trim();
                String sensorId = eSensorId.getText().toString().trim();
                String lightId = eLightId.getText().toString().trim();

                postRooms(name, profileId, sensorId, lightId);
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

    public void postRooms(String name, String profileId, String sensorId, String lightId){

        if (!validateForm()) {
            return;
        }

        RoomDTO roomDTO = new RoomDTO(name,profileId,sensorId,lightId);
        Call<List<RoomDTO>> roomsList = ApiClient.getService().putRooms(id, roomDTO);

        roomsList.enqueue(new Callback<List<RoomDTO>>() {
            @Override
            public void onResponse(Call<List<RoomDTO>> call, Response<List<RoomDTO>> response) {
                Log.e("Success", "It Worked!");
                getActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onFailure(Call<List<RoomDTO>> call, Throwable t) {
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

        if (  eProfileId.getText().toString().trim().isEmpty()){
            eProfileId.setError("Required.");
            valid = false;
        } else {
            eProfileId.setError(null);
        }

        if (  eSensorId.getText().toString().trim().isEmpty()){
            eSensorId.setError("Required.");
            valid = false;
        } else {
            eSensorId.setError(null);
        }

        if (  eLightId.getText().toString().trim().isEmpty()){
            eLightId.setError("Required.");
            valid = false;
        } else {
            eLightId.setError(null);
        }

        return valid;
    }


}