import java.util.*;

class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Room " + roomNumber + " reserved.");
        } else {
            System.out.println("Room " + roomNumber + " is already reserved.");
        }
    }

    public void checkIn() {
        if (!isAvailable) {
            System.out.println("Checked in to Room " + roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " is not reserved yet.");
        }
    }

    public void checkOut() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Checked out from Room " + roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " was not occupied.");
        }
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - $" + price;
    }
}

public class HotelReservationSystem {
    private List<Room> rooms;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        rooms.add(new Room(101, "Single", 100.0));
        rooms.add(new Room(102, "Double", 150.0));
        rooms.add(new Room(103, "Suite", 250.0));
    }

    public void listRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        system.listRooms();

        Room room1 = system.rooms.get(0);
        room1.reserve();
        room1.checkIn();
        room1.checkOut();
    }
}
