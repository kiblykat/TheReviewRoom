```mermaid
erDiagram
    Customer {
        long id PK
        string first_name
        string last_name
        string country
        string address
        int postal_code
        int mobile_number
        string email
    }

    Review {
        long id PK
        string category
        string review_content
        int rating
        long customer_id FK
        long product_id FK
    }

    Product {
        long id PK
        string category
        string name
        string description
        double price
        int stock_quantity
    }

    Customer ||--O{ Review : "Has"
    Product ||--O{ Review : "Has"
```
