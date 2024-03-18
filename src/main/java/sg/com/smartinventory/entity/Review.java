package sg.com.smartinventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id; //PK
  @Column(name="category")
  private String category;
  @Column(name="review_content")
  private String reviewContent;
  @Column(name="rating")
  private int rating;
  @Column(name="customer_id")
  private int customerId; //FK
  @Column(name="product_id")
  private int productId; //FK
}
