## 1/- Suppose we put all the functions inside the same service. Is this the
## recommended approach ? Please explain.

**Regrouper toutes les fonctionnalités dans un seul service n'est pas recommandé.**

###  Inconvénients :
- **Difficile à maintenir et à tester** : les modifications apportées à une fonctionnalité peuvent affecter tout le service.
- **Faible scalabilité** : il est difficile de faire évoluer ou réutiliser des composants individuellement.

###  Approche recommandée :
Séparer les responsabilités en plusieurs services spécialisés :
- `UserService`
- `RoomService`
- `BookingService`

---

## 2/- In this design, we chose to have a function setRoom(..) that should
## not impact the previous bookings. What is another way ? What is your
## recommendation ? Please explain and justify.

###  Recommandation :

Je recommande d’utiliser une autre approche, qui consiste à **copier les informations de la chambre et les stocker dans la réservation**.  
Ainsi, les réservations restent **cohérentes et immuables**, même si les données de la chambre changent par la suite.

###  Avantages :
- Les données de la réservation restent **fiables et historiques**.
- Aucune dépendance avec des modifications futures dans la classe `Room`.

###  Exemple de code Java :

```java
class BookedRoomDetails {
    RoomType type;
    int pricePerNight;
}

class Booking {
    int bookingId;
    int userId;
    int roomId;
    BookedRoomDetails bookedRoomDetails;
    Date checkIn;
    Date checkOut;
}

// Lors de la réservation :
BookedRoomDetails bookedRoomDetails = new BookedRoomDetails(RoomType.STANDARD, 1000);
Booking booking = new Booking(bookingId, userId, roomId, bookedRoomDetails, checkIn, checkOut);
