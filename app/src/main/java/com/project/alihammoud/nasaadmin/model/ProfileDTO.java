package com.project.alihammoud.nasaadmin.model;

import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

public class ProfileDTO {

    private String name;
    private boolean isPlant;
    private String _id;
    private LinkedList<String> rhythmList;
    private  LinkedList<String> intensityList;
    private  String[] timeList;

    public ProfileDTO(String name, boolean isPlant, LinkedList<String> rhythmList, LinkedList<String> intensityList, String[] timeList) {
        this.name = name;
        this.isPlant = isPlant;
        this.rhythmList = rhythmList;
        this.intensityList = intensityList;
        this.timeList = timeList;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "name='" + name + '\'' +
                ", isPlant=" + isPlant +
                ", _id='" + _id + '\'' +
                ", rhythmList=" + rhythmList +
                ", intensityList=" + intensityList +
                ", timeList=" + timeList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isPlant() {
        return isPlant;
    }

    public void setPlant(boolean plant) {
        isPlant = plant;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public LinkedList<String> getRhythmList() {
        return rhythmList;
    }

    public void setRhythmList(LinkedList<String> rhythmList) {
        this.rhythmList = rhythmList;
    }

    public LinkedList<String> getIntensityList() {
        return intensityList;
    }

    public void setIntensityList(LinkedList<String> intensityList) {
        this.intensityList = intensityList;
    }

    public String[] getTimeList() {
        return timeList;
    }

    public void setTimeList(String[] timeList) {
        this.timeList = timeList;
    }
}
