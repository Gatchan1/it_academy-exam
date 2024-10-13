package escaperoom.management;

import java.util.List;

public class InventoryManagement {

    public static boolean isInventoryEmpty() {
        return noClues() && noDecorations();
    }
    public static boolean noClues() {
        return RoomManagement.getRooms().stream()
                .allMatch(room -> room.getClues().isEmpty());
    }
    public static boolean noDecorations() {
        return RoomManagement.getRooms().stream()
                .allMatch(room -> room.getDecorations().isEmpty());
    }

    public static void printInventory() {
        printCluesInventory();
        printDecorationsInventory();
    }
    public static void printCluesInventory() {
        RoomManagement.getRooms().forEach(room -> printItems(room.getClues(), room.getId()));
    }
    public static void printDecorationsInventory() {
        RoomManagement.getRooms().forEach(room -> printItems(room.getDecorations(), room.getId()));
    }
    
    private static <T> void printItems(List<T> items, int roomId) {
        items.forEach(item -> System.out.println(item + " belonging to room " + roomId));
    }
}