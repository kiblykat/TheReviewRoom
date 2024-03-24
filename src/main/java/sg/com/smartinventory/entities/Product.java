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
  private long id; // PK. PostgreSQL bigserial data type.

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

  // Uni-directional One to Many mapping -> One Product (Parent) can have many
  // Reviews (Child). The extra column 'product_id' will be created on the many
  // side of the relationship, that is, in the Review table. The cascade attribute
  // is set to CascadeType.ALL to cascade all operations (e.g., save, update,
  // delete) to the associated Review entities. The @JoinColumn annotation is used
  // to specify the foreign key column (product_id) in the Review table that
  // establishes the relationship. Thus, it specifies the foreign key column in
  // the child entity’s table that refers to the parent entity. The ‘mappedBy’
  // attribute is not used in a unidirectional relationship as it’s specific to
  // bidirectional relationships.
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private List<Review> reviews;

  public Product() {
  }

  // Define Constructor for DataLoader.
  public Product(String category, String name, String description, double price, int stockQuantity) {
    this();

    this.category = category;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stockQuantity = stockQuantity;
  }

  // @ManyToOne Customer -> Many Products can be linked to 1 Customer
}