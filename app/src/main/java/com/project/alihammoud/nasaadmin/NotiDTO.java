package com.project.alihammoud.nasaadmin;

public class NotiDTO {

     String message;
     String device;


    public NotiDTO(String message, String device) {
        this.message = message;
        this.device = device;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "message='" + message + '\'' +
                ", device='" + device + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
