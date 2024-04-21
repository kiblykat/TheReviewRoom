from collections.abc import Generator
from typing import Annotated

from fastapi import Depends
from sqlmodel import Session

from app.core.db import engine

# This defines a function get_db that yields instances of Session. 
# This function is a generator, which is a special type of iterator that can be paused and resumed, allowing it to maintain state.
def get_db() -> Generator[Session, None, None]:
    # This creates a new Session instance using the engine object. The Session instance is used to interact with the database.
    with Session(engine) as session:
        # This yields the Session instance, allowing it to be used by the caller of the function. 
        # After the caller is done with the Session, control is returned back to this function, and the Session is automatically closed.
        yield session

# This creates an annotated type SessionDep that is essentially the Session type, but with a dependency on the get_db function. 
# This means that whenever FastAPI sees SessionDep as a dependency, it will call get_db to get a Session instance.
SessionDep = Annotated[Session, Depends(get_db)]
