package com.example.user.wsr_2.Room;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomResponse {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("name")
    @Expose
    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }
}
