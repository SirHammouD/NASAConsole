package com.project.alihammoud.nasaadmin;

public class SensorDTO {

    private String hostname;
    private String brightness;
    private String[] color;
    private String _id;
    private String name;


    public SensorDTO(String hostname, String name) {
        this.hostname = hostname;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "hostname='" + hostname + '\'' +
                ", brightness='" + brightness + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", id='" + _id +
                '}';
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
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

    public void setId(String id) {
        this._id = _id;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }
}
