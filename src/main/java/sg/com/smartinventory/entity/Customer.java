package sg.com.smartinventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="customer")
public class Customer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id; //PK
  
  @Column(name="first_name")
  private String firstName;
  @Column(name="last_name")
  private String lastName;
  @Column(name="country")
  private String country;
  @Column(name="address")
  private String address;
  @Column(name="postal_code")
  private int postalCode;
  @Column(name="mobile_number")
  private int mobileNumber;
  @Column(name="email")
  private String email;
  @Column(name="review_id")
  private int reviewId; //FK

  //Define Constructor for dataLoader
  public Customer(String firstName, String lastName, String country, String address, int postalCode, int mobileNumber,
      String email, int reviewId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.address = address;
    this.postalCode = postalCode;
    this.mobileNumber = mobileNumber;
    this.email = email;
    this.reviewId = reviewId;
  }

  
}
