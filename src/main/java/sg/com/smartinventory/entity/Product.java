package sg.com.smartinventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "category")
  private String category;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private double price;

  @Column(name = "stock_quantity")
  private int stockQuantity;

  @Column(name = "review_id")
  private String reviewId; // FK.

  public Product() {
  }

  // Define Constructor for DataLoader.
  public Product(String category, String name, String description, double price, int stockQuantity, String reviewId) {
    this();

    this.category = category;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stockQuantity = stockQuantity;
    this.reviewId = reviewId;
  }
}