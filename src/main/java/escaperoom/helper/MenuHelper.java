package escaperoom.helper;

import escaperoom.enums.Difficulty;
import escaperoom.escaperoomelement.Clue;
import escaperoom.escaperoomelement.Decoration;
import escaperoom.escaperoomelement.Room;
import escaperoom.exception.*;
import escaperoom.management.ClueManagement;
import escaperoom.management.DecorationManagement;
import escaperoom.management.RoomManagement;

public class MenuHelper {
    public static void tryRoomCreation(String name, Difficulty difficulty) {
        Room newRoom = new Room(name, difficulty);
        try {
            RoomManagement.addRoom(newRoom);
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Difficulty formatDifficulty(int difficultyNumber) {
        return switch (difficultyNumber) {
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.HARD;
            default -> Difficulty.UNKNOWN;
        };
    }

    public static void tryRemoveClue(int id) {
        try {
            ClueManagement.removeClue(id);
            System.out.println("Pista eliminada satisfactoriamente");
        } catch (NotFoundElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void tryRemoveDecoration(int id) {
        try {
            DecorationManagement.removeDecoration(id);
            System.out.println("Decoración eliminada satisfactoriamente");
        } catch (NotFoundElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void tryClueCreation(Room chosenRoom, String clueName, double cluePrice,
                                       int clueTime, String clueTheme) {
        Clue newClue = new Clue(clueName, cluePrice, clueTime, clueTheme);
        try {
            chosenRoom.addClue(newClue);
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void tryDecorationCreation(Room chosenRoom, String decorationName, int decorationQuantity,
                                             double decorationPrice, String decorationMaterial) {
        Decoration newDecoration = new Decoration(decorationName, decorationQuantity, decorationPrice, decorationMaterial);
        try {
            chosenRoom.addDecoration(newDecoration);
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
