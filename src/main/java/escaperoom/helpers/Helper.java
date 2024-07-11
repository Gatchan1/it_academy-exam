package escaperoom.helpers;

import escaperoom.components.Clue;
import escaperoom.components.ThemedRoom;
import escaperoom.exceptions.DuplicateRoomException;
import escaperoom.exceptions.NotFoundRoomException;

import java.util.ArrayList;

public class Helper {
    public static void addRoom(ThemedRoom room, ArrayList<ThemedRoom> rooms) throws DuplicateRoomException {
        for (ThemedRoom r : rooms) {
            if (r.equals(room)) {
                throw new DuplicateRoomException("Ya existe una sala igual.");
            }
        }
        rooms.add(room);
    }

    public static ThemedRoom chooseRoom(int id, ArrayList<ThemedRoom> rooms) throws NotFoundRoomException {
        ThemedRoom foundRoom = null;
        for (ThemedRoom room : rooms) {
            if (room.getId() == id) {
                foundRoom = room;
            }
        }
        if (foundRoom == null) {
            throw new NotFoundRoomException("No existe ninguna sala con el id introducido.");
        }
        return foundRoom;
    }

    public static Clue createClue() {
        String name = Input.readString("Introduce el nombre de la pista:");
        float price = Input.readFloat("Introduce el precio (sin IVA) de la pista:");
        int time = Input.readInt("Introduce el tiempo estimado" +
                "de resolución de la pista (en minutos):");
        String theme = Input.readString("Introduce el tema de la pista:");
        return new Clue(name, price, time, theme);
    }
}
