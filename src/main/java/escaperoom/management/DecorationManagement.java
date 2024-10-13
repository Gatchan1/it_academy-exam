package escaperoom.management;

import escaperoom.escaperoomelement.Decoration;
import escaperoom.exception.NotFoundElementException;

public class DecorationManagement {

    public static void removeDecoration(int id) throws NotFoundElementException {
        RoomManagement.getRooms().stream()
                .filter(room -> room.getDecorations().stream().anyMatch(decoration -> decoration.getId() == id))
                .findFirst()
                .ifPresentOrElse(room -> {
                    Decoration foundDecoration = room.getDecorations().stream()
                            .filter(decoration -> decoration.getId() == id)
                            .findFirst()
                            .get();             // We already filtered, we know it exists.
                    room.removeDecoration(foundDecoration);
                }, () -> {
                    throw new NotFoundElementException("No existe ninguna decoración con el id introducido");
                });
    }
}
