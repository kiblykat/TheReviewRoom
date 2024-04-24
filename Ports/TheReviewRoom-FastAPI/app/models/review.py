from sqlmodel import Field, Relationship, SQLModel

# Database model, database table inferred from class name.
class Review(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    category: str
    review_content: str
    rating: int
    address: str
    postal_code: int
    phone_number: int
    email: str
    first_name: str
    first_name: str
    customer_id: int | None = Field(default=None, foreign_key="customer.id")
    product_id: int | None = Field(default=None, foreign_key="product.id")
