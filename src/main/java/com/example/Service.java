package com.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Service {

    // Liste des Users
    ArrayList<User> users = new ArrayList<>();

    // Liste des chambres
    ArrayList<Room> rooms = new ArrayList<>();

    // Liste de toutes les réservations
    ArrayList<Booking> bookings = new ArrayList<>();


    // methode pour réserver une chambre
    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {

        // inputs validation
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Veuillez entrer les dates d’arrivée et de départ");
        }
        if (checkOut.before(checkIn)) {
            throw new IllegalArgumentException("La date d’arrivée doit être antérieure à la date de départ");
        }

        // Vérifie si la chambre existe
        Room room = rooms.stream().filter(r -> r.getId() == roomNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("La chambre n'existe pas"));

        // Vérifie si room existe
        User user = users.stream().filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User n'existe pas"));

        // Calcule la durée du séjour et le prix total
        int periodDays = (int) ((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
        double periodPrice = room.getPricePerNight() * periodDays;

        // Vérifie si le solde de l'utilisateur est suffisant
        if (user.getBalance() >= periodPrice) {

            // Vérifie si la chambre est déjà réservée pour la période souhaitée
            boolean isBooked = bookings.stream()

                    // 1- On compare si la date de check-in de la nouvelle réservation est après
                    // la date de check-out de l’ancienne réservation.
                    .filter(r -> r.getRoomId() == roomNumber)
                    .anyMatch(r -> r.getCheckOut().after(checkIn)

                            // 2- On compare aussi si la date de check-out de la nouvelle réservation
                            // est avant la date de check-in de l’ancienne réservation.
                            && r.getCheckIn().before(checkOut));

            if (!isBooked) {
                Booking booking = new Booking(userId, roomNumber, checkIn, checkOut, periodDays, periodPrice);
                bookings.add(booking);
                user.setBalance(user.getBalance() - periodPrice);
                System.out.println("Réservation effectuée avec succès");
            } else throw new IllegalArgumentException("La chambre est déjà réservée pour cette période");

        } else {
            throw new IllegalArgumentException("Solde insuffisant pour effectuer la réservation");
        }
    }

    /*
    Crée ou met à jour un User
     */
    public void setUser(int userId, double balance) {
        // Vérifie si User existe déjà
        User userExists = users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElse(null);
        if (userExists != null) {
            // Si existe met à jour du balance
            userExists.setBalance(balance);
        } else {
            // Sinon créer nouvel User
            User user = new User(userId, balance);
            users.add(user);
        }
    }

    /*
    Crée ou met à jour une chambre
     */
    public void setRoom(int roomId, RoomType roomType, int pricePerNight) {
        // Vérifie si la chambre existe déjà
        Room roomExists = rooms.stream()
                .filter(r -> r.getId() == roomId)
                .findFirst()
                .orElse(null);
        if (roomExists != null) {
            // Si existe met à jour du type et du prix
            roomExists.setType(roomType);
            roomExists.setPricePerNight(pricePerNight);
        } else {
            // Sinon créer une nouvelle chambre
            Room room = new Room(roomId, roomType, pricePerNight);
            rooms.add(room);
        }
    }

    /*
    Affiche toutes les réservations
     */
    public void printAll() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("============================== Bookings ==============================");
        System.out.printf("%-7s  | %-8s|  %-7s   |  %-7s  | %s | %s%n", "userId", "roomId", "checkIn", "checkOut", "période", "prix total");
        System.out.println("----------------------------------------------------------------------");
        // Inverse l'ordre pour afficher les plus récents en premier
        ArrayList<Booking> reversedBooking = new ArrayList<>(bookings);
        Collections.reverse(reversedBooking);
        for (Booking b : reversedBooking) {
            System.out.printf("%-7d  | %-8d| %-5s | %-7s | %-7d | %-10.2f%n",
                    b.getUserId(),
                    b.getRoomId(),
                    dateFormat.format(b.getCheckIn()),
                    dateFormat.format(b.getCheckOut()),
                    b.getPeriode(),
                    b.getTotalPrice()
            );
        }
    }

    /*
    Affiche tous les utilisateurs
     */
    public void printAllUsers() {
        System.out.println("===== All Users =====");
        // Vérifie que la liste n'est pas vide
        if (users.isEmpty()) {
            System.out.println("Aucun User trouvé");
        } else {
            // Inverse l'ordre pour afficher les plus récents en premier
            List<User> reversedUsers = new ArrayList<>(users);
            Collections.reverse(reversedUsers);

            System.out.printf("%-7s   |  %-10s%n", "UserID", "Balance");
            System.out.println("---------------------");

            for (User user : reversedUsers) {
                System.out.printf("%-7d   |  %-10.2f%n", user.getId(), user.getBalance());
            }
        }
    }

    /*
    Affiche toutes les chambres
     */
    public void printAllRooms() {
        System.out.println("============ All Rooms ============ ");
        // Vérifie que la liste n'est pas vide
        if (rooms.isEmpty()) {
            System.out.println("Aucune chambre trouvée");
        } else {
            System.out.printf("%-7s   |   %-7s    | %-10s%n", "RoomID", " Type", "Price");
            System.out.println("----------------------------------");

            for (Room room : rooms) {
                System.out.printf("%-7d   |   %-7s    | %-17d%n", room.getId(), room.getType(), room.getPricePerNight());
            }
        }
    }

}



