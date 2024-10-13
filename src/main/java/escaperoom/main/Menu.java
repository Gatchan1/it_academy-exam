package escaperoom.main;

import escaperoom.enums.Difficulty;
import escaperoom.escaperoomelement.Clue;
import escaperoom.escaperoomelement.Room;
import escaperoom.exception.NotFoundElementException;
import escaperoom.helper.Input;
import escaperoom.helper.MenuHelper;
import escaperoom.management.InventoryManagement;
import escaperoom.management.RoomManagement;

public class Menu {
    private static final String invalidChoiceMessage = "Escull una opció vàlida";

    public static void start() {
        boolean sortir = false;

        RoomManagement.getRooms().add(new Room("a", Difficulty.EASY));
        RoomManagement.getRooms().add(new Room("b", Difficulty.HARD));
        RoomManagement.getRooms().getFirst().addClue(new Clue("pista1", 10, 5, "spooky"));

        System.out.println("\nBienvenido a 'La sala de los Enigmas'");
        do {
            switch (chooseOption()) {
                case 1 -> createRoom();
                case 2 -> addClueToRoom();
                case 3 -> addDecorationToRoom();
                case 4 -> showInventory();
                case 5 -> removeFromInventory();
                case 0 -> {
                    System.out.println("Gràcies per utilitzar l'aplicació");
                    sortir = true;
                }
                default -> System.out.println(invalidChoiceMessage);
            }
        } while (!sortir);
    }

    private static byte chooseOption() {
        return Input.readRangeByte("""
                \nEscoge una opción:
                1. Crear sala.
                2. Añadir pista a una sala.
                3. Añadir decoración a una sala.
                4. Mostrar inventario.
                5. Quitar elemento del inventario.
                0. Sortir de l'aplicació.""", 0, 5);
    }

    private static void createRoom() {
        System.out.println("Crear una sala.");
        String name = Input.readString("Introduce el nombre de la sala:");
        int difficultyNumber = Input.readRangeByte("""
                Escoge una opción:
                1. Fácil.
                2. Media.
                3. Difícil.""", 1, 3);
        Difficulty difficultyLevel = MenuHelper.formatDifficulty(difficultyNumber);
        MenuHelper.tryRoomCreation(name, difficultyLevel);
    }

    private static void addClueToRoom() {
        int roomId = chooseRoomId("pista");
        try {
            Room chosenRoom = RoomManagement.findRoom(roomId);
            createClue(chosenRoom);
        } catch (NotFoundElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addDecorationToRoom() {
        int roomId = chooseRoomId("decoración");
        try {
            Room chosenRoom = RoomManagement.findRoom(roomId);
            createDecoration(chosenRoom);
        } catch (NotFoundElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int chooseRoomId(String elementName) {
        System.out.println("Añadir " + elementName);
        System.out.println("Estas son las salas actualmente disponibles, " +
                "\nelige el id de la sala a la que quieras añadir la nueva " + elementName);
        System.out.println(RoomManagement.getRooms());
        return Input.readInt("Introduce el id de la sala escogida:");
    }

    public static void createClue(Room chosenRoom) {
        String clueName = Input.readString("Introduce el nombre de la pista:");
        double cluePrice = Input.readDouble("Introduce el precio (sin IVA) de la pista:");
        int clueTime = Input.readInt("Introduce el tiempo estimado de resolución de " +
                "la pista (en minutos):");
        String clueTheme = Input.readString("Introduce el tema de la pista:");
        MenuHelper.tryClueCreation(chosenRoom, clueName, cluePrice, clueTime, clueTheme);
    }

    private static void createDecoration(Room chosenRoom) {
        String decorationName = Input.readString("Introduce el nombre de la decoración:");
        int decorationQuantity = Input.readInt("Introduce la cantidad de ejemplares a añadir:");
        double decorationPrice = Input.readDouble("Introduce el precio (sin IVA) de cada ejemplar:");
        String decorationMaterial = Input.readString("Introduce el material:");
        MenuHelper.tryDecorationCreation(chosenRoom, decorationName, decorationQuantity, decorationPrice, decorationMaterial);
    }

    private static void showInventory() {
        System.out.println("Mostrar inventario.");
        if (InventoryManagement.isInventoryEmpty()) System.out.println("El inventario está vacío");
        else InventoryManagement.printInventory();
    }

    private static void removeFromInventory() {
        int choice = Input.readRangeByte("""
                    Qué tipo de elemento quieres eliminar?
                    1. Pista
                    2. Decoración""", 1, 2);
        if (choice == 1) {
            removeClue();
        } else if (choice == 2) {
            removeDecoration();
        }
    }

    private static void removeClue() {
        if (InventoryManagement.noClues()) {
            System.out.println("No existen pistas en el inventario.");
        } else {
            int clueId = chooseClueId();
            MenuHelper.tryRemoveClue(clueId);
        }
    }

    private static int chooseClueId() {
        System.out.println("Mostrando todas las pistas:");
        InventoryManagement.printCluesInventory();
        return Input.readInt("Introduce el id de la pista a eliminar:");
    }

    private static void removeDecoration() {
        if (InventoryManagement.noDecorations()) {
            System.out.println("No existen decoraciones en el inventario.");
        } else {
            int decorationId = chooseDecorationId();
            MenuHelper.tryRemoveDecoration(decorationId);
        }
    }

    private static int chooseDecorationId() {
        System.out.println("Mostrando todas las decoraciones:");
        InventoryManagement.printDecorationsInventory();
        return Input.readInt("Introduce el id de la decoración a eliminar:");
    }
}
