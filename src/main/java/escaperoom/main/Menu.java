package escaperoom.main;

import escaperoom.components.Clue;
import escaperoom.components.ThemedRoom;
import escaperoom.exceptions.DuplicateClueException;
import escaperoom.exceptions.DuplicateRoomException;
import escaperoom.exceptions.NotFoundRoomException;
import escaperoom.helpers.Helper;
import escaperoom.helpers.Input;

import java.util.ArrayList;

public class Menu {
    private static ArrayList<ThemedRoom> rooms = new ArrayList<ThemedRoom>();

    public static void start() {
        boolean sortir = false;

        //rooms.add(new ThemedRoom("a", 1));
        //rooms.add(new ThemedRoom("b", 2));

        System.out.println("\nBienvenido a 'La sala de los Enigmas'");
          do{
              switch(menu()){
                  case 1: createRoom();
                      break;
                  case 2: addClue();
                      break;
                  case 3: addDecoration();
                      break;
                  case 4: showInventory();
                      break;
                  case 5: removeFromInventory();
                      break;
                  case 0: System.out.println("Gràcies per utilitzar l'aplicació");
                      sortir = true;
                      break;
              }
          }while(!sortir);
      }

    private static byte menu(){
        byte option;
        final byte min = 0;
        final byte max = 3;

        do{
            option = Input.readByte("\nEscoge una opción:"+
                    "\n1. Crear sala."+
                    "\n2. Añadir pista a una sala."+
                    "\n3. Opció 3."+
                    "\n0. Sortir de l'aplicació.\n");
            if(option < min || option > max){
                System.out.println("Escull una opció vàlida");
            }
        }while(option < min || option > max);
        return option;
    }

    private static void createRoom(){
        System.out.println("Crear una sala.");
        String name = Input.readString("Introduce el nombre de la sala:");
        int difficulty;
        final byte min = 1;
        final byte max = 3;
        do{
            difficulty = Input.readInt("\nEscoge una opción:"+
                    "\n1. Fácil."+
                    "\n2. Media."+
                    "\n3. Difícil.\n");
            if(difficulty < min || difficulty > max){
                System.out.println("Escull una opció vàlida");
            }
        }while(difficulty < min || difficulty > max);

        ThemedRoom newRoom = new ThemedRoom(name, difficulty);
        try {
            Helper.addRoom(newRoom, rooms);
        } catch (DuplicateRoomException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addClue(){
        System.out.println("Añadir una pista.");
        System.out.println("Estas son las salas actualmente disponibles, " +
                "\nelige el id de la sala a la que quieras añadir la nueva pista.");
        System.out.println(rooms);
        try {
            int id = Input.readInt("Introduce el id de la sala escogida:");
            ThemedRoom chosenRoom = Helper.chooseRoom(id, rooms);
            Clue newClue = Helper.createClue();
            chosenRoom.addClue(newClue);
        } catch (NotFoundRoomException | DuplicateClueException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addDecoration(){
        System.out.println("Añadir una pieza de decoración");
        //TODO
    }

    private static void showInventory() {
        System.out.println("Mostrar inventario.");

        //TODO. Include filters.
    }

    private static void removeFromInventory() {
        //TODO
    }
}
