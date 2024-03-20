package sg.com.smartinventory.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String id) {
        super("Could not find review with id: " + id + ". ");
    }
}