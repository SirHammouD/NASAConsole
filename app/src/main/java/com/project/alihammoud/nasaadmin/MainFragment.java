package com.project.alihammoud.nasaadmin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment implements View.OnClickListener{

public CardView lights;
public CardView sensors;
public CardView profiles;
public CardView plants;
public CardView rooms;
public CardView logo;
public CardView logout;
public CardView troubleshoot;
public CardView party;
public AlertDialog alertDialog;
public TextView logcat;
Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        context = getContext();


        LightFragment lightFragment = new LightFragment();
        SensorFragment sensorFragment = new SensorFragment();
        RoomFragment roomFragment = new RoomFragment();
        PlantFragment plantFragment = new PlantFragment();
        ProfileFragment profileFragment = new ProfileFragment();


        if (this.getArguments()!=null) {
            Bundle bundle = this.getArguments();
            String username = bundle.getString("username");
            Toast.makeText(context,username,Toast.LENGTH_LONG).show();
           /* bundle.putString("username", username);

            lightFragment.setArguments(bundle);
            sensorFragment.setArguments(bundle);
            roomFragment.setArguments(bundle);
            plantFragment.setArguments(bundle);
            profileFragment.setArguments(bundle);*/
        }

        logcat = view.findViewById(R.id.logcat);

    lights = view.findViewById(R.id.lights);
        lights.setOnClickListener(this);
    sensors = view.findViewById(R.id.sensors);
        sensors.setOnClickListener(this);
    profiles = view.findViewById(R.id.profiles);
        profiles.setOnClickListener(this);
    plants = view.findViewById(R.id.plants);
        plants.setOnClickListener(this);
    rooms = view.findViewById(R.id.rooms);
        rooms.setOnClickListener(this);
    logo = view.findViewById(R.id.logo);
        logo.setOnClickListener(this);
    logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(this);
    party = view.findViewById(R.id.party);
        party.setOnClickListener(this);
    troubleshoot = view.findViewById(R.id.troubleshoot);
        troubleshoot.setOnClickListener(this);





        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<List<NotiDTO>>  notification = ApiClient.getService().getNotification();


                notification.enqueue(new Callback<List<NotiDTO>> () {
                    @Override
                    public void onResponse(Call<List<NotiDTO>> call, Response<List<NotiDTO>>  response) {
                        List<NotiDTO> posts = response.body();

                        // logcat.setText(posts.getMessage()+"\n");

                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(context);
                        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.noti);
                        mp.start();
                        String notification = "";
                        for(int i=0;i<posts.size();i++){
                            notification =  notification + posts.get(i).getDevice()+ System.lineSeparator();
                        }
                        builder.setMessage(""+notification);
                        builder.setTitle("Alert, please check the device below!");
                        //Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                        builder.setCancelable(false);
                        builder
                                .setPositiveButton(
                                        "Ok",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which)
                                            {
                                                alertDialog.dismiss();
                                                // When the user click yes button
                                                // then app will close

                                            }
                                        });

                        // Create the Alert dialog
                        alertDialog = builder.create();
                        // Show the Alert Dialog box
                        alertDialog.show();


                    }

                    @Override
                    public void onFailure(Call<List<NotiDTO>>  call, Throwable t) {
                        Log.e("Failure", t.getLocalizedMessage() );
                    }
                });

            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lights:
                SetFragment(new LightFragment(),"lights");
                break;
            case R.id.rooms:
                SetFragment(new RoomFragment(),"rooms");
                break;
            case R.id.profiles:
                SetFragment(new ProfileFragment(),"profiles");
                break;
            case R.id.sensors:
                SetFragment(new SensorFragment(),"sensors");
                break;
            case R.id.plants:
                SetFragment(new PlantFragment(),"plants");
                break;
            case R.id.party:
                rickRoll();
                break;
            case R.id.troubleshoot:
                troubleshoot();
                break;
            case R.id.logout:
                LoginHelper loginHelper = new LoginHelper();
                loginHelper.setUsername("");
                loginHelper.setPassword("");
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new LoginFragment());
                ft.commit();
                break;


        }

    }

    private void SetFragment(Fragment fragment, String tag){

        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment).addToBackStack(tag);
        ft.commit();

    }

    private void troubleshoot(){


       Call<TroubleshootDTO> troubleshootDTO =  ApiClient.getService().getTroubleshoot();

        troubleshootDTO.enqueue(new Callback<TroubleshootDTO>() {
            @Override
            public void onResponse(Call<TroubleshootDTO> call, Response<TroubleshootDTO> response) {

               // logcat.setText( response.body().toString()+"\n");

                TroubleshootDTO posts = response.body();

                    logcat.setText(posts.getMessage()+"\n");

            }

            @Override
            public void onFailure(Call<TroubleshootDTO> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );
            }
        });




    }
    private void rickRoll(){

        Dialog dialogR = new Dialog(context);
        dialogR.setContentView(R.layout.rick_roll);
        dialogR.setTitle("Title...");
        VideoView videoView = dialogR.findViewById(R.id.video);
        String uriPath = "android.resource://" + context.getPackageName() + "/raw/rickroll";
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);
        videoView.start();

        // Show the Alert Dialog box
        dialogR.show();

    }


}