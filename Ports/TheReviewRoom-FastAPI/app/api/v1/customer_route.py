from collections.abc import Generator
from fastapi import APIRouter

from app.dependencies import SessionDep
from app.models.customer import Customer
from app.services.customer_service import create_customer, get_customer_by_id

customer_router = APIRouter(prefix="/customers", tags=["customers"])

@customer_router.post("/")
async def create_new_customer(customer: Customer, session: SessionDep):
    create_customer(session=session, customer_create=customer)
    
    return {"message": "Customer Created"}

@customer_router.get("/{customer_id}")
async def get_customer(customer_id: int, session: SessionDep):
    customer = get_customer_by_id(session=session, id=customer_id)

    return {"customer": str(customer)}