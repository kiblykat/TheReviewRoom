package sg.com.smartinventory.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Bigserial.
  @Column(name = "id")
  private long id; // PK. PostgreSQL bigserial data type.

  @Column(name = "category")
  private String category;

  @Column(name = "review_content")
  private String reviewContent;

  @Column(name = "rating")
  private int rating;

  @Min(value = 1, message = "Customer ID should start from 1. ")
  @Column(name = "customer_id")
  private long customerId; // FK. PostgreSQL bigserial data type.

  @Min(value = 1, message = "Product ID should start from 1. ")
  @Column(name = "product_id")
  private long productId; // FK. PostgreSQL bigserial data type.

  public Review() {
  }

  // Define Constructor for DataLoader.
  public Review(String category, String reviewContent, int rating, long customerId, long productId) {
    this();

    this.category = category;
    this.reviewContent = reviewContent;
    this.rating = rating;
    this.customerId = customerId;
    this.productId = productId;
  }

  // @ManyToOne Product -> Many Reviews can be linked to 1 Product

  // @ManyToOne Customer -> Many Reviews can be linked to 1 Customer
}