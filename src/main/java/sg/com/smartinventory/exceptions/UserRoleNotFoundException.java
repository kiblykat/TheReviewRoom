package sg.com.smartinventory.exceptions;

public class UserRoleNotFoundException extends RuntimeException {
    public UserRoleNotFoundException(Long id) {
        super("User role with id: " + id + " cannot be found");
    }
}
