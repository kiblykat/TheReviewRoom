package sg.com.smartinventory.services;

import java.util.List;

import sg.com.smartinventory.entities.UserRole;

public interface UserRoleService {
    UserRole createUserRole(UserRole role);

    UserRole getOneUserRole(Long id);

    List<UserRole> getAllUserRoles();

    UserRole updateUserRole(Long id, UserRole role);

    void deleteUserRole(Long id);

    // UserRole addUserToUserRole(Long roleId, Long userId);
}
