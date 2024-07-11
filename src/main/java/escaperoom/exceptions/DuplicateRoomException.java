package escaperoom.exceptions;

public class DuplicateRoomException extends RuntimeException{
    public DuplicateRoomException(String message) {
        super(message);
    }
}
