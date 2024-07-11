package escaperoom.exceptions;

public class NotFoundRoomException extends RuntimeException {
    public NotFoundRoomException(String message) {
        super(message);
    }
}
