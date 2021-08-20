package com.elysino.androidtask.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Main {
    @SerializedName("main")
    Temp temp;

    @SerializedName("name")
    private String name;

    @SerializedName("weather")
    private List<Weather> weather = null;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }


}
