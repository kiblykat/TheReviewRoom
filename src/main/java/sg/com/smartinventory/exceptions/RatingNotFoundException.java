package sg.com.smartinventory.exceptions;

public class RatingNotFoundException extends RuntimeException {
  public RatingNotFoundException(int rating) {
    super("No products with rating=" + rating + " found");
  }
}
