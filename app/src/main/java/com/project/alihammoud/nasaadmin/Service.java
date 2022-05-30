package com.project.alihammoud.nasaadmin;

import com.project.alihammoud.nasaadmin.model.LightDTO;
import com.project.alihammoud.nasaadmin.model.NotiDTO;
import com.project.alihammoud.nasaadmin.model.ProfileDTO;
import com.project.alihammoud.nasaadmin.model.RoomDTO;
import com.project.alihammoud.nasaadmin.model.SensorDTO;
import com.project.alihammoud.nasaadmin.model.TroubleshootDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface Service {

//GET
    @GET("lights")
    Call<List<LightDTO>> getLights();

    @GET("sensors")
    Call<List<SensorDTO>> getSensors();

    @GET("rooms")
    Call<List<RoomDTO>> getRooms();

    @GET("profiles")
    Call<List<ProfileDTO>> getProfiles();

    @GET("notifications")
    Call<List<NotiDTO>>getNotification();

    @GET("debug")
    Call<TroubleshootDTO> getTroubleshoot();


//POST
    @POST("light")
    Call<LightDTO> postLights(@Body LightDTO lightDTO);

    @POST("sensor")
    Call<SensorDTO> postSensors(@Body SensorDTO sensorDTO);

    @POST("room")
    Call<RoomDTO> postRooms(@Body RoomDTO roomDTO);

    @POST("profile")
    Call<ProfileDTO> postProfiles(@Body ProfileDTO profileDTO);


//DELETE_ID
    @DELETE("light/{id}")
    Call<LightDTO> deleteLights(@Path("id" ) String id);

    @DELETE("sensor/{id}")
    Call<SensorDTO> deleteSensors(@Path("id") String id);

    @DELETE("room/{id}")
    Call<RoomDTO> deleteRooms(@Path("id") String id);

    @DELETE("profile/{id}")
    Call<ProfileDTO> deleteProfiles(@Path("id") String id);


//PUT_ID
    @PUT("light/{id}")
    Call<List<LightDTO>> putLights(@Path("id") String id, @Body LightDTO post);

    @PUT("sensor/{id}")
    Call<List<SensorDTO>> putSensors(@Path("id") String id, @Body SensorDTO post);

    @PUT("room/{id}")
    Call<List<RoomDTO>> putRooms(@Path("id") String id, @Body RoomDTO post);

    @PUT("profile/{id}")
    Call<List<ProfileDTO>> putProfiles(@Path("id") String id, @Body ProfileDTO post);


//PATCH_ID
    @PATCH("light/{id}")
    Call<List<LightDTO>> patchLights(@Path("id") int id, @Body LightDTO post);

    @PATCH("sensor/{id}")
    Call<List<SensorDTO>> patchSensors(@Path("id") int id, @Body SensorDTO post);

    @PATCH("room/{id}")
    Call<List<RoomDTO>> patchRooms(@Path("id") int id, @Body RoomDTO post);

    @PATCH("profile/{id}")
    Call<List<ProfileDTO>> patchProfiles(@Path("id") int id, @Body ProfileDTO post);
}
