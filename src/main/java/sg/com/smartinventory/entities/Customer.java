package sg.com.smartinventory.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id; // PK. PostgreSQL bigserial data type.

  @NotBlank(message = "First name is mandatory. ")
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "country")
  private String country;

  @Column(name = "address")
  private String address;

  @Digits(fraction = 0, integer = 6, message = "Postal code should be 6 digits. ")
  @Column(name = "postal_code")
  private int postalCode;

  @Digits(fraction = 0, integer = 8, message = "Phone no should be 8 digits. ")
  @Column(name = "phone_number")
  private int phoneNumber;

  @Email(message = "Email should be valid. ")
  @Column(name = "email")
  private String email;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)

  private List<Review> reviews;

  public Customer() {
  }

  // Define Constructor for DataLoader.
  public Customer(String firstName, String lastName, String country, String address, int postalCode,
      int phoneNumber,
      String email) {
    this();

    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.address = address;
    this.postalCode = postalCode;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
}