package sg.com.smartinventory.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_role")
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "role")
  private String roleName;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
  private List<User> users;

  public UserRole(String roleName, String description) {
    this.roleName = roleName;
    this.description = description;
  }
}
