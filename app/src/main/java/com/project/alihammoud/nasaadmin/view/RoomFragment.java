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
import com.project.alihammoud.nasaadmin.controller.RoomsAdapter;
import com.project.alihammoud.nasaadmin.model.RoomDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RoomFragment extends Fragment {

    RecyclerView recyclerView;
    RoomsAdapter roomsAdapter;
    FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_room, container, false);


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
                ft.replace(R.id.frame, new AddRoomFragment()).addToBackStack("rooms");
                ft.commit();
            }
        });

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.room_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        roomsAdapter = new RoomsAdapter();

        /*Runnable update = new Runnable() {
            @Override
            public void run() {
                getRooms();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(update, 0, 3, TimeUnit.SECONDS);*/

        getRooms();

        return view;
    }

    public void getRooms(){
        Call<List<RoomDTO>> roomsList = ApiClient.getService().getRooms();

        roomsList.enqueue(new Callback<List<RoomDTO>>() {
            @Override
            public void onResponse(Call<List<RoomDTO>> call, Response<List<RoomDTO>> response) {
                if (response.isSuccessful()){
                    List<RoomDTO> RoomDTO = response.body();
                    roomsAdapter.setData(RoomDTO);
                    recyclerView.setAdapter(roomsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<RoomDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });
    }
}