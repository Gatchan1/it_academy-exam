package escaperoom.management;

import escaperoom.escaperoomelement.Clue;
import escaperoom.exception.NotFoundElementException;

public class ClueManagement {

    public static void removeClue(int id) throws NotFoundElementException {
        RoomManagement.getRooms().stream()
                .filter(room -> room.getClues().stream().anyMatch(clue -> clue.getId() == id))
                .findFirst()
                .ifPresentOrElse(room -> {
                    Clue foundClue = room.getClues().stream()
                            .filter(clue -> clue.getId() == id)
                            .findFirst()
                            .get();             // We already filtered, we know it exists.
                    room.removeClue(foundClue);
                }, () -> {
                    throw new NotFoundElementException("No existe ninguna pista con el id introducido");
                });
    }

}
