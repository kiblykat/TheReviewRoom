from typing import Any

from sqlmodel import Session, select

from app.models.customer import Customer, CustomerUpdate
from app.core.db import engine


def create_customer(*, session: Session, customer_create: Customer) -> Customer:
    db_obj = Customer.model_validate(
        customer_create
    )
    session.add(db_obj)
    session.commit()
    session.refresh(db_obj)

    return db_obj

def update_customer_name(*, session: Session, db_customer: Customer, customer_in: CustomerUpdate) -> Any:
    customer_data = customer_in.model_dump(exclude_unset=True)
    extra_data = {}
    if "first_name" in customer_data:
        first_name = customer_data["first_name"]
        extra_data["first_name"] = first_name
    if "last_name" in customer_data:
        last_name = customer_data["last_name"]
        extra_data["last_name"] = last_name
    db_customer.sqlmodel_update(customer_data, update=extra_data)
    session.add(db_customer)
    session.commit()
    session.refresh(db_customer)
    
    return db_customer

def get_customer_by_id(*, session: Session, id: int) -> Customer | None:
    statement = select(Customer).where(Customer.id == id)
    session_customer = session.exec(statement).first()
    
    return session_customer