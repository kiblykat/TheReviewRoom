from fastapi import APIRouter

from app.dependencies import SessionDep

product_router = APIRouter(prefix="/v1/products", tags=["products"])

@product_router.get("/")
async def create_product(session: SessionDep):
    return {"message": "Product Created"}