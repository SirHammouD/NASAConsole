package com.project.alihammoud.nasaadmin;

public class RoomDTO {

    private String name;
    private String profileId;
    private String sensorId;
    private String lightId;
    private String _id;



    @Override
    public String toString() {
        return "RoomDTO{" +
                "name='" + name + '\'' +
                ", profileId='" + profileId + '\'' +
                ", sensorId='" + sensorId + '\'' +
                ", lightId='" + lightId + '\'' +
                ", id='" + _id +
                '}';
    }

    public RoomDTO(String name, String profileId, String sensorId, String lightId) {
        this.name = name;
        this.profileId = profileId;
        this.sensorId = sensorId;
        this.lightId = lightId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getLightId() {
        return lightId;
    }

    public void setLightId(String lightId) {
        this.lightId = lightId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
}
