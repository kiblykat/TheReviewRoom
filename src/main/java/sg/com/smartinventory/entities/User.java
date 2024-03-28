package sg.com.smartinventory.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "Username cannot be blank")
  @Column(name = "username")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  @Column(name = "password")
  private String password;

  @NotBlank(message = "First name cannot be blank")
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Digits(fraction = 0, integer = 8, message = "ContactNo must be 8 digits")
  @Column(name = "contact_no")
  private String contactNo;

  @Email(message = "Email format must be valid")
  @Column(name = "email")
  private String email;

  @JsonBackReference
  @ManyToOne(optional = false)
  @JoinColumns({
      @JoinColumn(name = "role_id", referencedColumnName = "id"),
      @JoinColumn(name = "role_name", referencedColumnName = "role_name")
  })
  private UserRole userRole;

  public User(String username) {
    this.username = username;
  }
}
