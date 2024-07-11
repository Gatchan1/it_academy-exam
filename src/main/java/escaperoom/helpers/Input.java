package escaperoom.helpers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    static Scanner sc = new Scanner(System.in);

    public static byte readByte(String message) {
        byte reading = 0;
        boolean askUser = true;
        while (askUser) {
            try {
                System.out.println(message);
                reading = sc.nextByte();
                sc.nextLine();
                askUser = false;
            } catch (InputMismatchException e) {
                System.out.println("Error en la introducción del dato.");
                sc.nextLine();
            }
        }
        return reading;
    }

    public static int readInt(String message) {
        int reading = 0;
        boolean askUser = true;
        while (askUser) {
            try {
                System.out.println(message);
                reading = sc.nextInt();
                sc.nextLine();
                askUser = false;
            } catch (InputMismatchException e) {
                System.out.println("Error en la introducción del dato.");
                sc.nextLine();
            }
        }
        return reading;
    }

    public static float readFloat(String message) {
        float reading = 0;
        boolean askUser = true;
        while (askUser) {
            try {
                System.out.println(message);
                reading = sc.nextFloat();
                sc.nextLine();
                askUser = false;
            } catch (InputMismatchException e) {
                System.out.println("Error en la introducción del dato.");
                sc.nextLine();
            }
        }
        return reading;
    }

    public static String readString(String message) {
        String reading = "a";
        boolean askUser = true;
        while (askUser) {
            try {
                System.out.println(message);
                reading = sc.nextLine();
                askUser = false;
            } catch (RuntimeException e) {
                System.out.println("Error en la introducción del dato.");
            }
        }
        return reading;
    }
}
