package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {

        // Service instance
        Service hotelService = new Service();

        // date formater
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // ------------------------ Creating Rooms ----------------------------
        hotelService.setRoom(1,RoomType.STANDARD,1000);
        hotelService.setRoom(2,RoomType.JUNIOR,2000);
        hotelService.setRoom(3,RoomType.SUITE,3000);

        // ------------------------ Creating Users ----------------------------
        hotelService.setUser(1,5000);
        hotelService.setUser(2, 10000);
        System.out.println("=====================================================================================");

        System.out.println("- scenario 1 : User 1 tries booking Room 2 from 30/06/2026 to 07/07/2026 (7 nights) :");
        // checkIn and checkOut dates formated for user1
        Date checkIn1 = dateFormat.parse("30/06/2026");
        Date checkOut1 = dateFormat.parse("07/07/2026");
        try {
            hotelService.bookRoom(1,2,checkIn1,checkOut1);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("");


        System.out.println("- scenario 2 : User 1 tries booking Room 2 from 07/07/2026 to 30/06/2026 :");
        //checkIn and checkOut for user1
        Date checkIn2 = dateFormat.parse("07/07/2026");
        Date checkOut2 = dateFormat.parse("30/06/2026");
        try {
            hotelService.bookRoom(1,2,checkIn2,checkOut2);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("");

        System.out.println("- scenario 3 : User 1 tries booking Room 1 from 07/07/2026 to 08/07/2026 (1 night) :");
        // checkIn and checkOut for user2
        Date checkIn3 = dateFormat.parse("07/07/2026");
        Date checkOut3 = dateFormat.parse("08/07/2026");
        try {
            hotelService.bookRoom(1,1,checkIn3,checkOut3);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("");

        System.out.println("- scenario 4 : User 2 tries booking Room 1 from 07/07/2026 to 09/07/2026 (2 nights) :");
        // checkIn and checkOut for user2
        Date checkIn4 = dateFormat.parse("07/07/2026");
        Date checkOut4 = dateFormat.parse("09/07/2026");
        try {
            hotelService.bookRoom(2,1,checkIn4,checkOut4);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("");

        System.out.println("- scenario 5 : User 2 tries booking Room 3 from 07/07/2026 to 08/07/2026 (1 night) :");
        // checkIn and checkOut for user2
        Date checkIn5 = dateFormat.parse("07/07/2026");
        Date checkOut5 = dateFormat.parse("08/07/2026");
        try {
            hotelService.bookRoom(2,3,checkIn5,checkOut5);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("");

        // Changing room 1 properties
        hotelService.setRoom(1,RoomType.SUITE,10000);

        System.out.println("-------------------------------------------------------------------");
        hotelService.printAll();

        System.out.println("-------------------------------------------------------------------\n");
        hotelService.printAllUsers();

        System.out.println("-------------------------------------------------------------------\n");
        hotelService.printAllRooms();

    }
}