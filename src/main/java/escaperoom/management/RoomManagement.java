package escaperoom.management;

import escaperoom.escaperoomelement.Room;
import escaperoom.exception.DuplicateElementException;
import escaperoom.exception.NotFoundElementException;

import java.util.ArrayList;

public class RoomManagement {
    private static final ArrayList<Room> rooms = new ArrayList<Room>();

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static void addRoom(Room room) throws DuplicateElementException {
        if (rooms.stream().anyMatch(r -> r.equals(room))) {
            throw new DuplicateElementException("Ya existe una sala igual");
        }
        rooms.add(room);
    }

    public static Room findRoom(int roomId) throws NotFoundElementException {
        return rooms.stream()
                .filter(room -> room.getId() == roomId)
                .findFirst()
                .orElseThrow(() -> new NotFoundElementException("No existe ninguna sala con el id introducido"));
    }
}
