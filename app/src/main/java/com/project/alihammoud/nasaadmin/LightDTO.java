package com.project.alihammoud.nasaadmin;

public class LightDTO {

    private String name;
    private String hostname;
    private String _id;
  

    @Override
    public String toString() {
        return "LightDTO{" +
                ", name='" + name + '\'' +
                ", hostname='" + hostname + '\'' +
                ", id='" + _id +
                '}';
    }

    public LightDTO(String name, String hostname) {
        this.name = name;
        this.hostname = hostname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

}
