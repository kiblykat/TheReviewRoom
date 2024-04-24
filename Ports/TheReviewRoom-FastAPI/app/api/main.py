from fastapi import APIRouter
from .v1 import customer_route, product_route, review_route

api_router = APIRouter()

api_router.include_router(customer_route.customer_router)
api_router.include_router(product_route.product_router)
api_router.include_router(review_route.review_router)
