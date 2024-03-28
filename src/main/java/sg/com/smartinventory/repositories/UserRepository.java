package sg.com.smartinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  User findByEmail(String email);
}
