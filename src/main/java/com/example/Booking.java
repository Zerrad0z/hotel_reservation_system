package com.example;

import java.util.Date;

public class Booking {
    private int userId;
    private int roomId;
    private Date checkIn;  // date d’arrivée
    private Date checkOut; // date de depart
    private int periode;   // la période de reservation de checkIn a checkOut
    private double totalPrice; // prix total de la réservation

    public Booking(int userId, int roomId, Date checkIn, Date checkOut, int periode, double totalPrice) {
        this.userId = userId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.periode = periode;
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getPeriode() {
        return periode;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
