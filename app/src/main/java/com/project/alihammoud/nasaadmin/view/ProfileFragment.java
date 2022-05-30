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
import com.project.alihammoud.nasaadmin.controller.ProfilesAdapter;
import com.project.alihammoud.nasaadmin.model.ProfileDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    ProfilesAdapter profilesAdapter;
    FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

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
                ft.replace(R.id.frame, new AddProfileFragment()).addToBackStack("profiles");
                ft.commit();
            }
        });

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.profile_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        profilesAdapter = new ProfilesAdapter();

       /* Runnable update = new Runnable() {
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
                    List<ProfileDTO> profileDTOs = response.body();
                    List<ProfileDTO> posts = new ArrayList<ProfileDTO>();

                    for(int i=0;i<profileDTOs.size();i++){
                        if(profileDTOs.get(i).isPlant()==false){
                            posts.add(profileDTOs.get(i));
                        }
                    }
                    profilesAdapter.setData(posts);
                    recyclerView.setAdapter(profilesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ProfileDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }


        });
    }
}