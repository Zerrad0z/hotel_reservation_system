package com.example;

public class Room {
    private int id;
    private int pricePerNight;
    private RoomType type;

    public Room(int id, RoomType type, int pricePerNight) {
        this.id = id;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public RoomType getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
