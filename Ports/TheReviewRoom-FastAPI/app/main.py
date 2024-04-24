from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from . import dataloader, start_db
from .api.main import api_router
from .core.config import settings


the_review_room = FastAPI()

# Set all CORS enabled origins
if settings.BACKEND_CORS_ORIGINS:
    the_review_room.add_middleware(
        CORSMiddleware,
        allow_origins=[
            str(origin).strip("/") for origin in settings.BACKEND_CORS_ORIGINS
        ],
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )

the_review_room.include_router(api_router, prefix=settings.API_V1_STR)

# Initiate the database, create the tables and load the initial data. 
start_db.main()
dataloader.main()

@the_review_room.get("/")
async def root():
    return {"message": "Hello World"}
