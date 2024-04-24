from sqlmodel import Session, SQLModel, create_engine, select

# from app import crud
from app.core.config import settings
from app.models.customer import Customer, CustomerUpdate
from app.models.product import Product
from app.models.review import Review
# from app.services.customer_service import create_customer

engine = create_engine(str(settings.SQLALCHEMY_DATABASE_URI))


# make sure all SQLModel models are imported (app.models) before initializing DB
# otherwise, SQLModel might fail to initialize relationships properly
# for more details: https://github.com/tiangolo/full-stack-fastapi-template/issues/28


def init_db(session: Session) -> None:
    # Tables should be created with Alembic migrations.
    # But if you don't want to use migrations, create
    # the tables un-commenting the next lines.
    # from app.core.engine import engine
    
    # This works because the models are already imported and registered from app.models.
    SQLModel.metadata.create_all(engine)

    # user = session.exec(
    #     select(User).where(User.email == settings.FIRST_SUPERUSER)
    # ).first()
    # if not user:
    #     user_in = UserCreate(
    #         email=settings.FIRST_SUPERUSER,
    #         password=settings.FIRST_SUPERUSER_PASSWORD,
    #         is_superuser=True,
    #     )
    #     user = crud.create_user(session=session, user_create=user_in)
    
    # create_customer(session=session, customer_create=Customer(first_name="John", last_name="Doe", country="USA", address="1234 Main St.", postal_code=12345, phone_number=1234567890, email="a@a.com"))