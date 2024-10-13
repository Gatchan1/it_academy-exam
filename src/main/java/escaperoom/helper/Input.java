package escaperoom.helper;

import escaperoom.exception.EmptyStringException;
import escaperoom.exception.OutOfRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    static Scanner sc = new Scanner(System.in);

    public static byte readRangeByte(String message, int minOption, int maxOption) {
        byte reading = 0;
        boolean askUser = true;
        while (askUser) {
            try {
                System.out.println(message);
                reading = sc.nextByte();
                if (reading < minOption || reading > maxOption) throw new
                        OutOfRangeException("El número introducido está fuera de rango.");
                askUser = false;
            } catch (InputMismatchException e) {
                System.out.println("Error en la introducción del dato.");
            } catch (OutOfRangeException e) {
                System.out.println(e.getMessage());
            } finally {
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

    public static double readDouble(String message) {
        double reading = 0;
        boolean askUser = true;
        while (askUser) {
            try {
                System.out.println(message);
                reading = sc.nextDouble();
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
                if (!reading.isEmpty()) askUser = false;
                else throw new EmptyStringException("No puede introducirse un dato vacío.");
            } catch ( EmptyStringException e) {
                System.out.println(e.getMessage());
            }
        }
        return reading;
    }
}
