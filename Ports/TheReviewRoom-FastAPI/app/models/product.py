from sqlmodel import Field, Relationship, SQLModel

# Database model, database table inferred from class name.
class Product(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    category: str
    name: str
    description: str
    price: float
    stock_quantity: int
