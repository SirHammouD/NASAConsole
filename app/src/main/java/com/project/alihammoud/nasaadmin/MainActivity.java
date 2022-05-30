package com.project.alihammoud.nasaadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;

import com.project.alihammoud.nasaadmin.controller.ApiClient;
import com.project.alihammoud.nasaadmin.model.NotiDTO;
import com.project.alihammoud.nasaadmin.view.LoginFragment;
import com.project.alihammoud.nasaadmin.view.MainFragment;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private MainFragment mainFragment;
    private LoginFragment loginFragment;
    private FrameLayout mMainFrame;
    private ServerSocket serverSocket;
    private int localPort;
    private NsdManager nsdManager;
    private String serviceName ="test";
    private NsdManager.RegistrationListener registrationListener;
    private NsdManager.DiscoveryListener discoveryListener;
    private NsdManager.ResolveListener resolveListener;
    private static final String TAG ="Device Discovery";
    public static final String SERVICE_TYPE = "_http._tcp.";
    //SERVICE_TYPE = "_services._dns-sd._udp"
    int i;
    private static ArrayList<NsdServiceInfo> ServicesAvailable = new ArrayList<>();

    final Handler handler = new Handler();
    Runnable r;
    public AlertDialog alertDialog;
    FragmentManager fragmentManager;
    boolean inLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout) findViewById(R.id.frame);
        mainFragment = new MainFragment();
        loginFragment = new LoginFragment();

        SetFragment(loginFragment,"Login");



           r = new Runnable() {
               public void run() {
                   handler.postDelayed(r, 1000000);
                   final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.noti);
                   mp.start();
                   noti();
                   //Toast.makeText(getContext(),"Degradation",Toast.LENGTH_SHORT).show();
               }
           };
           r.run();


        /*try {
            initializeServerSocket();
            initializeRegistrationListener();
            initializeDiscoveryListener();
            registerService(localPort);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


    private void noti(){
        Call<List<NotiDTO>> notification = ApiClient.getService().getNotification();
        notification.enqueue(new Callback<List<NotiDTO>>() {
            @Override
            public void onResponse(Call<List<NotiDTO>>  call, Response<List<NotiDTO>> response) {

                List<NotiDTO> posts = response.body();

                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(MainActivity.this);
                String notification = "";
                for(int i=0;i<posts.size();i++){
                    notification =  notification + posts.get(i).getDevice()+ System.lineSeparator();
                }
                builder.setMessage(""+notification);
                builder.setTitle("Alert, please check the device below!");
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
            public void onFailure(Call<List<NotiDTO>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage() );

            }
        });

    }

    public void initializeServerSocket() throws IOException {
        // Initialize a server socket on the next available port.

        serverSocket = new ServerSocket(8089);
        // Store the chosen port.
        localPort = serverSocket.getLocalPort();

        Log.i(TAG, "IP " + serverSocket.getInetAddress().getAddress().toString()
                + ", running on port " + localPort);



    }

    public void registerService(int port) {
        // Create the NsdServiceInfo object, and populate it.
        NsdServiceInfo serviceInfo = new NsdServiceInfo();

        // The name is subject to change based on conflicts
        // with other services advertised on the same network.
        serviceInfo.setServiceName("test2");
        serviceInfo.setServiceType(SERVICE_TYPE);
        serviceInfo.setPort(localPort);

        nsdManager = (NsdManager) this.getSystemService(Context.NSD_SERVICE);

        nsdManager.registerService(
                serviceInfo, NsdManager.PROTOCOL_DNS_SD, registrationListener);

    }


    public void initializeRegistrationListener() {
        registrationListener = new NsdManager.RegistrationListener() {

            @Override
            public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
                // Save the service name. Android may have changed it in order to
                // resolve a conflict, so update the name you initially requested
                // with the name Android actually used.
                serviceName = NsdServiceInfo.getServiceName();
                nsdManager.discoverServices(
                        SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
            }

            @Override
            public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Registration failed! Put debugging code here to determine why.
            }

            @Override
            public void onServiceUnregistered(NsdServiceInfo arg0) {
                // Service has been unregistered. This only happens when you call
                // NsdManager.unregisterService() and pass in this listener.
                Log.i(TAG, "Service Unregistered: " + arg0);

            }

            @Override
            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Unregistration failed. Put debugging code here to determine why.
            }
        };
        Log.d("test", "initialized registration listener");
    }

    public void initializeDiscoveryListener() {

        // Instantiate a new DiscoveryListener
        discoveryListener = new NsdManager.DiscoveryListener() {

            // Called as soon as service discovery begins.
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "Service discovery started");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                // A service was found! Do something with it.
                //Log.d(TAG, "Service discovery success" + service);
                Log.d(TAG, "Service discovery success " + service);
                ServicesAvailable.add(service);

              /* if (!service.getServiceType().equals("_nsdchat._tcp")) {
                    // Service type is the string containing the protocol and
                    // transport layer for this service.
                    Log.d(TAG, "Unknown Service Type: " + service.getServiceType());

                } else if (service.getServiceName().equals(serviceName)) {
                    // The name of the service tells the user what they'd be
                    // connecting to. It could be "Bob's Chat App".
                    Log.d(TAG, "Same machine: " + serviceName);
                } else //if (service.getServiceName().contains("NASA_Controller"))
               {
                   //nsdManager.resolveService(service, resolveListener);
                   Log.d(TAG, "Service Names: " + service.getServiceName());

                }*/

            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                // When the network service is no longer available.
                // Internal bookkeeping code goes here.
                Log.e(TAG, "service lost: " + service);
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                nsdManager.stopServiceDiscovery(this);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                nsdManager.stopServiceDiscovery(this);
            }
        };
    }


    private void SetFragment(Fragment fragment, String tag){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, "console");
        fragmentTransaction.commit();

    }

   /* @Override
    protected void onDestroy() {
       tearDown();
        super.onDestroy();
    }

    // NsdHelper's tearDown method
    public void tearDown() {
        nsdManager.unregisterService(registrationListener);
        nsdManager.stopServiceDiscovery(discoveryListener);
    }*/
}