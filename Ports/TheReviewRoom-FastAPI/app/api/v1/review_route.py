from fastapi import APIRouter

from app.dependencies import SessionDep

review_router = APIRouter(prefix="/v1/reviews", tags=["reviews"])

@review_router.get("/")
async def create_review(session: SessionDep):
    return {"message": "Review Created"}