package com.project.alihammoud.nasaadmin.model;

public class TroubleshootDTO {

    String message;

    public TroubleshootDTO(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TroubleshootDTO{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
