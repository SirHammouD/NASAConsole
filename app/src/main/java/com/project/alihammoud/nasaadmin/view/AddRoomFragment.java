package com.project.alihammoud.nasaadmin.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.project.alihammoud.nasaadmin.R;
import com.project.alihammoud.nasaadmin.controller.ApiClient;
import com.project.alihammoud.nasaadmin.model.LightDTO;
import com.project.alihammoud.nasaadmin.model.ProfileDTO;
import com.project.alihammoud.nasaadmin.model.RoomDTO;
import com.project.alihammoud.nasaadmin.model.SensorDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddRoomFragment extends Fragment {

    public Button cancel, add;
    public EditText eName;
    public EditText eProfileId;
    public EditText eSensorId;
    public EditText eLightId;
    List<String> lightList = new ArrayList<String>();
    List<String> sensorList = new ArrayList<String>();
    List<String> profileList = new ArrayList<String>();
    List<String> testList = new ArrayList<String>();
    public Spinner spinner1, spinner2, spinner3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_room, container, false);

        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");
        eName = view.findViewById(R.id.name);
       /* eProfileId = view.findViewById(R.id.profileId);
        eSensorId = view.findViewById(R.id.sensorId);
        eLightId = view.findViewById(R.id.lightId);*/
        spinner1 = view.findViewById(R.id.spinnerLight);
        spinner2 = view.findViewById(R.id.spinnerSensor);
        spinner3 = view.findViewById(R.id.spinnerProfile);


        profileList();
        sensorList();
        lightList();
        ArrayAdapter<String> adapterLight = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,lightList);
        ArrayAdapter<String> adapterSensor = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,sensorList);
        ArrayAdapter<String> adapterProfile = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,profileList);

        adapterLight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapterLight);

        adapterSensor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterSensor);

        adapterProfile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterProfile);


        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = eName.getText().toString();
               /* String profileId = eProfileId.getText().toString();
                String sensorId = eSensorId.getText().toString();
                String lightId = eLightId.getText().toString();*/
                String lightSelected = spinner1.getSelectedItem().toString();
                String sensorSelected = spinner2.getSelectedItem().toString();
                String profileSelected = spinner3.getSelectedItem().toString();

                postRooms(name, profileSelected, sensorSelected, lightSelected);
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
        Call<RoomDTO> roomsList = ApiClient.getService().postRooms(roomDTO);

        roomsList.enqueue(new Callback<RoomDTO>() {
            @Override
            public void onResponse(Call<RoomDTO> call, Response<RoomDTO> response) {
                Log.e("Success", "It Worked!");
                getActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onFailure(Call<RoomDTO> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    public void profileList(){

        Call<List<ProfileDTO>> profilesList = ApiClient.getService().getProfiles();

        profilesList.enqueue(new Callback<List<ProfileDTO>>() {
            @Override
            public void onResponse(Call<List<ProfileDTO>> call, Response<List<ProfileDTO>> response) {
                if (response.isSuccessful()){
                    List<ProfileDTO> profileDTOS = response.body();

                    for(int i=0;i<profileDTOS.size();i++){
                       // String line = profileDTOS.get(i).getName() + profileDTOS.get(i).getId();
                            profileList.add(profileDTOS.get(i).getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProfileDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });
    }


    public void sensorList(){

        Call<List<SensorDTO>> sensorsDTO = ApiClient.getService().getSensors();

        sensorsDTO.enqueue(new Callback<List<SensorDTO>>() {
            @Override
            public void onResponse(Call<List<SensorDTO>> call, Response<List<SensorDTO>> response) {
                if (response.isSuccessful()){
                    List<SensorDTO> sensorDTOS = response.body();

                    for(int i=0;i<sensorDTOS.size();i++){
                        // String line = profileDTOS.get(i).getName() + profileDTOS.get(i).getId();
                        sensorList.add(sensorDTOS.get(i).getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SensorDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });
    }

    public void lightList(){

        Call<List<LightDTO>> lightDTO = ApiClient.getService().getLights();

        lightDTO.enqueue(new Callback<List<LightDTO>>() {
            @Override
            public void onResponse(Call<List<LightDTO>> call, Response<List<LightDTO>> response) {
                if (response.isSuccessful()){
                    List<LightDTO> sensorDTOS = response.body();


                    for(int i=0;i<sensorDTOS.size();i++){
                        // String line = profileDTOS.get(i).getName() + profileDTOS.get(i).getId();
                        lightList.add(sensorDTOS.get(i).getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LightDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
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

        if (  spinner1.getSelectedItem().toString().trim().isEmpty()){
            ((TextView)spinner1.getSelectedView()).setError("Required.");
            valid = false;
        } else {
            ((TextView)spinner1.getSelectedView()).setError(null);
        }

        if (  spinner2.getSelectedItem().toString().trim().isEmpty()){
            ((TextView)spinner2.getSelectedView()).setError("Required.");
            valid = false;
        } else {
            ((TextView)spinner2.getSelectedView()).setError(null);
        }

        if (  spinner3.getSelectedItem().toString().trim().isEmpty()){
            ((TextView)spinner3.getSelectedView()).setError("Required.");
            valid = false;
        } else {
            ((TextView)spinner3.getSelectedView()).setError(null);
        }

        return valid;
    }
}